package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OctopusLoginPages {
    public OctopusLoginPages() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

 //  @FindBy(xpath = "//*[@id=\"auth-login_email\"]")
 //  public WebElement enterEmail;

    @FindBy(xpath = "//*[@id=\"auth-login_captcha\"]/div/div/div/div/iframe")
    public WebElement reCaptchaiframe;

    @FindBy(xpath = "/html/body/main/div[2]/div/div[4]/div[1]")
    public WebElement homePage;

    @FindBy(id = "auth-login_email")
    public WebElement enterEmail;

    @FindBy(id = "auth-login_password")
    public WebElement enterPassword;

    @FindBy(id = "recaptcha-anchor")
    public WebElement recaptcha;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@class='m-0 text-pageHeader font-bold text-primary-900']")
    public WebElement dashboardText;


}
