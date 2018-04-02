package tests;

import livelib.browser_factory.BrowserManager;
import livelib.browser_factory.BrowserManagerFactory;
import livelib.browser_factory.BrowserName;
import livelib.decorator.WebDriverDecorator;
import livelib.pages.BaseLiveLibPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static livelib.utils.PropertiesReader.getPropertyValue;

public abstract class BaseTest {
    private static final String TEST_USER_LOGIN;
    private static final String TEST_USER_PASSWORD;

    static {
        TEST_USER_LOGIN = getPropertyValue("LOGIN");
        TEST_USER_PASSWORD = getPropertyValue("PASSWORD");
    }

    protected WebDriverDecorator webDriverDecorator;
    protected BaseLiveLibPage baseLiveLibPage;
    private BrowserManager manager;

    protected static String getTestUserLogin() {
        return TEST_USER_LOGIN;
    }

    protected static String getTestUserPassword() {
        return TEST_USER_PASSWORD;
    }

    @BeforeClass
    @Parameters("browser")
    public void loadState(BrowserName browserName) {
        manager = BrowserManagerFactory.getManager(browserName);
        webDriverDecorator = new WebDriverDecorator(manager.getDriver());
        baseLiveLibPage = new BaseLiveLibPage(webDriverDecorator);
        baseLiveLibPage.open();
        baseLiveLibPage.closeStartPopup();
    }

    @AfterClass
    public void afterClass() {
        manager.quitDriver();
    }
}
