package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import pages.OctopusSignUpPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class Octopus_02_SignUpStepDefinitions {
    OctopusSignUpPage octopusSignUpPage = new OctopusSignUpPage();
    Actions actions = new Actions(Driver.getDriver());
    Faker faker = new Faker();
    String email;
    String password = "12345678aA";

    @Given("user clicks on the Sign Up button in Home page")
    public void userClicksOnTheSignUpButtonInHomePage() {
        ReusableMethods.waitForClickablility(octopusSignUpPage.signUpButton, 5);

        actions.click(octopusSignUpPage.signUpButton).perform();
        // ReusableMethods.click(octopusSignUpPage.signUpButton);
    }

    @When("user enters Full Name into the Full Name field")
    public void userEntersFullNameIntoTheFullNameField() {
        actions.sendKeys(octopusSignUpPage.enterFullName, faker.name().fullName()).perform();
    }


    @And("user enters Email Address into the Email Address field")
    public void userEntersEmailAddressIntoTheEmailAddressField() {
        Driver.getDriver().switchTo().newWindow(WindowType.TAB);
        Driver.getDriver().get(ConfigReader.getProperty("fakemail3"));
        ReusableMethods.waitForVisibility(octopusSignUpPage.copyEmail, 5);
        email = octopusSignUpPage.copyEmail.getText();
        ReusableMethods.switchToWindow1(0);
        octopusSignUpPage.enterEmail.sendKeys(email);
    }

    @And("user enters Password into the Password field")
    public void userEntersPasswordIntoThePasswordField() {
        octopusSignUpPage.enterPassword.sendKeys(password, Keys.TAB, password);
    }

    @And("user clicks on the reCAPTCHA checkbox")
    public void userClicksOnTheReCAPTCHACheckbox() {
        ReusableMethods.wait((int) (Math.random() * 5) + 1);
        Driver.getDriver().switchTo().frame(octopusSignUpPage.reCaptchaiframe);
        ReusableMethods.click(octopusSignUpPage.recaptcha);
        Driver.getDriver().switchTo().defaultContent();

    }


    @And("user clicks on the I accept KVKK, Clarification Text checkbox")
    public void userClicksOnTheIAcceptKVKKClarificationTextCheckbox() {
        ReusableMethods.click(octopusSignUpPage.checkBox);
    }


    @And("user clicks on the Sign Up button")
    public void userClicksOnTheSignUpButton() {
        ReusableMethods.wait(2);
        ReusableMethods.click(octopusSignUpPage.signUpButton2);
    }
    @And("user enters verification code")
    public void userEntersVerificationCode() {
        ReusableMethods.switchToWindow1(1);

        ReusableMethods.waitForVisibility(octopusSignUpPage.verificationCodeMail,15);
        ReusableMethods.click(octopusSignUpPage.verificationCodeMail);
        Driver.getDriver().switchTo().frame(octopusSignUpPage.emailFrame);
        ReusableMethods.waitForVisibility(octopusSignUpPage.verificationCodeText,10);
        ReusableMethods.scroll(octopusSignUpPage.verificationCodeText);
        String verificationCode = octopusSignUpPage.verificationCodeText.getText().replaceAll("[^0-9]", "");
        Driver.getDriver().switchTo().defaultContent();
        ReusableMethods.switchToWindow1(0);
        ReusableMethods.waitForClickablility(octopusSignUpPage.otpInput, 5);
        actions.sendKeys(octopusSignUpPage.otpInput, verificationCode).perform();
        ReusableMethods.click(octopusSignUpPage.dogrula);
    }


    @When("The user clicks the sign up button without filling in any fields.")
    public void the_user_clicks_the_sign_up_button_without_filling_in_any_fields() {
        ReusableMethods.scroll(octopusSignUpPage.signUpButton2);
        ReusableMethods.waitForClickablility(octopusSignUpPage.signUpButton2, 5);
        ReusableMethods.click(octopusSignUpPage.signUpButton2);
    }

    @Then("The user should see |isim gerekli| message  under Full Name field")
    public void the_user_should_see_isim_gerekli_message_under_full_name_field() {
        ReusableMethods.scroll(octopusSignUpPage.isimGerekli);
        ReusableMethods.waitForVisibility(octopusSignUpPage.isimGerekli, 5);
        String expectedMessage = "İsim gerekli";
        String actualMessage = octopusSignUpPage.isimGerekli.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Then("The user should see |E-posta gerekli| message under Email Address field")
    public void the_user_should_see_e_posta_gerekli_message_under_email_address_field() {
        ReusableMethods.scroll(octopusSignUpPage.ePostaGerekli);
        String expectedMessage = "E-posta gerekli";
        String actualMessage = octopusSignUpPage.ePostaGerekli.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Then("The user should see |Şifre gerekli| and |Şifre gereksinimleri karşılamıyor| messagesunder Password field")
    public void the_user_should_see_şifre_gerekli_and_şifre_gereksinimleri_karşılamıyor_messagesunder_password_field() {
        ReusableMethods.scroll(octopusSignUpPage.sifreGerekli);
        String expectedMessage = "Şifre gerekli";
        String actualMessage = octopusSignUpPage.sifreGerekli.getText();
        Assert.assertEquals(expectedMessage, actualMessage);

        String expectedMessage2 = "Şifre gereksinimleri karşılamıyor";
        String actualMessage2 = octopusSignUpPage.sifreGereksinimleriKarşılamıyor.getText();
        Assert.assertEquals(expectedMessage2, actualMessage2);

    }

    @Then("The user should see |Şifre doğrulaması gerekiyor| message under Confirm Password field")
    public void the_user_should_see_şifre_doğrulaması_gerekiyor_message_under_confirm_password_field() {
        ReusableMethods.scroll(octopusSignUpPage.sifreDogrulamasıGerekiyor);
        String expectedMessage = "Şifre doğrulaması gerekiyor";
        String actualMessage = octopusSignUpPage.sifreDogrulamasıGerekiyor.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Then("The user should see |captcha gerekli bir alan| message under reCAPTCHA field")
    public void the_user_should_see_captcha_gerekli_bir_alan_message_under_re_captcha_field() {
        ReusableMethods.scroll(octopusSignUpPage.captchaGerekliBirAlan);
        String expectedMessage = "captcha gerekli bir alan";
        String actualMessage = octopusSignUpPage.captchaGerekliBirAlan.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Then("The user should see |Şartları kabul etmeniz gerekmektedir| message under I accept KVKK, Clarification Text field")
    public void the_user_should_see_şartları_kabul_etmeniz_gerekmektedir_message_under_ı_accept_kvkk_clarification_text_field() {
        ReusableMethods.scroll(octopusSignUpPage.sartlarKabulEtmenizGerekmektedir);
        String expectedMessage = "Şartları kabul etmeniz gerekmektedir.";
        String actualMessage = octopusSignUpPage.sartlarKabulEtmenizGerekmektedir.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }


    @When("user enters less than three character into the Full Name field")
    public void userEntersLessThanThreeCharacterIntoTheFullNameField() {
        String name = "Aa";
        actions.sendKeys(octopusSignUpPage.enterFullName, name).perform();

    }
    @Then("User should see this message \\(İsim en az {int} karakter uzunluğunda olmalı) message  under Full Name field")
    public void user_should_see_this_message_i̇sim_en_az_karakter_uzunluğunda_olmalı_message_under_full_name_field(Integer int1) {
        String expectedMessage = "İsim en az 3 karakter uzunluğunda olmalı";
        String actualMessage = octopusSignUpPage.isimEnAz3KarakterUzunlukta.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }


    @When("user enters {string} into the Email Address field")
    public void userEntersIntoTheEmailAddressField(String email) {
        String expectedMessage = "E-posta geçerli formatta olmalı";
        ReusableMethods.waitForClickablility(octopusSignUpPage.enterEmail,5);
        actions.sendKeys(octopusSignUpPage.enterEmail, email).perform();
        Assert.assertEquals(expectedMessage, octopusSignUpPage.emailGecerliFormattaOlmali.getText());
    }


    @Then("User should see this message \\(E-posta geçerli formatta olmalı) message  under Email Address field")
    public void userShouldSeeThisMessageEPostaGecerliFormattaOlmalıMessageUnderEmailAddressField() {
    }


    @When("user enters invalid verification code")
    public void userEntersInvalidVerificationCode() {

        ReusableMethods.waitForClickablility(octopusSignUpPage.otpInput, 5);
        actions.sendKeys(octopusSignUpPage.otpInput, "12345").perform();
        ReusableMethods.click(octopusSignUpPage.dogrula);
    }

    @Then("User should see this message \\(Hata) message")
    public void userShouldSeeThisMessageHataMessage() {
        ReusableMethods.waitForVisibility(octopusSignUpPage.hataMesaji, 5);
        String expectedMessage = "Hata";
        String actualMessage = octopusSignUpPage.hataMesaji.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}
