package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class BookItemPage extends BaseLiveLibPage {

    private static final String CLOSE_ADVERTISEMENT_XPATH = "//span[@class='i-hint-close']";
    private static final String BASE_CITE_NAME = "livelib";

    private String bookNameFromField;
    private String bookAuthorFromField;
    private String bookComment;

    @FindBy(xpath = "//span[@itemprop = 'name']")
    private WebElement nameOfTheBookField;

    @FindBy(id = "book-author")
    private WebElement nameOfTheAuthorField;

    @FindBy(xpath = "//span[@class = 'userbook-button userbook-main-menu']/span")
    private WebElement userBookMenuButton;

    @FindBy(xpath = "//div[@class='book-left-data']//span[@title='Хочу прочитать']")
    private WebElement bookMenuForm;

    @FindBy(xpath = "//div[@class = 'share']/table//div[@class = 'ub-menu']/a[contains(@href,'/review/create')]")
    private WebElement addBookReviewHref;

    @FindBy(xpath = "//a[@class = 'ub-add-comment']")
    private WebElement addBookCommentHref;

    @FindBy(xpath = "//textarea[contains(@id,'ub-notes')]")
    private WebElement addCommentTextField;

    @FindBy(xpath = "//td/div[@class='ub-menu-edit-area edit-note-area']/div[@class='edit-note-actions']/a[@class='btn-menu']")
    private WebElement saveCommentButton;

    @FindBy(xpath = "//a[contains(@id,'ub-status-wish')]")
    private WebElement addBookWLikeReadButton;

    @FindBy(xpath = "//a[contains(@id,'ub-status-read')]")
    private List<WebElement> checkboxesOnMenuBooks;

    @FindBy(xpath = CLOSE_ADVERTISEMENT_XPATH)
    private WebElement popupAdvertisement;
    @FindBy(xpath = "//span[@class='source-action']")
    private List<WebElement> shopLinks;

    @FindBy(xpath = "//a[@title='Добавить историю']")
    private WebElement addStoryButton;

    @FindBy(xpath = "//a[@title='Добавить в любимые']")
    private WebElement addToFavorites;

    @FindBy(xpath = "//div[@class = 'ub-menu mini-top-pad']//following::a[@class='ub-add-comment']/following::div[2]")
    private WebElement checkCommentField;

    @FindBy(xpath = " //div[@class ='eHeader_MiddleRow']")
    private WebElement ozonHeader;

    @FindBy(xpath = "//div[@class='userrating']//span[@title='Книга очень понравилась!']")
    private WebElement estimating;

    @FindBy(xpath = "//a[@title='Удалить из любимых']")
    private WebElement buttonDeleteFromFavourites;

    public BookItemPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
        closeAlert();
    }

    public boolean isAddingBookFormPresent() {
        LOGGER.debug("Checking is adding book form present");
        return bookMenuForm.isEnabled();
    }

    public boolean isBookNameMatch(String bookName) {
        bookNameFromField = nameOfTheBookField.getText();
        LOGGER.debug("Comparing current book name with " + bookName);
        return bookNameFromField.equals(bookName);
    }

    public boolean isBookAuthorMatch(String bookAuthor) {
        bookAuthorFromField = nameOfTheAuthorField.getText();
        LOGGER.debug("Comparing author of current book with " + bookAuthor);
        return bookAuthorFromField.equals(bookAuthor);
    }

    public BookItemPage getUserBookMenuForm() {
        webDriverDecorator.waitHighlightClick(userBookMenuButton);
        LOGGER.debug("Getting user menu form");
        return this;
    }

    public EditReviewPage addReview() {
        webDriverDecorator.waitHighlightClick(bookMenuForm);
        webDriverDecorator.waitHighlightClick(addBookReviewHref);
        LOGGER.debug("Adding review to the book has begun");
        return new EditReviewPage(webDriverDecorator);
    }

    public BookItemPage addComment(String comment) {
        webDriverDecorator.waitHighlightClick(bookMenuForm);
        webDriverDecorator.waitHighlightClick(addBookCommentHref);
        webDriverDecorator.waitHighlightInput(addCommentTextField, comment);
        webDriverDecorator.waitHighlightClick(saveCommentButton);
        LOGGER.debug("Comment " + comment + " was written");
        return this;
    }

    public BookItemPage addBookToWouldLikeToRead() {
        webDriverDecorator.waitHighlightClick(bookMenuForm);
        webDriverDecorator.waitHighlightClick(addBookWLikeReadButton);
        LOGGER.debug("Book was added to would like to read column");
        return this;
    }

    public BookItemPage addBookToFavorites() {
        webDriverDecorator.waitHighlightClick(addToFavorites);
        LOGGER.debug("Book was added to favorites");
        return this;
    }

    public BookItemPage addBookToHaveRead() {
        webDriverDecorator.waitHighlightClick(bookMenuForm);
        webDriverDecorator.waitHighlightClick(checkboxesOnMenuBooks.get(1));
        LOGGER.debug("Book was added to have read column");
        return this;
    }

    private BookItemPage closeAlert() {
        if (webDriverDecorator.isElementPresent(By.xpath(CLOSE_ADVERTISEMENT_XPATH))) {
            webDriverDecorator.waitHighlightClick(popupAdvertisement);
        }
        LOGGER.debug("Alert was closed");
        return this;
    }

    public BookItemPage navigateToShop(String shopName) {
        for (WebElement shop : shopLinks) {
            if (shop.getText().equalsIgnoreCase(shopName)) {
                webDriverDecorator.waitHighlightClick(shop);
                webDriverDecorator.switchTo().window(new ArrayList<>(webDriverDecorator.getWindowHandles()).get(1));
                webDriverDecorator.waitForElementVisible(ozonHeader);
                break;
            }
        }
        LOGGER.debug("Going to shop page");
        return this;
    }

    public boolean isTransferredFromLivelibToShop(String shopName) {
        String url = webDriverDecorator.getCurrentUrl();
        boolean result = url.toLowerCase().contains(shopName.toLowerCase()) && url.contains(BASE_CITE_NAME);
        webDriverDecorator.close();
        webDriverDecorator.switchTo().window(new ArrayList<>(webDriverDecorator.getWindowHandles()).get(0));
        LOGGER.debug("Checking is this page " + shopName + " was opened from livelib");
        return result;
    }

    public AddingStoryPage addStory() {
        webDriverDecorator.waitHighlightClick(bookMenuForm);
        webDriverDecorator.waitHighlightClick(addStoryButton);
        LOGGER.debug("Adding story to the book has begun");
        return new AddingStoryPage(webDriverDecorator);
    }

    public boolean isCommentOnPage(String comment) {
        bookComment = checkCommentField.getText();
        LOGGER.debug("Checking is comment" + comment + " on page");
        return bookComment.equals(comment);
    }

    public BookItemPage estimateBook() {
        webDriverDecorator.waitHighlightClick(estimating);
        LOGGER.debug("Book was estimated with 5 stars");
        return this;
    }

    public BookItemPage deleteFromFavourites() {
        webDriverDecorator.waitHighlightClick(buttonDeleteFromFavourites);
        LOGGER.debug("Book was deleted from favorites");
        return this;
    }
}

