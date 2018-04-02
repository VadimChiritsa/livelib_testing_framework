package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class WantToReadPage extends BaseLiveLibPage {

    private static final String ACTION = "delete";
    private static final String BOOK_WANTED_TO_READ_XPATH = "//div[@id='booklist']//a[contains(text(),'%s')]";
    @FindBy(xpath = "//a[@class='my-book-list-a menu-item']")
    private List<WebElement> checkboxBook;
    @FindBy(id = "my-book-list-select")
    private WebElement dropDownActions;
    @FindBy(xpath = "//input[@id='my-book-list-button']")
    private WebElement buttonEnter;
    @FindBy(xpath = "//span[@class='i-clear']")
    private WebElement popupClose;
    private List<WebElement> booksList;

    public WantToReadPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public boolean isBookOnPage(String bookName) {
        LOGGER.debug("Checking is book with name " + bookName + " exist on page");
        booksList = webDriverDecorator.findElements(By.xpath(String.format(BOOK_WANTED_TO_READ_XPATH, bookName)));
        return booksList.size() > 0;
    }

    public WantToReadPage deleteBookFromWantToRead() {
        for (WebElement checkbox : checkboxBook) {
            webDriverDecorator.waitHighlightClick(checkbox);
        }
        Select dropDown = new Select(dropDownActions);
        dropDown.selectByValue(ACTION);
        webDriverDecorator.waitHighlightClick(buttonEnter);
        webDriverDecorator.switchTo().alert().accept();
        webDriverDecorator.waitHighlightClick(popupClose);
        LOGGER.debug("Book was deleted from want to read page");
        return this;
    }
}
