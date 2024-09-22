package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.checkerframework.checker.units.qual.K;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.OctopusLoginPages;
import pages.OctopusScreenCreatePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class Octopus_03_AddScreenStepDefinitions {

    OctopusLoginPages octopusLoginPages = new OctopusLoginPages();
    OctopusScreenCreatePage octopusScreenCreatePage = new OctopusScreenCreatePage();
    Actions actions = new Actions(Driver.getDriver());
    Faker faker = new Faker();

    public void logIn() {
        ReusableMethods.wait((int) (Math.random() * 5) + 1);
        Driver.getDriver().switchTo().frame(octopusLoginPages.reCaptchaiframe);
        ReusableMethods.click(octopusLoginPages.recaptcha);
        Driver.getDriver().switchTo().defaultContent();
        ReusableMethods.wait((int) (Math.random() * 5) + 1);
        actions.click(octopusLoginPages.enterEmail);
        octopusLoginPages.enterEmail.sendKeys(ConfigReader.getProperty("Email"));
        ReusableMethods.wait((int) (Math.random() * 5) + 1);
        octopusLoginPages.enterPassword.sendKeys(ConfigReader.getProperty("Password"));
        ReusableMethods.wait((int) (Math.random() * 5) + 1);
        ReusableMethods.scroll(octopusLoginPages.loginButton);
        ReusableMethods.click(octopusLoginPages.loginButton);

    }


    @Given("User Sign in to Octopus Website")
    public void userSignInToOctopusWebsite() {
        logIn();
    }

    @And("User Enter Screens Page")
    public void userEnterScreensPage() {
        ReusableMethods.waitForClickablility(octopusScreenCreatePage.screens, 20);
        ReusableMethods.click(octopusScreenCreatePage.screens);

    }

    @And("User clicks Create Screen Button")
    public void userClicksCreateScreenButton() {
        ReusableMethods.waitForClickablility(octopusScreenCreatePage.createScreen, 5);
        ReusableMethods.click(octopusScreenCreatePage.createScreen);
    }

    @And("User enters Save ID")
    public void userEntersSaveID() {
        actions.click(octopusScreenCreatePage.IdInfo).perform();
        String RegisterId = "Mustafa123";
        actions.sendKeys(RegisterId).perform();

    }

    @And("User enters Screen Name")
    public void userEntersScreenName() {
        actions.click(octopusScreenCreatePage.screenName).perform();
        String screenName = faker.name() + faker.number().digits(5);
        actions.sendKeys(screenName).perform();
    }

    @And("User selects Program Vertical")
    public void userSelectsProgramVertical() {
        ReusableMethods.click(octopusScreenCreatePage.chooseProgram);
        octopusScreenCreatePage.chooseProgram.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    @And("User selects Emergency Program")
    public void userSelectsEmergencyProgram() {
        ReusableMethods.click(octopusScreenCreatePage.emergencyProgram);
        octopusScreenCreatePage.emergencyProgram.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    @And("User selects Screen Type LED")
    public void userSelectsScreenTypeLED() {
        ReusableMethods.click(octopusScreenCreatePage.screenType);
        octopusScreenCreatePage.screenType.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    @And("User Enter Tag")
    public void userEnterTag() {
        actions.click(octopusScreenCreatePage.tags).perform();
        String tag = faker.name() + faker.number().digits(5);
        octopusScreenCreatePage.tags.sendKeys(tag, Keys.ENTER);
    }

    @And("User selects Event Planning")
    public void userSelectsEventPlanning() {
        ReusableMethods.click(octopusScreenCreatePage.schedulePlan);
        octopusScreenCreatePage.schedulePlan.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    @And("User selects Routings")
    public void userSelectsRoutings() {
        actions.click(octopusScreenCreatePage.chooseAngle).perform();
        octopusScreenCreatePage.chooseAngle.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    @And("User selects Ratio")
    public void userSelectsRatio() {
        actions.click(octopusScreenCreatePage.ratio).perform();
        octopusScreenCreatePage.ratio.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

    }

    @And("User selects Organization")
    public void userSelectsOrganization() {
        ReusableMethods.click(octopusScreenCreatePage.organization);
        octopusScreenCreatePage.organization.sendKeys(Keys.ARROW_DOWN, Keys.ENTER, Keys.TAB);
    }

    @And("User selects Advanced")
    public void userSelectsAdvanced() {
        ReusableMethods.scroll(octopusScreenCreatePage.general);
        actions.sendKeys(octopusScreenCreatePage.general, Keys.ENTER, Keys.TAB).perform();

    }

    @And("User selects Sync Settings")
    public void userSelectsSyncSettings() {
        ReusableMethods.waitForClickablility(octopusScreenCreatePage.syncSettings, 5);
        actions.click(octopusScreenCreatePage.syncSettings).perform();
        actions.sendKeys(octopusScreenCreatePage.syncSettings,Keys.TAB).perform();
    }

    @And("User clicks Position and Size")
    public void userClicksPositionAndSize() {

      ReusableMethods.click(octopusScreenCreatePage.positionAndSize);


    }

    @And("User selects Display Screen")
    public void userSelectsDisplayScreen() {

        octopusScreenCreatePage.selectDisplay.sendKeys(Keys.ENTER,Keys.ARROW_DOWN, Keys.ENTER);
    }

    @And("User clicks Lift & Learn")
    public void userClicksLiftLearn() {
        ReusableMethods.click(octopusScreenCreatePage.liftAndLearn);
       // actions.sendKeys(octopusScreenCreatePage.liftAndLearn, Keys.SPACE).perform();
    }

    @When("User clicks Save Button")
    public void userClicksSaveButton() {
        ReusableMethods.scroll(octopusScreenCreatePage.saveButton);
        ReusableMethods.click(octopusScreenCreatePage.saveButton);
    }

    @Then("User should be able to add a new screen")
    public void userShouldBeAbleToAddANewScreen() {
        ReusableMethods.waitForVisibility(octopusScreenCreatePage.screenUpdateMessage, 10);
        String actualMessage = octopusScreenCreatePage.screenUpdateMessage.getText();
        System.out.println("actualMessage = " + actualMessage);
        String expectedMessage = "Screen Update";
        Assert.assertEquals(expectedMessage, actualMessage);

    }

    @Then("User should not be able to add a new screen")
    public void userShouldNotBeAbleToAddANewScreen() {
        ReusableMethods.waitForVisibility(octopusScreenCreatePage.screenUpdateMessage, 10);
        String actualMessage = octopusScreenCreatePage.screenUpdateMessage.getText();
        ReusableMethods.webElementResmi(octopusScreenCreatePage.screenUpdateMessage,"Hata Mesajı");
        System.out.println("actualMessage = " + actualMessage);
        String expectedMessage = "Kaydetme sırasında bir hata oluştu, lütfen alanları kontrol edin\n" +
                "Kırmızı alanlar zorunludur.";
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}