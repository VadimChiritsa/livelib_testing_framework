package livelib.browser_factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static livelib.utils.PropertiesReader.getPropertyValue;

public abstract class BrowserManager {

    protected static final Logger LOGGER = LogManager.getLogger(BrowserManager.class.getName());
    private static final int IMPLICIT_WAIT = Integer.parseInt(getPropertyValue("IMPLICIT_WAIT"));

    protected WebDriver driver;

    protected abstract void createDriver();

    public WebDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        setupDriver();
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected void setupDriver() {
        driver.manage().timeouts().pageLoadTimeout(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

}
