package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectionsPage extends BaseLiveLibPage {

    @FindBy(xpath = "//div[@class='container']//h1")
    private WebElement bestSelectionsHead;

    @FindBy(id = "input-search-book")
    private WebElement inputSearch;

    @FindBy(id = "btn-search-new")
    private WebElement button_search;

    @FindBy(xpath = "//a[@class='post-title game-title margs-bottom']")
    private WebElement selectionNameText;

    public SelectionsPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public SelectionsPage findInSelectionsSearch(String input) {
        webDriverDecorator.waitHighlightInput(inputSearch, input);
        webDriverDecorator.waitHighlightClick(button_search);
        LOGGER.debug("Search for " + input + " was executed in selections page");
        return this;
    }

    public boolean selectionIsCorrect(String selectionName) {
        webDriverDecorator.waitForElementEnabled(selectionNameText);
        LOGGER.debug("Comparing current selections with " + selectionName + " was started");
        return selectionNameText.getText().contains(selectionName);
    }

    public String getHeadBestSelectionsText() {
        jsExecutor.highlightElement(bestSelectionsHead);
        LOGGER.debug("Getting best selections text");
        return bestSelectionsHead.getText();
    }
}
