package tests.header;

import livelib.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;

public class HeaderTests extends BaseTest {

    private static final String DATA_URL_BOOKS = "https://www.livelib.ru/books";
    private static final String DATA_URL_GENRES = "https://www.livelib.ru/genres";
    private static final String DATA_URL_AUTHORS = "https://www.livelib.ru/authors";
    private static final String DATA_URL_REVIEWS = "https://www.livelib.ru/reviews";
    private static final String DATA_URL_QUOTES = "https://www.livelib.ru/quotes";
    private static final String DATA_URL_SELECTIONS = "https://www.livelib.ru/selections";
    private static final String DATA_URL_GROUPS = "https://www.livelib.ru/groups";
    private static final String DATA_WHAT_TO_READ = "Что почитать?";
    private static final String DATA_POPULAR_BOOKS = "Популярные книги";
    private static final String DATA_ALL_GENRES = "Все жанры";
    private static final String DATA_AUTHORS = "Авторы";
    private static final String DATA_REVIEWS_ON_BOOKS = "Рецензии на книги";
    private static final String DATA_QUOTES_FROM_BOOKS = "Цитаты из книг";
    private static final String DATA_BEST_SELECTIONS = "Лучшие подборки за месяц";
    private static final String DATA_GROUPS = "Группы";
    private static final String DATA_PARTICULAR_GENRE = "Эротика и секс";

    @Test
    public void openBooksPageTest() {
        BooksPage booksPage = baseLiveLibPage.navigateToBooksPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(booksPage.isExpectedPage(DATA_URL_BOOKS));
        softAssert.assertEquals(booksPage.getHeadWhatToReadText(), DATA_WHAT_TO_READ);
        softAssert.assertTrue(booksPage.isElementExistedOnSubMenu(DATA_POPULAR_BOOKS));
        softAssert.assertAll();
    }

    @Test
    public void openGenresPageTest() {
        GenresPage genresPage = baseLiveLibPage.navigateToGenresPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(genresPage.isExpectedPage(DATA_URL_GENRES));
        softAssert.assertEquals(genresPage.getHeadAllGenresText(), DATA_ALL_GENRES);
        softAssert.assertTrue(genresPage.isGenreExistedOnPage(DATA_PARTICULAR_GENRE));
        softAssert.assertAll();
    }

    @Test
    public void openAuthorsPageTest() {
        AuthorPage authorPage = baseLiveLibPage.navigateToAuthorPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(authorPage.isExpectedPage(DATA_URL_AUTHORS));
        softAssert.assertEquals(authorPage.getHeadAuthorsText(), DATA_AUTHORS);
        softAssert.assertAll();
    }

    @Test
    public void openReviewPageTest() {
        ReviewPage reviewPage = baseLiveLibPage.navigateToReviewPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(reviewPage.isExpectedPage(DATA_URL_REVIEWS));
        softAssert.assertEquals(reviewPage.getHeadReviewsOnBooksText(), DATA_REVIEWS_ON_BOOKS);
        softAssert.assertAll();
    }

    @Test
    public void openQuotesPageTest() {
        QuotesPage quotesPage = baseLiveLibPage.navigateToQuotesPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(quotesPage.isExpectedPage(DATA_URL_QUOTES));
        softAssert.assertEquals(quotesPage.getHeadQuotesFromBooksText(), DATA_QUOTES_FROM_BOOKS);
        softAssert.assertAll();
    }

    @Test
    public void openSelectionsPageTest() {
        SelectionsPage selectionsPage = baseLiveLibPage.navigateToSelectionsPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(selectionsPage.isExpectedPage(DATA_URL_SELECTIONS));
        softAssert.assertEquals(selectionsPage.getHeadBestSelectionsText(), DATA_BEST_SELECTIONS);
        softAssert.assertAll();
    }

    @Test
    public void openGroupsPageTest() {
        GroupsPage groupsPage = baseLiveLibPage.navigateToGroupsPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(groupsPage.isExpectedPage(DATA_URL_GROUPS));
        softAssert.assertEquals(groupsPage.getHeadGroupsText(), DATA_GROUPS);
        softAssert.assertAll();
    }

    @Test
    public void openDropDownListTest() {
        baseLiveLibPage.clickDropDownListOnHeader();
        Assert.assertTrue(baseLiveLibPage.dropDownListIsAppeared());
    }
}
