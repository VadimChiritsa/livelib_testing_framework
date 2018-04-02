package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class QuotesPage extends BaseLiveLibPage {

    private static final String BOOK_QUOTES_XPATH = "//div[@id='book-quotes']//div[@class='block-border card-block event-pad']//a[@title='%s']";
    private static final String TITLE = "Цитаты из книг";

    @FindBy(xpath = "//div[@class='container']//h1")
    private WebElement quotesFromBooksHead;
    @FindBy(id = "input-search-book")
    private WebElement inputSearch;
    @FindBy(id = "btn-search-new")
    private WebElement button_search;

    private List<WebElement> quotesList;

    public QuotesPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
        webDriverDecorator.waitForTitle(TITLE);
    }

    public QuotesPage findInQuotesSearch(String input) {
        webDriverDecorator.waitHighlightInput(inputSearch, input);
        webDriverDecorator.waitHighlightClick(button_search);
        LOGGER.debug("Search for " + input + " was executed in quotes page");
        return this;
    }

    public boolean areQuotesExist(String bookName) {
        LOGGER.debug("Checking is quotes for book with name " + bookName + " exist on page");
        quotesList = webDriverDecorator.findElements(By.xpath(String.format(BOOK_QUOTES_XPATH, bookName)));
        return quotesList.size() > 0;
    }

    public String getHeadQuotesFromBooksText() {
        LOGGER.debug("Getting head quotes");
        jsExecutor.highlightElement(quotesFromBooksHead);
        return quotesFromBooksHead.getText();
    }
}