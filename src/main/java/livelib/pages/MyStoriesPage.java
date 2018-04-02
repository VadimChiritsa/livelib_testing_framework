package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyStoriesPage extends BaseLiveLibPage {

    @FindBy(xpath = "//div[@class='block-border card-block']//a[@class='post-scifi-title']")
    private WebElement storyTitle;

    @FindBy(xpath = "//div[@class='block-border card-block']//div[@class='brief-review']")
    private WebElement storyBrief;

    @FindBy(xpath = "//a[@class='object-more']")
    private WebElement buttonMoreAboutStory;

    @FindBy(xpath = "//a[@title='Редактировать историю']")
    private WebElement buttonEditStory;

    public MyStoriesPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public boolean isStoryExist(String title, String brief) {
        LOGGER.debug("Checking is story with title " + title + " and brief " + brief + " exist on page");
        return storyTitle.getText().equalsIgnoreCase(title) && storyBrief.getText().equalsIgnoreCase(brief);
    }

    public AddingStoryPage editStory() {
        LOGGER.debug("Editing story has started");
        webDriverDecorator.waitHighlightClick(buttonMoreAboutStory);
        webDriverDecorator.waitHighlightClick(buttonEditStory);
        return new AddingStoryPage(webDriverDecorator);
    }

}
