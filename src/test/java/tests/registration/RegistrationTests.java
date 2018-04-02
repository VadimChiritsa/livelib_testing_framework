package tests.registration;

import livelib.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import static livelib.utils.PropertiesReader.getPropertyValue;

public class RegistrationTests extends BaseTest {

    private static final String DOMAIN_NAME = "@gmail.com";
    private static final String INCORRECT_EMAIL = "login";
    private static final int MIN_RANDOM_INT = 0;
    private static final int MAX_RANDOM_INT = 10 * 1000;
    private static final int NUMBER_OF_PARTS = 3;

    private String registrationLogin;
    private String registrationPassword;

    @BeforeClass
    public void setLoginAndPasswordFromProperties() {
        registrationLogin = Utils.generateNameForRegistration(NUMBER_OF_PARTS, DOMAIN_NAME, MAX_RANDOM_INT, MIN_RANDOM_INT);
        registrationPassword = getPropertyValue("PASSWORD_FOR_TEST_REGISTRATION");
    }

    @Test
    public void incorrectEmailForRegistrationTest() {
        baseLiveLibPage
                .fillAndSendRegistrationData(INCORRECT_EMAIL, registrationPassword)
                .acceptAlert()
                .closeSignInForm();
        Assert.assertTrue(baseLiveLibPage.hasRegistrationLink());
    }

    @Test(dependsOnMethods = "incorrectEmailForRegistrationTest")
    public void successfulRegistrationTest() {
        baseLiveLibPage.fillAndSendRegistrationData(registrationLogin, registrationPassword);
        Assert.assertTrue(baseLiveLibPage.conditionForRegistrationTest());
    }
}
