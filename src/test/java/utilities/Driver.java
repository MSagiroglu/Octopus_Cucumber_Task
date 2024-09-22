package utilities;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    private Driver() {

    }
    static ChromeOptions options;


    static int timeout = 5;

    static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (ConfigReader.getProperty("browser")) {

                case "chrome":
                   //options = new ChromeOptions();
                   //options.addArguments("--no-sandbox");
                   //options.addArguments("--disable-gpu");
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "chrome-headless":
                    options = new ChromeOptions();
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
                //bu secenekte chrome acilmadan test kosulur
                break;
                default:
                    driver = new EdgeDriver();


            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    public static void quitDriver() {
        if (driver != null)
            driver.quit();
        driver = null;
    }


}
