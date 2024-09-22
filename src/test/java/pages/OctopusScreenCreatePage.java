package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OctopusScreenCreatePage {
    public OctopusScreenCreatePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[text()='İlk ekranınızı ekleyin!']")
    public WebElement screens;

    @FindBy(xpath = "//*[text()='Ekran Oluştur']")
    public WebElement createScreen;
    @FindBy(xpath = "//input[@id='screen_uuid']\n")
    public WebElement IdInfo;

    @FindBy(xpath = "//input[@id='title']")
    public WebElement screenName;

    @FindBy(xpath = "// input[@id='program_id']")
    public WebElement chooseProgram;
    @FindBy(xpath = "//input[@id='emergency_program_id']")
    public WebElement emergencyProgram;
    @FindBy(xpath = "//input[@id='screen_type']")
    public WebElement screenType;

    @FindBy(xpath = "//input[@id='tags']")
    public WebElement tags;
    @FindBy(xpath = "//input[@id='schedule_id']")
    public WebElement schedulePlan;

    @FindBy(xpath = "//label[@class='ant-radio-button-wrapper ant-radio-button-wrapper-checked ant-radio-button-wrapper-in-form-item !h-full css-1oo3gz8']")
    public WebElement chooseAngle;
    @FindBy(xpath = "//input[@id='ratio_id']")
    public WebElement ratio;
    @FindBy(xpath = "//input[@id='organization_id']")
    public WebElement organization;
    @FindBy(xpath = "//span[text()='Gelişmiş']")
    public WebElement general;
    @FindBy(xpath = "(//span[@class='ant-radio ant-wave-target'])[1]")
    public WebElement syncSettings;
    @FindBy(xpath = "//button[@id='positionAndSize']")
    public WebElement positionAndSize;
    @FindBy(xpath = "//input[@id='positionAndSizeData_selectDisplay']")
    public WebElement selectDisplay;

    @FindBy(xpath = "//button[@id='option_lift_and_learn_enabled']")
    public WebElement liftAndLearn;
    @FindBy(xpath = "//*[text()='Kaydet']")
    public WebElement saveButton;
    @FindBy(xpath = "//div[@class='ant-notification-notice-wrapper']")
    public WebElement screenUpdateMessage;
}