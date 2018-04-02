package livelib.browser_factory;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowserManager extends BrowserManager {
    @Override
    protected void createDriver() {
        driver = new FirefoxDriver();
        LOGGER.debug("FirefoxDriver was created");
    }

    @Override
    public void quitDriver() {
        super.quitDriver();
        LOGGER.debug("FirefoxDriver was quited");
    }

    @Override
    protected void setupDriver() {
        super.setupDriver();
        LOGGER.debug("FirefoxDriver was configured");
    }
}
