package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyDraftsPage extends BaseLiveLibPage {

    @FindBy(xpath = "//table[@class='game-list']//a[contains(text(),'Новая рецензия на книгу')]")
    private WebElement newReviewDraft;

    @FindBy(xpath = "//span[@class='i-delete']")
    private WebElement buttonDeleteDraft;

    @FindBy(xpath = "//span[@class='i-clear']")
    private WebElement popupClose;

    public MyDraftsPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public boolean isDraftReviewExist() {
        LOGGER.debug("Checking is review in drafts");
        return newReviewDraft.isEnabled();
    }

    public MyDraftsPage deleteDraft() {
        webDriverDecorator.waitHighlightClick(buttonDeleteDraft);
        webDriverDecorator.waitHighlightClick(popupClose);
        LOGGER.debug("Review was deleted");
        return this;
    }
}
