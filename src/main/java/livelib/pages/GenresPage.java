package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GenresPage extends BaseLiveLibPage {

    @FindBy(id = "logo")
    private WebElement logo;

    @FindBy(xpath = "//div[@class='container']//h1")
    private WebElement allGenresHead;

    @FindBy(xpath = "//*[@class='main-genre-title']")
    private List<WebElement> listAllGenres;

    public GenresPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public GenresPage clickGenreByName(String genreName) {
        for (WebElement genre : listAllGenres) {
            jsExecutor.scrollToElement(genre);
            if (genre.getText().equalsIgnoreCase(genreName)) {
                webDriverDecorator.waitHighlightClick(genre);
                break;
            }
        }
        LOGGER.debug("Genre " + genreName + " was clicked");
        return this;
    }

    public boolean isGenreExistedOnPage(String genreName) {
        boolean exist = false;
        for (WebElement genre : listAllGenres) {
            jsExecutor.scrollToElement(genre);
            if (genre.getText().equalsIgnoreCase(genreName)) {
                jsExecutor.highlightElement(genre);
                exist = webDriverDecorator.isElementPresent(genre);
            }
        }
        LOGGER.debug("Checking is genre " + genreName + " exist on page");
        return exist;
    }

    public String getHeadAllGenresText() {
        jsExecutor.highlightElement(allGenresHead);
        LOGGER.debug("Getting all genres text");
        return allGenresHead.getText();
    }

    public boolean isBookFromNeededGenreExists(String genreName) {
        String locatorTag = "//a[@class='label-genre'][contains(@href, '" + genreName + "')]";
        WebElement genreTag = webDriverDecorator.findElement(By.xpath(locatorTag));
        LOGGER.debug("Checking is book from genre " + genreName + " exist");
        return genreTag.isEnabled();
    }

}

