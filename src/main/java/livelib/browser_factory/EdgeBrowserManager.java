package livelib.browser_factory;

import org.openqa.selenium.edge.EdgeDriver;

public class EdgeBrowserManager extends BrowserManager {
    @Override
    protected void createDriver() {
        driver = new EdgeDriver();
        LOGGER.debug("EdgeDriver was created");
    }

    @Override
    public void quitDriver() {
        super.quitDriver();
        LOGGER.debug("EdgeDriver was quited");
    }

    @Override
    protected void setupDriver() {
        super.setupDriver();
        LOGGER.debug("EdgeDriver was configured");
    }
}
