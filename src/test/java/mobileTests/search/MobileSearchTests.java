package mobileTests.search;

import mobileTests.MobileBaseTest;
import mobileTests.pages.AuthorMobilePage;
import mobileTests.pages.MobileMainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static livelib.utils.PropertiesReader.getPropertyValue;

public class MobileSearchTests extends MobileBaseTest {

    MobileMainPage mobileMainPage;
    AuthorMobilePage authorMobilePage;
    private String bookName;
    private String authorName;
    private String actualAuthorName;

    @BeforeClass
    private void setTestVariablesFromProperties() {
        bookName = getPropertyValue("TEST_BOOK_NAME");
        authorName = getPropertyValue("TEST_BOOK_AUTHOR");
        actualAuthorName = getPropertyValue("MOBILE_AUTHOR_TEST");
    }

    @Test
    public void searchBookTest() {
        mobileMainPage = new MobileMainPage(driver)
                .searchBookByName(bookName);
        Assert.assertTrue(mobileMainPage.isBookExisted(bookName));
    }


    @Test(dependsOnMethods = "searchBookTest")
    public void searchAuthorTest() {
        authorMobilePage = mobileMainPage
                .searchAuthorByName(authorName);
        Assert.assertTrue(authorMobilePage.isAuthorExist(actualAuthorName));
    }
}