package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import pages.OctopusLoginPages;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import static org.junit.Assert.assertTrue;

public class Octopus_01_LoginStepDefinitions {

    OctopusLoginPages octopusLoginPages = new OctopusLoginPages();
    Actions actions = new Actions(Driver.getDriver());

    @Given("User enters valids email")
    public void user_enters_valids_email() {

      // ReusableMethods.wait((int) (Math.random() * 5) + 1);
      // Driver.getDriver().switchTo().frame(octopusLoginPages.reCaptchaiframe);
      // ReusableMethods.click(octopusLoginPages.recaptcha);
      // Driver.getDriver().switchTo().defaultContent();
      // ReusableMethods.wait((int) (Math.random() * 5) + 1);
      // actions.click(octopusLoginPages.enterEmail);
      // octopusLoginPages.enterEmail.sendKeys(ConfigReader.getProperty("Email"));
        //actions.sendKeys(ConfigReader.getProperty("Email")).perform();

         try {
             if (octopusLoginPages.reCaptchaiframe.isDisplayed()) {
                 Driver.getDriver().switchTo().frame(octopusLoginPages.reCaptchaiframe);
                 ReusableMethods.click(octopusLoginPages.recaptcha);
                 Driver.getDriver().switchTo().defaultContent();
                 ReusableMethods.wait((int) (Math.random()*5)+1);
                 actions.click(octopusLoginPages.enterEmail);
                 ReusableMethods.wait((int) (Math.random()*5)+1);
                 octopusLoginPages.enterEmail.sendKeys(ConfigReader.getProperty("Email"));
                 //actions.sendKeys(ConfigReader.getProperty("Email")).perform();


             }
         } catch (Exception e) {
             actions.click(octopusLoginPages.enterEmail);
             actions.sendKeys(ConfigReader.getProperty("Email")).perform();
         }
    }

    @Given("User enters valids password")
    public void user_enters_valids_password() {
        ReusableMethods.wait((int) (Math.random() * 5) + 1);
        octopusLoginPages.enterPassword.sendKeys(ConfigReader.getProperty("Password"));
        // actions.sendKeys(octopusLoginPages.enterPassword, ConfigReader.getProperty("Password")).perform();
    }


    @When("User clicks on Sign In button")
    public void userClicksOnSignInButton() {

        ReusableMethods.wait((int) (Math.random() * 5) + 1);
        ReusableMethods.scroll(octopusLoginPages.loginButton);
        ReusableMethods.click(octopusLoginPages.loginButton);
        // actions.click(octopusLoginPages.loginButton).perform();
    }

    @Then("User should be able to sign in successfully")
    public void userShouldBeAbleToSignInSuccessfully() {
        ReusableMethods.waitForVisibility(octopusLoginPages.dashboardText, 10);
        String dashboardText = octopusLoginPages.dashboardText.getText();
        Assert.assertTrue(dashboardText.contains(ConfigReader.getProperty("DashboardName")));
    }
}