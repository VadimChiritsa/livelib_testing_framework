package livelib.browser_factory;

import org.openqa.selenium.opera.OperaDriver;

public class OperaBrowserManager extends BrowserManager {
    @Override
    protected void createDriver() {
        driver = new OperaDriver();
        LOGGER.debug("OperaDriver was created");
    }

    @Override
    public void quitDriver() {
        super.quitDriver();
        LOGGER.debug("OperaDriver was quited");
    }

    @Override
    protected void setupDriver() {
        super.setupDriver();
        LOGGER.debug("OperaDriver was configured");
    }
}
