package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewMailPage extends BaseLiveLibPage {

    @FindBy(id = "new-message-recipient")
    private WebElement addressField;

    @FindBy(id = "add-text")
    private WebElement messageField;

    @FindBy(xpath = "//form[@method='post']//input[@type = 'submit']")
    private WebElement sentMessageButton;

    public CreateNewMailPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public CreateNewMailPage setAddressField(String addressee) {
        webDriverDecorator.waitHighlightInput(addressField, addressee);
        LOGGER.debug("Address field was filled with " + addressee);
        return this;
    }

    public CreateNewMailPage setMessageField(String message) {
        webDriverDecorator.waitHighlightInput(messageField, message);
        LOGGER.debug("Message field was filled with " + message);
        return this;
    }

    public SentMailsPage sendMail() {
        webDriverDecorator.waitHighlightClick(sentMessageButton);
        LOGGER.debug("Current letter was tried to sent");
        return new SentMailsPage(webDriverDecorator);
    }

}
