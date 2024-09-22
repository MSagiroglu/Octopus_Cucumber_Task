

package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static utilities.Driver.getDriver;

public class ReusableMethods {

    public static void logToTxtFile(WebElement element, String definingMessage, String fileName) throws IOException {
        String rootDir = System.getProperty("user.dir");

        String filePath = rootDir + "/test-output/Logs/" + fileName;

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(definingMessage.toString() + element.getText().toString() + "\n");
        writer.close();
    }

    public static void logToTxtFileWithSearchedTermPositive(WebElement element, String definingMessage, String fileName, String searchedTerm) throws IOException {
        String rootDir = System.getProperty("user.dir");

        String filePath = rootDir + "/test-output/Logs/" + fileName;

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(definingMessage.toString() + searchedTerm.toString() + " :" + element.getText().toString());
        writer.close();
    }

    public static void logToTxtFileWithSearchedTermNegativeAfterPositive(WebElement element, String definingMessage, String fileName, String searchedTerm) throws IOException {
        String rootDir = System.getProperty("user.dir");
        String dosyaYolu = rootDir + "/test-output/Logs/" + fileName;
        FileReader fileReader = new FileReader(dosyaYolu);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String text = bufferedReader.readLine();
        bufferedReader.close();

        String filePath = rootDir + "/test-output/Logs/" + fileName;

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(text.toString() + "\n" + definingMessage.toString() + searchedTerm.toString() + " :" + element.getText().toString());
        writer.close();
    }


