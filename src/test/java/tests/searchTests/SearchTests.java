package tests.searchTests;

import livelib.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import static livelib.utils.PropertiesReader.getPropertyValue;

public class SearchTests extends BaseTest {

    private String authorName;
    private String bookName;
    private String selectionsName;
    private String groupsName;

    @BeforeClass
    private void setTestVariablesFromProperties() {
        bookName = getPropertyValue("TEST_BOOK_NAME");
        authorName = getPropertyValue("TEST_BOOK_AUTHOR");
        selectionsName = getPropertyValue("TEST_SELECTIONS_NAME");
        groupsName = getPropertyValue("TEST_GROUPS_NAME");
    }

    @Test
    public void searchByAuthorOnAuthorPage() {
        AuthorPage authorPage = baseLiveLibPage
                .navigateToAuthorPage()
                .findInAuthorSearch(authorName);
        Assert.assertTrue(authorPage.isAuthorCorrect(authorName));
    }

    @Test
    public void searchByReviewOnReviewPage() {
        ReviewPage reviewPage = baseLiveLibPage
                .navigateToReviewPage()
                .findInReviewSearch(bookName);
        Assert.assertTrue(reviewPage.areReviewsExist(bookName));
    }

    @Test
    public void searchByQuotesOnQuotesPage() {
        QuotesPage quotesPage = baseLiveLibPage
                .navigateToQuotesPage()
                .findInQuotesSearch(bookName);
        Assert.assertTrue(quotesPage.areQuotesExist(bookName));
    }

    @Test
    public void searchBySelectionsOnSelectionsPage() {
        SelectionsPage selectionsPage = baseLiveLibPage
                .navigateToSelectionsPage()
                .findInSelectionsSearch(selectionsName);
        Assert.assertTrue(selectionsPage.selectionIsCorrect(selectionsName));
    }

    @Test
    public void searchByGroupsOnGroupsPage() {
        GroupsPage groupsPage = baseLiveLibPage
                .navigateToGroupsPage()
                .findInGroupsSearch(groupsName);
        Assert.assertTrue(groupsPage.groupIsCorrect(groupsName));
    }

    @Test
    public void mainSearchFieldTest() {
        GlobalSearchResultPage globalSearchResultPage = baseLiveLibPage.search(bookName);
        Assert.assertTrue(globalSearchResultPage.areBooksExist(bookName));
    }
}


