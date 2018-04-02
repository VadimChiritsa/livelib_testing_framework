package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyFavouritesPage extends BaseLiveLibPage {

    @FindBy(xpath = "//a[@class='brow-book-name with-cycle']")
    private WebElement bookName;

    @FindBy(xpath = "//div[@class='block-border separator list-wish']//a[@title='Удалить из избранного']")
    private WebElement buttonDeleteFromFavourites;

    public MyFavouritesPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public boolean isBookExist(String bookName) {
        LOGGER.debug("Checking is book with name " + bookName + " exist on my favorites page");
        return this.bookName.getText().equalsIgnoreCase(bookName);
    }

    public BookItemPage chooseFavouriteBook() {
        webDriverDecorator.waitHighlightClick(bookName);
        LOGGER.debug("Book from favorites was clicked");
        return new BookItemPage(webDriverDecorator);
    }
}