    public static void getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
    }


    //===============Explicit Wait==============//


    public static void jsExecutorScrool(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        // Belirli webElement element seviyesine scroll
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }


    // HARD WAIT
    public static void wait(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bekle(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //========Hover Over=====//
    public static void hover(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }

    //==========Return a list of string given a list of Web Element====////
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //Alert ACCEPT
    public static void alertAccept() {
        getDriver().switchTo().alert().accept();
    }

    //Alert DISMISS
    public static void alertDismiss() {
        getDriver().switchTo().alert().dismiss();
    }

    //Alert getText()
    public static void alertText() {
        getDriver().switchTo().alert().getText();
    }

    //Alert promptBox
    public static void alertprompt(String text) {
        getDriver().switchTo().alert().sendKeys(text);
    }

    //DropDown VisibleText
    /*
        Select select2 = new Select(gun);
        select2.selectByVisibleText("7");

        //ddmVisibleText(gun,"7"); --> Yukarıdaki kullanım yerine sadece method ile handle edebilirim
     */

    // Selects a random value from a dropdown list and returns the selected Web Element
    public static WebElement selectRandomTextFromDropdown(Select select) {
        Random random = new Random();
        List<WebElement> weblist = select.getOptions();
        int optionIndex = 1 + random.nextInt(weblist.size() - 1);
        select.selectByIndex(optionIndex);
        return select.getFirstSelectedOption();
    }

    public static void ddmVisibleText(WebElement ddm, String visibleText) {
        Select select = new Select(ddm);
        select.selectByVisibleText(visibleText);
    }

    //DropDown Index
    public static void ddmIndex(WebElement ddm, int index) {
        Select select = new Select(ddm);
        select.selectByIndex(index);
    }

    //DropDown Value
    public static void ddmValue(WebElement ddm, String value) {
        Select select = new Select(ddm);
        select.selectByValue(value);
    }

    //SwitchToWindow1
    public static void switchToWindow1(int sayi) {
        List<String> tumWindowHandles = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tumWindowHandles.get(sayi));
    }

    //SwitchToWindow2
    public static void switchToWindow2(int sayi) {
        getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[sayi].toString());
    }
    //EXPLICIT WAIT METHODS

    //Visible Wait
    public static void visibleWait(WebElement element, int sayi) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    //VisibleElementLocator Wait
    public static WebElement visibleWait(By locator, int sayi) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sayi));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //WaitForClickablility Wait Element
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //WaitForClickablility Wait Locator
    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }

    public static void doubleClick(WebElement element) {
        new Actions(getDriver()).doubleClick(element).build().perform();
    }

    public static void selectCheckBox(WebElement element, boolean check) {
        if (check) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }


    //Alert Wait
    public static void alertWait(int sayi) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.alertIsPresent());

    }

    //Tüm Sayfa ScreenShot
    public static void tumSayfaResmi(String userStoryNumber, String pageName) {
        String tarih = new SimpleDateFormat("hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "projectImages/us_" + userStoryNumber + "_images/webpage/" + tarih + "_" + pageName + ".png";
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //WebElement ScreenShot
    public static void webElementResmi( WebElement element, String elementName) {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());

        String dosyaYolu = "projectImages/webelement/" + tarih + "_" + elementName + ".png";
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        try {
            FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getScreenshot(String featureName, String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        // full path to the screenshot location
        String dosyaYolu = System.getProperty("user.dir") + "/target/Screenshots/wepPage/US_" + featureName + "/" + name + date + ".png";
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            // save the screenshot to the path given
            FileUtils.copyFile(source, new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dosyaYolu;
    }

    //WebTable
    public static void printData(int satir, int sutun) {
        WebElement satirSutun = getDriver().findElement(By.xpath("(//tbody)[1]//tr[" + satir + "]//td[" + sutun + "]"));
        System.out.println(satirSutun.getText());
    }


    //JS Scroll
    public static void scroll(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    //JS Sayfa Sonu Scroll
    public static void scrollEnd() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    //JS Sayfa Başı Scroll
    public static void scrollHome() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }

    //JS SendKeys
    public static void sendKeysJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].value='" + text + "'", element);
    }

    //JS SendAttributeValue
    public static void sendAttributeJS(WebElement element, String text) {
        try {
            element.sendKeys(text);

        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
        }
    }

    public static String getTextWithJavaScript(WebElement element) {
        String text ;

        try {
            text = element.getText();
        } catch (Exception e) {

            // JavaScriptExecutor kullanarak elementin içeriğini al
            JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
            text = (String) jsExecutor.executeScript("return arguments[0].textContent;", element);

        }
        return text;
    }


    //JS GetAttributeValue
    public static void getValueByJS(String id, String attributeName) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String attribute_Value = (String) js.executeScript("return document.getElementById('" + id + "')." + attributeName);
        System.out.println("Attribute Value: = " + attribute_Value);
    }
    public static void robotClassDosyaYukleme(String filePath){
        try{
            ReusableMethods.bekle(3);
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);
            Robot robot = new Robot();
            //pressing ctrl+v
            robot.keyPress(KeyEvent.VK_CONTROL);
            ReusableMethods.bekle(3);
            robot.keyPress(KeyEvent.VK_V);
            ReusableMethods.bekle(3);
            //releasing ctrl+v
            robot.keyRelease(KeyEvent.VK_CONTROL);
            ReusableMethods.bekle(3);
            robot.keyRelease(KeyEvent.VK_V);
            ReusableMethods.bekle(3);
            System.out.println("PASSED");
            //pressing enter
            ReusableMethods.bekle(3);
            robot.keyPress(KeyEvent.VK_ENTER);
            ReusableMethods.bekle(3);
            //releasing enter
            robot.keyRelease(KeyEvent.VK_ENTER);
            ReusableMethods.bekle(3);
            System.out.println("ENTER");
        }catch (Exception e){
        }
            }

    //Shadow Root
    public static void shadowRootWithParentElement(WebElement parentElement,String locateValue) {


        //shadow root içeren elementi clickler
        WebElement acceptButton = (WebElement) ((JavascriptExecutor) getDriver())
                .executeScript("return arguments[0].shadowRoot.querySelector('"+locateValue+"')", parentElement);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        js.executeScript("arguments[0].click();", acceptButton);
    }


    public static void shadowRootWithSearchContext(WebElement shadowParentElement, String locateChildElement) {
    /*asagidaki kod ile shadow root yapisina sahip tag'in locatini searchContext objesine atiyoruz. Boylece elementin
        Shadow Dom try yapisina ulasiliyor
                    */
        SearchContext searchContext = shadowParentElement.getShadowRoot();

        /*
        asagidaki kod click islemi yapilmak istenen elementi bulmak icin searchContext icindeki Shadow Dom
        icine bir element locate verilerek aranir ve bir webelemente atanir.
         */
        WebElement shadowElement = searchContext.findElement(By.cssSelector(locateChildElement));
        shadowElement.click();


    }
    public static void shadowRootWithClickParent(WebElement parentElement,WebElement childElement) {
        try {
            parentElement.click();
            childElement.click();
        }catch (Exception e) {

        }

    }

}






