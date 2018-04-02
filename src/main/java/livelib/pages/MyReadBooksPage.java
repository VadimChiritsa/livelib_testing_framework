package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import livelib.pages.htmlElements.MyBooksHeader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MyReadBooksPage extends BaseLiveLibPage {

    private static final String ACTION = "delete";

    private MyBooksHeader booksHeader;

    @FindBy(xpath = "//a[@class='brow-book-name with-cycle']")
    private List<WebElement> listBooks;

    @FindBy(xpath = "//a[@class='my-book-list-a menu-item']")
    private List<WebElement> checkboxBooks;

    @FindBy(id = "my-book-list-select")
    private WebElement dropDownActions;

    @FindBy(id = "my-book-list-button")
    private WebElement buttonEnter;

    @FindBy(xpath = "//span[@class='i-clear']")
    private WebElement popupClose;

    public MyReadBooksPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
        booksHeader.setWebDriverDecorator(webDriverDecorator);
    }

    public boolean isBookExist(final String bookName) {
        LOGGER.debug("Checking is book with name " + bookName + " exists on page");
        return listBooks.size() > 0 && listBooks.stream().anyMatch(item -> item.getText().equalsIgnoreCase(bookName));
    }

    public WantToReadPage navigateToWantToReadPage() {
        booksHeader.navigateToWantToReadBooks();
        LOGGER.debug("Navigated to want to read page");
        return new WantToReadPage(webDriverDecorator);
    }

    public MyStoriesPage navigateToMyStoriesPage() {
        booksHeader.navigateToMyStories();
        LOGGER.debug("Navigated to my stories page");
        return new MyStoriesPage(webDriverDecorator);
    }

    public MyFavouritesPage navigateToMyFavouritesPage() {
        booksHeader.navigateToFavourites();
        LOGGER.debug("Navigated to my favorites page");
        return new MyFavouritesPage(webDriverDecorator);
    }

    public MyReviewsPage navigateToMyReviewsPage() {
        booksHeader.navigateToMyReviews();
        LOGGER.debug("Navigated to my reviews page");
        return new MyReviewsPage(webDriverDecorator);
    }

    public MyDraftsPage navigateToMyDraftsPage() {
        booksHeader.navigateToDrafts();
        LOGGER.debug("Navigated to my drafts page");
        return new MyDraftsPage(webDriverDecorator);
    }

    public MyReadBooksPage deleteBooksFromMyReadBooks() {
        for (WebElement checkbox : checkboxBooks) {
            webDriverDecorator.waitHighlightClick(checkbox);
        }
        Select dropDown = new Select(dropDownActions);
        dropDown.selectByValue(ACTION);
        webDriverDecorator.waitHighlightClick(buttonEnter);
        webDriverDecorator.switchTo().alert().accept();
        webDriverDecorator.waitHighlightClick(popupClose);
        LOGGER.debug("Books from my read books page were deleted");
        return this;
    }
}
