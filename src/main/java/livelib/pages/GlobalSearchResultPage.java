package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GlobalSearchResultPage extends BaseLiveLibPage {

    private static final String BOOK_LIST_XPATH = "//div[@id='objects-block']//a[contains(@title,'%s')]";

    @FindBy(id = "input-search-book")
    private WebElement inputSearch;

    @FindBy(id = "btn-search-new")
    private WebElement button_search;

    @FindBy(xpath = "//a[@class = 'title']")
    private WebElement bookTitleLink;

    @FindBy(xpath = "//div[@class='object-info']/a")
    private List<WebElement> booksList;

    private List<WebElement> books;

    public GlobalSearchResultPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public GlobalSearchResultPage findInBookSearch(String input) {
        webDriverDecorator.waitHighlightInput(inputSearch, input);
        webDriverDecorator.waitHighlightClick(button_search);
        LOGGER.debug("Search for " + input + " was executed in global search result page");
        return this;
    }

    public boolean areBooksExist(String bookName) {
        books = webDriverDecorator.findElements(By.xpath(String.format(BOOK_LIST_XPATH, bookName)));
        LOGGER.debug("Checking are books with name " + bookName + " exist");
        return books.size() > 0;
    }

    public BookItemPage openBookItem(String bookName) {
        for (WebElement book : booksList) {
            if (book.getText().equalsIgnoreCase(bookName)) {
                webDriverDecorator.waitHighlightClick(book);
                break;
            }
        }
        LOGGER.debug("Page of " + bookName + " was opened");
        return new BookItemPage(webDriverDecorator);
    }
}
