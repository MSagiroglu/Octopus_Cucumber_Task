package pages;

import io.cucumber.java.zh_cn.假如;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OctopusSignUpPage {
    public OctopusSignUpPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[text()='Üye Ol']")
    public WebElement signUpButton;

    @FindBy(xpath = "//input[@id='auth-signup_name']")
    public WebElement enterFullName;

    @FindBy(xpath = "//input[@id='auth-signup_email']")
    public WebElement enterEmail;

    @FindBy(xpath = "//input[@id='auth-signup_password']")
    public WebElement enterPassword;

    @FindBy(xpath = "//input[@id='auth-signup_confirmPassword']")
    public WebElement confirmPassword;
    @FindBy(xpath = "//input[@id='auth-signup_accept']")
    public WebElement checkBox;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement signUpButton2;


    @FindBy(xpath = "//iframe[@title='reCAPTCHA']")
    public WebElement reCaptchaiframe;

    @FindBy(id = "recaptcha-anchor")
    public WebElement recaptcha;

    @FindBy(xpath = "//span[@id='email']")
    public WebElement copyEmail;

    @FindBy(xpath = "//div[text()='İsim gerekli']")
    public WebElement isimGerekli;

    @FindBy(xpath = "//div[text()='E-posta gerekli']")
    public WebElement ePostaGerekli;

    @FindBy(xpath = "//div[text()='Şifre gerekli']")
    public WebElement sifreGerekli;

    @FindBy(xpath = "//div[text()='Şifre gereksinimleri karşılamıyor']")
    public WebElement sifreGereksinimleriKarşılamıyor;

    @FindBy(xpath = "//div[text()='Şartları kabul etmeniz gerekmektedir.']")
    public WebElement sartlarKabulEtmenizGerekmektedir;

    @FindBy(xpath = "//div[text()='captcha gerekli bir alan']")
    public WebElement captchaGerekliBirAlan;
    @FindBy(xpath = "//div[text()='Şifre doğrulaması gerekiyor']")
    public WebElement sifreDogrulamasıGerekiyor;

    @FindBy(xpath = "//div[text()='İsim en az 3 karakter uzunluğunda olmalı']")
    public WebElement isimEnAz3KarakterUzunlukta;

    @FindBy(xpath = "(//input[@maxlength='1'])[1]")
    public WebElement otpInput;

    @FindBy(xpath = "(//*[text()='Verification Code'])[1]")
    public WebElement verificationCodeMail;

    @FindBy(xpath = "//iframe[@id=\"iframeMail\"]")
    public WebElement emailFrame;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn') and .//span[text()='Doğrula']]")
    public WebElement dogrula;

    @FindBy(xpath = "// b[contains(text(), 'Your verification code')]\n")
    public WebElement verificationCodeText;

    @FindBy(xpath = "//div[text()='E-posta geçerli formatta olmalı']")
    public WebElement emailGecerliFormattaOlmali;

    @FindBy(xpath = "//span[@class=\"text-red-500\"]")
    public WebElement hataMesaji;







}

