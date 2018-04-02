package mobileTests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AuthorMobilePage extends MobileMainPage {
    protected static final Logger LOGGER = LogManager.getLogger(MobileMainPage.class.getName());
    private String authorRealName = "Стивен Кинг";
    @AndroidFindBy(id = "ru.livelib.client:id/text_name")
    private List<MobileElement> listOfAuthors;

    public AuthorMobilePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public boolean isAuthorExist(String authorName) {
        boolean result = false;
        for (MobileElement author : listOfAuthors) {
            if (author.getText().equalsIgnoreCase(authorRealName)) {
                LOGGER.debug("Author " + authorRealName + " is found on the page");
                result = true;
                break;
            }
        }
        LOGGER.debug("Author " + authorRealName + " is not found on the page");
        return result;
    }
}
