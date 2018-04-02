package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReviewPage extends BaseLiveLibPage {

    private static final String BOOK_REVIEW_XPATH = "//div[@id='book-reviews']//a[@title='%s']";

    @FindBy(xpath = "//h1[@class='profile-title']")
    private WebElement reviewsOnBooksHead;
    @FindBy(id = "input-search-book")
    private WebElement inputSearch;
    @FindBy(id = "btn-search-new")
    private WebElement button_search;

    private List<WebElement> reviewsList;

    public ReviewPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public ReviewPage findInReviewSearch(String input) {
        webDriverDecorator.waitHighlightInput(inputSearch, input);
        webDriverDecorator.waitHighlightClick(button_search);
        return this;
    }

    public boolean areReviewsExist(String nameBook) {
        LOGGER.debug("Checking are reviews for book with name " + nameBook + " exist on review page");
        reviewsList = webDriverDecorator.findElements(By.xpath(String.format(BOOK_REVIEW_XPATH, nameBook)));
        return reviewsList.size() > 0;
    }

    public String getHeadReviewsOnBooksText() {
        LOGGER.debug("Getting head reviews");
        jsExecutor.highlightElement(reviewsOnBooksHead);
        return reviewsOnBooksHead.getText();
    }
}
