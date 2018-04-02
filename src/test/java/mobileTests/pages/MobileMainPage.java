package mobileTests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MobileMainPage {
    protected static final Logger LOGGER = LogManager.getLogger(MobileMainPage.class.getName());
    private AppiumDriver<MobileElement> driver;

    @AndroidFindBy(id = "ru.livelib.client:id/action_search")
    private MobileElement searchButton;
    @AndroidFindBy(id = "ru.livelib.client:id/search_src_text")
    private MobileElement searchField;
    @AndroidFindBy(id = "ru.livelib.client:id/name")
    private List<MobileElement> listOfBooks;
    @AndroidFindBy(className = "android.widget.ImageButton")
    private MobileElement menuButton;
    @AndroidFindBy(xpath = "//*[@text = 'Авторы']")
    private MobileElement authorButton;

    public MobileMainPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        LOGGER.debug(MobileMainPage.class.getName() + " is loaded");
    }

    public MobileMainPage searchBookByName(String bookName) {
        searchButton.click();
        inputSearchField(bookName);
        LOGGER.debug("Searching book " + bookName);
        return this;
    }

    private void inputSearchField(String text) {
        searchField.sendKeys(text + "\n");
        LOGGER.debug(text + " was input into search field");
    }

    public boolean isBookExisted(String bookName) {
        for (MobileElement book : listOfBooks) {
            if (book.getText().equalsIgnoreCase(bookName)) {
                LOGGER.debug("Book " + bookName + " is found on the page");
                return true;
            }
        }
        LOGGER.debug("Book " + bookName + " is not found on the page");
        return false;

    }

    public AuthorMobilePage searchAuthorByName(String authorName) {
        menuButton.click();
        authorButton.click();
        searchButton.click();
        inputSearchField(authorName);
        LOGGER.debug("Searching author " + authorName);
        return new AuthorMobilePage(driver);
    }
}
