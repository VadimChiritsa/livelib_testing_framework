package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditReviewPage extends BaseLiveLibPage {

    @FindBy(xpath = "//input[@name='review[title]']")
    private WebElement addTitle;

    @FindBy(xpath = "//textarea[@name='review[review]']")
    private WebElement addBrief;

    @FindBy(xpath = "//select[@name='review[access]']")
    private WebElement openSelect;

    @FindBy(xpath = "//select[@name='review[access]']//option[text()='только я']")
    private WebElement buttonOnlyMe;

    @FindBy(xpath = "//input[@value='Опубликовать']")
    private WebElement buttonSave;

    @FindBy(id = "review[remove]")
    private WebElement deleteReview;

    @FindBy(xpath = "//a[text()='Предпросмотр']")
    private WebElement showReview;

    @FindBy(xpath = "//span[@class='i-rclose']")
    private WebElement closeReview;

    @FindBy(xpath = "//span[@class='i-clear']")
    private WebElement popupClose;

    public EditReviewPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public EditReviewPage fillTitleAndTextReview(String title, String text) {
        webDriverDecorator.waitHighlightInput(addTitle, title);
        webDriverDecorator.waitHighlightInput(addBrief, text);
        LOGGER.debug("Review's title and text were filled as " + title + " and " + text);
        return this;
    }

    public EditReviewPage chooseOnlyMeCanSee() {
        webDriverDecorator.waitHighlightClick(openSelect);
        webDriverDecorator.waitHighlightClick(buttonOnlyMe);
        LOGGER.debug("CheckBox 'only me can see' was checked");
        return this;
    }

    public EditReviewPage addToDrafts() {
        webDriverDecorator.waitHighlightClick(showReview);
        webDriverDecorator.waitHighlightClick(closeReview);
        LOGGER.debug("Review was added to drafts");
        return this;
    }

    public ReviewPage clickSave() {
        webDriverDecorator.waitHighlightClick(buttonSave);
        LOGGER.debug("Review was saved");
        return new ReviewPage(webDriverDecorator);
    }

    public ReviewPage fillAndSaveReview(String title, String text) {
        fillTitleAndTextReview(title, text);
        chooseOnlyMeCanSee();
        clickSave();
        webDriverDecorator.waitHighlightClick(popupClose);
        return new ReviewPage(webDriverDecorator);
    }

    public MyReviewsPage deleteReview() {
        webDriverDecorator.waitHighlightClick(deleteReview);
        webDriverDecorator.waitHighlightClick(buttonSave);
        LOGGER.debug("Review was deleted");
        return new MyReviewsPage(webDriverDecorator);
    }
}