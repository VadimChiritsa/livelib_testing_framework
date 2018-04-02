package cucumberTests.steps;

import cucumberTests.CucumberBaseTest;
import livelib.decorator.WebDriverDecorator;
import livelib.pages.BaseLiveLibPage;

import static livelib.utils.PropertiesReader.getPropertyValue;

public class BaseSteps {

    private static final String TEST_USER_LOGIN;
    private static final String TEST_USER_PASSWORD;

    static {
        TEST_USER_LOGIN = getPropertyValue("LOGIN");
        TEST_USER_PASSWORD = getPropertyValue("PASSWORD");
    }

    protected WebDriverDecorator webDriverDecorator;
    protected BaseLiveLibPage baseLiveLibPage;

    public BaseSteps() {
        webDriverDecorator = new WebDriverDecorator(CucumberBaseTest.manager.getDriver());
        baseLiveLibPage = new BaseLiveLibPage(webDriverDecorator);
        baseLiveLibPage.open();
    }

    public static String getTestUserLogin() {
        return TEST_USER_LOGIN;
    }

    public static String getTestUserPassword() {
        return TEST_USER_PASSWORD;
    }
}
