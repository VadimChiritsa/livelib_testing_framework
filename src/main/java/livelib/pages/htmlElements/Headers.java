package livelib.pages.htmlElements;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

@FindBy(xpath = "//div[@class = 'menu-main']")
public class Headers extends HtmlElement {

    private static final int TIMEOUT_IN_SECONDS = 5;
    private WebDriverDecorator webDriverDecorator;
    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//div[@class='header-container']//a[@href='/books']")
    private Button booksTab;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//li[@id='genre-item']/a")
    private Button genreTab;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//a[@href = '/genres']")
    private Link allGenresLink;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//div[@class='header-container']//a[@href='/authors']")
    private Button authorsTab;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//div[@class='header-container']//a[@href='/reviews']")
    private Button reviewsTab;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//div[@class='header-container']//a[@href='/quotes']")
    private Button quotesTab;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//div[@class='header-container']//a[@href='/selections']")
    private Button selectionsTab;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//div[@class='header-container']//a[@href='/groups']")
    private Button groupsTab;

    public void setWebDriverDecorator(WebDriverDecorator webDriverDecorator) {
        this.webDriverDecorator = webDriverDecorator;
    }

    public void clickBooksTab() {
        webDriverDecorator.waitHighlightClick(booksTab);
    }

    public void clickGenresTab() {
        webDriverDecorator.waitHighlightClick(genreTab);
    }

    public void clickAllGenresLink() {
        webDriverDecorator.waitHighlightClick(allGenresLink);
    }

    public void clickAuthorsTab() {
        webDriverDecorator.waitHighlightClick(authorsTab);
    }

    public void clickReviewTab() {
        webDriverDecorator.waitHighlightClick(reviewsTab);
    }

    public void clickQuotesTab() {
        webDriverDecorator.waitHighlightClick(quotesTab);
    }

    public void clickSelectionsTab() {
        webDriverDecorator.waitHighlightClick(selectionsTab);
    }

    public void clickGroupsTab() {
        webDriverDecorator.waitHighlightClick(groupsTab);
    }
}
