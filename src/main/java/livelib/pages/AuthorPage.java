package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorPage extends BaseLiveLibPage {

    private static final String TITLE = "Авторы";

    @FindBy(xpath = "//div[@class='header-container']//a[@href='/authors/popular']")
    private WebElement popularBooks;
    @FindBy(id = "input-search-book")
    private WebElement inputSearch;
    @FindBy(id = "btn-search-new")
    private WebElement button_search;
    @FindBy(xpath = "//div[@class='event-author']//span[@class='author-original']")
    private WebElement authorNameText;
    @FindBy(xpath = "//div[@class='block']//h1")
    private WebElement authorsText;

    public AuthorPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
        webDriverDecorator.waitForTitle(TITLE);
    }

    public AuthorPage findInAuthorSearch(String input) {
        webDriverDecorator.waitForElementEnabled(popularBooks);
        webDriverDecorator.waitHighlightInput(inputSearch, input);
        webDriverDecorator.waitHighlightClick(button_search);
        LOGGER.debug("Search for " + input + " was executed in author page");
        return this;
    }

    public boolean isAuthorCorrect(String fullName) {
        webDriverDecorator.waitForElementEnabled(authorNameText);
        LOGGER.debug("Comparing current author with " + fullName + " was started");
        return authorNameText.getText().contains(fullName);
    }

    public String getHeadAuthorsText() {
        jsExecutor.highlightElement(authorsText);
        LOGGER.debug("Getting head authors text");
        return authorsText.getText();
    }
}
