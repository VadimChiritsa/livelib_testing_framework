package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyReviewsPage extends BaseLiveLibPage {

    @FindBy(xpath = "//a[@class='post-scifi-title']")
    private WebElement reviewTitle;

    @FindBy(xpath = "//div[@class='description']//p")
    private WebElement reviewBrief;

    @FindBy(xpath = "//a[@class='object-more']")
    private WebElement buttonMoreAboutReview;

    @FindBy(xpath = "//a[@title='Редактировать рецензию']")
    private WebElement buttonEditReview;

    public MyReviewsPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public boolean isReviewExist(String title, String brief) {
        LOGGER.debug("Checking is review with title " + title + " and brief " + brief + " exist on my reviews page");
        return reviewTitle.getText().equalsIgnoreCase(title) && reviewBrief.getText().equalsIgnoreCase(brief);
    }

    public EditReviewPage editReview() {
        LOGGER.debug("Editing review has started");
        webDriverDecorator.waitHighlightClick(buttonMoreAboutReview);
        webDriverDecorator.waitHighlightClick(buttonEditReview);
        return new EditReviewPage(webDriverDecorator);
    }
}
