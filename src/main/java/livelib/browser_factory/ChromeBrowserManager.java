package livelib.browser_factory;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowserManager extends BrowserManager {
    @Override
    protected void createDriver() {
        driver = new ChromeDriver();
        LOGGER.debug("ChromeDriver was created");
    }

    @Override
    public void quitDriver() {
        super.quitDriver();
        LOGGER.debug("ChromeDriver was quited");
    }

    @Override
    protected void setupDriver() {
        super.setupDriver();
        LOGGER.debug("ChromeDriver was configured");
    }
}
