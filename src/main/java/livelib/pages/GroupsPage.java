package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GroupsPage extends BaseLiveLibPage {

    private static final String TITLE = "Группы";

    @FindBy(xpath = "//div[@class='container']//h1")
    private WebElement groupsHead;

    @FindBy(id = "input-search-book")
    private WebElement inputSearch;

    @FindBy(id = "btn-search-new")
    private WebElement button_search;

    @FindBy(css = "a[href*='216'] + a")
    private WebElement groupNameText;

    public GroupsPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
        webDriverDecorator.waitForTitle(TITLE);
    }

    public GroupsPage findInGroupsSearch(String input) {
        webDriverDecorator.waitHighlightInput(inputSearch, input);
        webDriverDecorator.waitHighlightClick(button_search);
        LOGGER.debug("Search for " + input + " was executed in groups page");
        return this;
    }

    public boolean groupIsCorrect(String groupName) {
        webDriverDecorator.waitForElementEnabled(groupNameText);
        LOGGER.debug("Checking is group name correct");
        return groupNameText.getText().contains(groupName);
    }

    public String getHeadGroupsText() {
        jsExecutor.highlightElement(groupsHead);
        LOGGER.debug("Getting head groups text");
        return groupsHead.getText();
    }
}
