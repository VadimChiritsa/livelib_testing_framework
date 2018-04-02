package cucumberTests.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class LoginSteps extends BaseSteps {

    @Given("^I click sign in button$")
    public void iEnterTheUsernameAndThePasswordAndClickLoginButton() {
        baseLiveLibPage.openSignInForm();
    }

    @And("^I click login button$")
    public void iClickLoginButton() {
        baseLiveLibPage.clickSubmitButton();
    }

    @Then("^I shouldn't see the user profile$")
    public void iShouldnTSeeTheUserformPage() {
        Assert.assertTrue(baseLiveLibPage.hasRegistrationLink());
    }

    @Then("^I should see the user profile$")
    public void iShouldSeeTheUserformPage() {
        Assert.assertTrue(baseLiveLibPage.hasUserMenu());
    }

    @And("^I close alert about incorrect data$")
    public void iCloseAlert() {
        baseLiveLibPage.acceptAlert();
    }

    @And("^I close sign in form$")
    public void iCloseSignInForm() {
        baseLiveLibPage.closeSignInForm();
    }

    @And("^I enter username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iEnterCorrectUsernameAsAndIncorrectPasswordAs(String username, String password) {
        baseLiveLibPage.fillCredentialsForSigningIn(username, password);
    }
}
