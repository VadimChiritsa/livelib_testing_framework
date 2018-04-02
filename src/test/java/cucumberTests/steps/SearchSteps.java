package cucumberTests.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import livelib.pages.*;
import org.testng.Assert;

import static livelib.utils.PropertiesReader.getPropertyValue;

public class SearchSteps extends BaseSteps {
    private ReviewPage reviewPage;
    private String bookName;
    private String groupName;
    private String selectionsName;
    private GlobalSearchResultPage globalSearchResultPage;
    private AuthorPage authorPage;
    private QuotesPage quotesPage;
    private GroupsPage groupsPage;
    private SelectionsPage selectionsPage;

    @Given("^I get book from properties$")
    public void iGetBookFromProperties() {
        bookName = getPropertyValue("TEST_BOOK_NAME");
    }

    @When("^I input book to the global search field$")
    public void iInputBookToGlobalSearchField() {
        globalSearchResultPage = baseLiveLibPage.search(bookName);
    }

    @Then("^I should see book on the page$")
    public void iShouldSeeBookOnThePage() {
        Assert.assertTrue(globalSearchResultPage.areBooksExist(bookName));
    }

    @Given("^I navigate to the author page$")
    public void iNavigateToAuthorPage() {
        authorPage = baseLiveLibPage.navigateToAuthorPage();
    }

    @When("^I input \"([^\"]*)\" to the search field by author$")
    public void iInputToTheSearchField(String authorName) {
        authorPage.findInAuthorSearch(authorName);
    }

    @Then("^I should see \"([^\"]*)\" on the author page$")
    public void iShouldSeeOnThePage(String authorName) {
        Assert.assertTrue(authorPage.isAuthorCorrect(authorName));
    }

    @Given("^I navigate to the review page$")
    public void iNavigateToTheReviewPage() {
        reviewPage = baseLiveLibPage.navigateToReviewPage();
    }

    @When("^I input \"([^\"]*)\" to the search field by review$")
    public void iInputToTheSearchFieldByReview(String authorName) {
        reviewPage.findInReviewSearch(authorName);
    }

    @Then("^I should see \"([^\"]*)\" on the review page$")
    public void iShouldSeeOnTheReviewPage(String authorName) {
        Assert.assertTrue(reviewPage.areReviewsExist(authorName));
    }

    @Given("^I get book name from properties$")
    public void iGetBookName() {
        bookName = getPropertyValue("TEST_BOOK_NAME");
    }

    @And("^I go to quotes page$")
    public void iGoToQuotesPage() {
        quotesPage = baseLiveLibPage.navigateToQuotesPage();
    }

    @When("^I enter into the search field book$")
    public void iInputBookName() {
        quotesPage.findInQuotesSearch(bookName);
    }

    @Then("^I see all quotes connected to this book om the page$")
    public void iSeeQoutesOnThePage() {
        Assert.assertTrue(quotesPage.areQuotesExist(bookName));
    }

    @Given("^I get group name from properties$")
    public void iGetGroupName() {
        groupName = getPropertyValue("TEST_GROUPS_NAME");
    }

    @And("^I go to groups page$")
    public void iGoToGroupsPage() {
        groupsPage = baseLiveLibPage.navigateToGroupsPage();
    }

    @When("^I enter into the search field group$")
    public void iInputGroupName() {
        groupsPage.findInGroupsSearch(groupName);
    }

    @Then("^I see all groups connected to this book om the page$")
    public void iSeeGroupsOnThePage() {
        Assert.assertTrue(groupsPage.groupIsCorrect(groupName));
    }

    @Given("^I get selection name from properties$")
    public void iGetSelectionName() {
        selectionsName = getPropertyValue("TEST_SELECTIONS_NAME");
    }

    @And("^I go to selections page$")
    public void iGoToSelectionsPage() {
        selectionsPage = baseLiveLibPage.navigateToSelectionsPage();
    }

    @When("^I enter into the search field selection$")
    public void iInputSelectionName() {
        selectionsPage.findInSelectionsSearch(selectionsName);
    }

    @Then("^I see all selections connected to this book om the page$")
    public void iSeeSelectionsOnThePage() {
        Assert.assertTrue(selectionsPage.selectionIsCorrect(selectionsName));
    }
}
