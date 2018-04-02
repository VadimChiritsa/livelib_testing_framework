package tests.books;

import livelib.pages.*;
import livelib.utils.StorageBooks;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import static livelib.utils.PropertiesReader.getPropertyValue;

public class BookTests extends BaseTest {

    private MyStoriesPage myStoriesPage;
    private WantToReadPage wantToReadPage;
    private MyFavouritesPage myFavouritesPage;
    private MyDraftsPage myDraftsPage;
    private MyReadBooksPage myReadBooksPage;
    private MyReviewsPage reviewPage;
    private BookItemPage bookItemPage;
    private String bookName;
    private String commentText;
    private String shopName;
    private String storyTitle;
    private String storyDescription;
    private String reviewTitle;
    private String reviewText;

    @BeforeClass(alwaysRun = true)
    public void setValuesFromProperties() {
        commentText = getPropertyValue("TEST_COMMENT_TEXT");
        shopName = getPropertyValue("SHOP_NAME");
        storyTitle = getPropertyValue("STORY_TITLE");
        storyDescription = getPropertyValue("STORY_DESCRIPTION");
        reviewTitle = getPropertyValue("TEST_REVIEW_TITLE");
        reviewText = getPropertyValue("TEST_REVIEW_TEXT");
        baseLiveLibPage = baseLiveLibPage.signIn(getTestUserLogin(), getTestUserPassword());
    }

    @Test(alwaysRun = true)
    public void addBookToFavorites() {
        bookName = StorageBooks.getRandomBook();
        myFavouritesPage = baseLiveLibPage
                .search(bookName)
                .openBookItem(bookName)
                .addBookToFavorites()
                .navigateToMyReadBooksPage()
                .navigateToMyFavouritesPage();
        Assert.assertTrue(myFavouritesPage.isBookExist(bookName));
    }

    @Test(alwaysRun = true, dependsOnMethods = "addBookToFavorites")
    public void addBookToWouldLikeToReadTest() {
        bookName = StorageBooks.getRandomBook();
        wantToReadPage = baseLiveLibPage
                .search(bookName)
                .openBookItem(bookName)
                .addBookToWouldLikeToRead()
                .navigateToMyReadBooksPage()
                .navigateToWantToReadPage();
        Assert.assertTrue(wantToReadPage.isBookOnPage(bookName));
    }

    @Test(alwaysRun = true, dependsOnMethods = "addBookToWouldLikeToReadTest")
    public void addCommentToTheBook() {
        bookName = StorageBooks.getRandomBook();
        bookItemPage = baseLiveLibPage
                .search(bookName)
                .openBookItem(bookName)
                .addComment(commentText);
        Assert.assertTrue(bookItemPage.isCommentOnPage(commentText));
    }

    @Test(alwaysRun = true, dependsOnMethods = "addCommentToTheBook")
    public void writeStoryTest() {
        bookName = StorageBooks.getRandomBook();
        myStoriesPage = baseLiveLibPage
                .search(bookName)
                .openBookItem(bookName)
                .addStory()
                .writeStory(storyTitle, storyDescription)
                .navigateToMyReadBooksPage()
                .navigateToMyStoriesPage();
        Assert.assertTrue(myStoriesPage.isStoryExist(storyTitle, storyDescription));
    }

    @Test(alwaysRun = true, dependsOnMethods = "writeStoryTest")
    public void buyBookTest() {
        bookName = StorageBooks.getRandomBook();
        bookItemPage = baseLiveLibPage
                .search(bookName)
                .openBookItem(bookName)
                .navigateToShop(shopName);
        Assert.assertTrue(bookItemPage.isTransferredFromLivelibToShop(shopName));
    }

    @Test(alwaysRun = true, dependsOnMethods = "buyBookTest")
    public void writeReviewTest() {
        bookName = StorageBooks.getRandomBook();
        reviewPage = baseLiveLibPage
                .search(bookName)
                .openBookItem(bookName)
                .addReview()
                .fillAndSaveReview(reviewTitle, reviewText)
                .navigateToMyReadBooksPage()
                .navigateToMyReviewsPage();
        Assert.assertTrue(reviewPage.isReviewExist(reviewTitle, reviewText));
    }

    @Test(alwaysRun = true, dependsOnMethods = "writeReviewTest")
    public void estimateBookTest() {
        bookName = StorageBooks.getRandomBook();
        myReadBooksPage = baseLiveLibPage
                .search(bookName)
                .openBookItem(bookName)
                .estimateBook()
                .navigateToMyReadBooksPage();
        Assert.assertTrue(myReadBooksPage.isBookExist(bookName));
    }

    @Test(alwaysRun = true, dependsOnMethods = "estimateBookTest")
    public void addBookToHaveReadTest() {
        bookName = StorageBooks.getRandomBook();
        myReadBooksPage = baseLiveLibPage
                .search(bookName)
                .openBookItem(bookName)
                .addBookToHaveRead()
                .navigateToMyReadBooksPage();
        Assert.assertTrue(myReadBooksPage.isBookExist(bookName));
    }

    @Test(alwaysRun = true, dependsOnMethods = "addBookToHaveReadTest")
    public void addDraftTest() {
        bookName = StorageBooks.getRandomBook();
        myDraftsPage = baseLiveLibPage
                .search(bookName)
                .openBookItem(bookName)
                .addReview()
                .fillTitleAndTextReview(reviewTitle, reviewText)
                .addToDrafts()
                .navigateToMyReadBooksPage()
                .navigateToMyDraftsPage();
        Assert.assertTrue(myDraftsPage.isDraftReviewExist());
    }

    @AfterClass(alwaysRun = true)
    public void clean() {
        baseLiveLibPage
                .navigateToMyReadBooksPage()
                .navigateToMyDraftsPage()
                .deleteDraft()
                .navigateToMyReadBooksPage()
                .navigateToMyReviewsPage()
                .editReview()
                .deleteReview()
                .navigateToMyReadBooksPage()
                .navigateToMyFavouritesPage()
                .chooseFavouriteBook()
                .deleteFromFavourites()
                .navigateToMyReadBooksPage()
                .navigateToWantToReadPage()
                .deleteBookFromWantToRead()
                .navigateToMyReadBooksPage()
                .deleteBooksFromMyReadBooks()
                .navigateToMyStoriesPage()
                .editStory()
                .deleteStory();
    }
}

