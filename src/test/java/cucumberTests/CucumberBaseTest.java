package cucumberTests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import livelib.browser_factory.BrowserManager;
import livelib.browser_factory.BrowserManagerFactory;
import livelib.browser_factory.BrowserName;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

@CucumberOptions(strict = true,
        plugin = {"json:target/cucumber-report.json", "html:target/cucumber-report"},
        features = {"src/test/java/cucumberTests/features/Search.feature",
                "src/test/java/cucumberTests/features/Login.feature",
                "src/test/java/cucumberTests/features/Registration.feature"},
        glue = {"cucumberTests.steps"})
public class CucumberBaseTest extends AbstractTestNGCucumberTests {

    public static BrowserManager manager;

    @BeforeMethod
    @Parameters("browser")
    public void loadState(BrowserName browserName) {
        manager = BrowserManagerFactory.getManager(browserName);
    }

    @AfterMethod
    public void afterMethod() {
        manager.quitDriver();
    }
}
