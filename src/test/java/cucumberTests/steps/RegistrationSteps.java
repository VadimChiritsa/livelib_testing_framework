package cucumberTests.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import livelib.utils.Utils;
import org.testng.Assert;

import static livelib.utils.PropertiesReader.getPropertyValue;

public class RegistrationSteps extends BaseSteps {
    private static final String DOMAIN_NAME = "@gmail.com";
    private static final String INCORRECT_EMAIL = "login";
    private static final int MIN_RANDOM_INT = 0;
    private static final int MAX_RANDOM_INT = 10 * 1000;
    private static final int NUMBER_OF_PARTS = 3;

    private String registrationLogin;
    private String registrationPassword;

    @And("^I click registration button$")
    public void iClickRegistrationButton() {
        baseLiveLibPage.openRegistrationForm();
    }

    @And("^I enter email and password$")
    public void iEnterEmailAndPassword() {
        baseLiveLibPage.fillCredentialsForRegistration(registrationLogin, registrationPassword);
    }

    @And("^I click register button$")
    public void iClickRegisterButton() {
        baseLiveLibPage.clickRegisterButton();
    }

    @And("^I close registration form$")
    public void iCloseRegistrationForm() {
        baseLiveLibPage.closeSignInForm();
    }

    @Then("^I shouldn't see user menu or email popup$")
    public void iShouldnTSeeUserMenuOrEmailPopup() {
        Assert.assertTrue(baseLiveLibPage.hasRegistrationLink());
    }

    @Then("^I should see user menu or email popup$")
    public void iShouldSeeUserMenuOrEmailPopup() {
        Assert.assertTrue(baseLiveLibPage.conditionForRegistrationTest());
    }

    @And("^I enter incorrect email as \"([^\"]*)\" and password$")
    public void iEnterIncorrectEmailAndPassword(String login) {
        baseLiveLibPage.fillCredentialsForRegistration(login, registrationPassword);
    }

    @Given("^I get password from properties$")
    public void iGetPasswordFromProperties() {
        registrationPassword = getPropertyValue("PASSWORD_FOR_TEST_REGISTRATION");
    }

    @And("^I generate random email$")
    public void iGenerateRandomEmail() {
        registrationLogin = Utils.generateNameForRegistration(NUMBER_OF_PARTS, DOMAIN_NAME, MAX_RANDOM_INT, MIN_RANDOM_INT);
    }

    @And("^I close alert$")
    public void iCloseAlert() {
        baseLiveLibPage.acceptAlert();
    }
}
