package tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class LoginTests extends BaseTest {

    private static final String INCORRECT_LOGIN = "name";
    private static final String INCORRECT_PASSWORD = "password";

    @Test
    public void enterIncorrectLoginTest() {
        baseLiveLibPage
                .signInForLoginTests(INCORRECT_LOGIN, getTestUserPassword())
                .acceptAlert()
                .closeSignInForm();
        Assert.assertTrue(baseLiveLibPage.hasRegistrationLink());
    }

    @Test
    public void enterIncorrectPasswordTest() {
        baseLiveLibPage
                .signInForLoginTests(getTestUserLogin(), INCORRECT_PASSWORD)
                .acceptAlert()
                .closeSignInForm();
        Assert.assertTrue(baseLiveLibPage.hasRegistrationLink());
    }

    @Test(dependsOnMethods = "enterIncorrectPasswordTest")
    public void enterCorrectCredentialsTest() {
        baseLiveLibPage.signInForLoginTests(getTestUserLogin(), getTestUserPassword());
        Assert.assertTrue(baseLiveLibPage.hasUserMenu());
    }
}
