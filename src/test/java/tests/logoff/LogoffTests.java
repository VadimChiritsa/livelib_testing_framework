package tests.logoff;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class LogoffTests extends BaseTest {

    @Test
    public void successfulLogoffTest() {
        baseLiveLibPage
                .signIn(getTestUserLogin(), getTestUserPassword())
                .logOut();
        Assert.assertTrue(baseLiveLibPage.hasRegistrationLink());
    }
}
