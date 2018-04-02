package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddingStoryPage extends BaseLiveLibPage {

    @FindBy(xpath = "//input[@name='story[title]']")
    private WebElement inputTitleOfStory;

    @FindBy(xpath = "//textarea[@name='story[description]']")
    private WebElement inputDescriptionOfStory;

    @FindBy(xpath = "//input[@class='btn100']")
    private WebElement submitStory;

    @FindBy(xpath = "//span[@class='i-clear']")
    private WebElement popupClose;

    @FindBy(id = "story[remove]")
    private WebElement removeStoryCheckBox;

    public AddingStoryPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public AddingStoryPage writeStory(String title, String description) {
        webDriverDecorator.waitHighlightInput(inputTitleOfStory, title);
        webDriverDecorator.waitHighlightInput(inputDescriptionOfStory, description);
        webDriverDecorator.waitHighlightClick(submitStory);
        webDriverDecorator.waitHighlightClick(popupClose);
        LOGGER.debug("Story with title " + title + " and description " + description + " was written");
        return this;
    }

    public AddingStoryPage deleteStory() {
        webDriverDecorator.waitHighlightClick(removeStoryCheckBox);
        webDriverDecorator.waitHighlightClick(submitStory);
        webDriverDecorator.switchTo().alert().accept();
        webDriverDecorator.waitHighlightClick(popupClose);
        LOGGER.debug("Story was deleted");
        return this;
    }

}
