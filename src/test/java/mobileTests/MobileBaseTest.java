package mobileTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static livelib.utils.PropertiesReader.getPropertyValue;

public class MobileBaseTest {
    protected static final Logger LOGGER = LogManager.getLogger(MobileBaseTest.class.getName());
    private static final int IMPLICIT_WAIT = Integer.parseInt(getPropertyValue("IMPLICIT_WAIT"));

    protected AppiumDriver<MobileElement> driver;

    @BeforeSuite
    public void setUp() {
        LOGGER.debug("Starting appium from IntelliJ IDEA as NodeJS module");
        AppiumServiceBuilder builder = new AppiumServiceBuilder().
                withAppiumJS(new File(System.getenv("PATH_TO_CUSTOM_NODE")))
                .usingPort(Integer.parseInt(getPropertyValue("APPIUM_PORT")));
        builder.build();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", getPropertyValue("DEVICE_NAME"));
        capabilities.setCapability("appPackage", getPropertyValue("APP_PACKAGE"));
        capabilities.setCapability("appActivity", getPropertyValue("APP_ACTIVITY"));
        driver = new AndroidDriver<>(builder, capabilities);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        LOGGER.debug("Android driver configured");
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}