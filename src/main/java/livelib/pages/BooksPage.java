package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import livelib.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BooksPage extends BaseLiveLibPage {

    private static final int MIN_VALUE_RANDOM_BOOK = 0;
    private static final int MAX_VALUE_RANDOM_BOOK = 4;

    @FindBy(xpath = "//div[@class='container']//h1")
    private WebElement whatToReadHead;
    @FindBy(xpath = "//div[@id='carousel-shakers-']//img")
    private List<WebElement> popularBooksOnSlider;
    @FindBy(xpath = "//ul[@id='menu-container']//a[@href='/books']")
    private WebElement whatToReadButton;
    @FindBy(xpath = "//ul[@id='menu-container']//a[@href='/rec/master']")
    private WebElement recommendationsButton;
    @FindBy(xpath = "//ul[@id='menu-container']//a[@href='/books/movers-and-shakers']")
    private WebElement popularBooksButton;
    @FindBy(xpath = "//ul[@id='menu-container']//a[@href='/books/novelties']")
    private WebElement noveltiesButton;
    @FindBy(xpath = "//ul[@id='menu-container']//a[@href='/books/annual/2018']")
    private WebElement ratingButton;
    @FindBy(xpath = "//ul[@id='menu-container']//a[@href='/mob/97/books']")
    private WebElement flashmobButton;
    @FindBy(xpath = "//ul[@id='menu-container']//a[@href='/books/bestsellers']")
    private WebElement bestsellersButton;
    @FindBy(xpath = "//span[@class='i-gmore']")
    private WebElement dropdownMenuButton;
    @FindBy(xpath = "//span[@class='/books/top']")
    private WebElement top100Button;
    @FindBy(xpath = "//ul[@id='menu-container']//a")
    private List<WebElement> allLinksOnMenu;

    public BooksPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public BooksPage clickPopularBooksOnMenu() {
        webDriverDecorator.waitHighlightClick(popularBooksButton);
        LOGGER.debug("Popular books on books page was clicked");
        return this;
    }

    public BooksPage clickWhatToReadBooksOnMenu() {
        webDriverDecorator.waitHighlightClick(whatToReadButton);
        LOGGER.debug("What to read on books page was clicked");
        return this;
    }

    public BooksPage clickRecommendationsOnMenu() {
        webDriverDecorator.waitHighlightClick(recommendationsButton);
        LOGGER.debug("Recommendations on books page was clicked");
        return this;
    }

    public BooksPage clickNoveltiesOnMenu() {
        webDriverDecorator.waitHighlightClick(noveltiesButton);
        LOGGER.debug("Novelties on books page was clicked");
        return this;
    }

    public BooksPage clickRatingOnMenu() {
        webDriverDecorator.waitHighlightClick(ratingButton);
        LOGGER.debug("Rating on books page was clicked");
        return this;
    }

    public BooksPage clickFlashmobOnMenu() {
        webDriverDecorator.waitHighlightClick(flashmobButton);
        LOGGER.debug("Flashmob on books page was clicked");
        return this;
    }

    public BooksPage clickBestsellersOnMenu() {
        webDriverDecorator.waitHighlightClick(bestsellersButton);
        LOGGER.debug("Bestsellers on books page was clicked");
        return this;
    }

    public BooksPage clickDropdownOnMenu() {
        webDriverDecorator.waitHighlightClick(dropdownMenuButton);
        LOGGER.debug("Dropdown on books page was clicked");
        return this;
    }

    public BooksPage clickTop100OnMenu() {
        webDriverDecorator.waitHighlightClick(top100Button);
        LOGGER.debug("Top100 on books page was clicked");
        return this;
    }

    public boolean isElementExistedOnSubMenu(String linkText) {
        LOGGER.debug("Checking is " + linkText + " existed on submenu");
        for (WebElement link : allLinksOnMenu) {
            if (link.getText().equalsIgnoreCase(linkText)) {
                return webDriverDecorator.isElementPresent(link);
            }
        }
        return false;
    }

    public String getHeadWhatToReadText() {
        jsExecutor.highlightElement(whatToReadHead);
        LOGGER.debug("Getting what to read text");
        return whatToReadHead.getText();
    }

    public BookItemPage clickRandomBookFromPopular() {
        WebElement book = popularBooksOnSlider.get(Utils.getRandomInteger(MAX_VALUE_RANDOM_BOOK, MIN_VALUE_RANDOM_BOOK));
        webDriverDecorator.waitHighlightClick(book);
        LOGGER.debug("Random book from popular was selected");
        return new BookItemPage(webDriverDecorator);
    }

}

