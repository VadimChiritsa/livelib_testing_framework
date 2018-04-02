package livelib.pages.htmlElements;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Header container for MyBooks")
@FindBy(xpath = "//div[@class='header-container']")
public class MyBooksHeader extends HtmlElement {

    private static final int TIMEOUT_IN_SECONDS = 5;
    private WebDriverDecorator webDriverDecorator;
    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//ul[@id='menu-container']//a[contains(@href,'/drafts')]")
    private WebElement buttonDrafts;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//ul[@id='menu-container']//a[contains(@href,'/read')]")
    private WebElement buttonReadBooks;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//ul[@id='menu-container']//a[contains(@href,'/wish')]")
    private WebElement buttonWantToRead;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//ul[@id='menu-container']//a[contains(@href,'/unread')]")
    private WebElement buttonUnreadBooks;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//ul[@id='menu-container']//a[contains(@href,'/favorites')]")
    private WebElement buttonFavorites;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//ul[@id='menu-container']//a[contains(@href,'/reviews')]")
    private WebElement buttonReviews;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//ul[@id='menu-container']//a[contains(@href,'/stories')]")
    private WebElement buttonStories;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//ul[@id='menu-container']//a[contains(@href,'/quotes')]")
    private WebElement buttonQuotes;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//span[@class='i-gmore']")
    private WebElement moreButton;

    public void setWebDriverDecorator(WebDriverDecorator webDriverDecorator) {
        this.webDriverDecorator = webDriverDecorator;
    }

    public void navigateToDrafts() {
        webDriverDecorator.waitHighlightClick(moreButton);
        webDriverDecorator.waitHighlightClick(buttonDrafts);
    }

    public void navigateToReadBooks() {
        webDriverDecorator.waitHighlightClick(buttonReadBooks);
    }

    public void navigateToWantToReadBooks() {
        webDriverDecorator.waitHighlightClick(buttonWantToRead);
    }

    public void navigateToUnreadBooks() {
        webDriverDecorator.waitHighlightClick(buttonUnreadBooks);
    }

    public void navigateToFavourites() {
        webDriverDecorator.waitHighlightClick(buttonFavorites);
    }

    public void navigateToMyReviews() {
        webDriverDecorator.waitHighlightClick(buttonReviews);
    }

    public void navigateToMyStories() {
        webDriverDecorator.waitHighlightClick(buttonStories);
    }

    public void navigateToMyQuotes() {
        webDriverDecorator.waitHighlightClick(buttonQuotes);
    }
}