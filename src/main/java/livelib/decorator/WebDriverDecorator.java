package livelib.decorator;

import livelib.utils.JSExecutor;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static livelib.utils.PropertiesReader.getPropertyValue;

public class WebDriverDecorator implements WebDriver {

    private static final int EXPLICIT_WAIT = Integer.parseInt(getPropertyValue("EXPLICIT_WAIT"));
    private static final int IMPLICIT_WAIT = Integer.parseInt(getPropertyValue("IMPLICIT_WAIT"));
    private static final int CUSTOM_WAIT = 5;

    private JSExecutor jsExecutor;
    private WebDriver driver;

    public WebDriverDecorator(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementEnabled(WebElement element) {
        new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementEnabled(WebElement element, int timeOut) {
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementVisible(WebElement element) {
        new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementVisible(WebElement element, int timeOut) {
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForTitle(String title) {
        new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.titleIs(title));
    }

    public void waitForTextAppearedInElement(By locator, String text) {
        new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.textToBe(locator, text));
    }

    public boolean isChrome() {
        return driver instanceof ChromeDriver;
    }

    public boolean isElementPresent(WebElement element) {
        try {
            waitForElementEnabled(element);
        } catch (TimeoutException e) {
            LogManager.getLogger().error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean isElementPresent(By locator) {
        boolean flag;
        driver.manage().timeouts().implicitlyWait(CUSTOM_WAIT, TimeUnit.SECONDS);
        flag = driver.findElements(locator).size() > 0;
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        return flag;
    }

    public void waitHighlightClick(WebElement element) {
        waitForElementEnabled(element);
        jsExecutor = new JSExecutor(new WebDriverDecorator(driver));
        jsExecutor.scrollToElement(element);
        jsExecutor.highlightElement(element);
        element.click();
    }

    public void waitHighlightInput(WebElement element, String input) {
        waitForElementEnabled(element);
        jsExecutor = new JSExecutor(new WebDriverDecorator(driver));
        jsExecutor.scrollToElement(element);
        jsExecutor.highlightElement(element);
        element.clear();
        element.sendKeys(input);
    }

    public WebDriver getActualDriver() {
        return driver;
    }

    public void get(String s) {
        driver.get(s);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    public Navigation navigate() {
        return driver.navigate();
    }

    public Options manage() {
        return driver.manage();
    }
}