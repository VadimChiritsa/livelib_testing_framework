package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentMailsPage extends BaseLiveLibPage {
    private String addresseeFromField;
    private String messageFromField;

    @FindBy(xpath = "//div[@class='mrow-data wordbreak']/a")
    private WebElement addressTitle;

    @FindBy(xpath = "//div[@class='mrow-text total-text']/p")
    private WebElement messageTitle;

    @FindBy(xpath = "//a[@title='Удалить']")
    private WebElement deleteButton;

    @FindBy(xpath = "//span[@class='i-object-more']")
    private WebElement moreButton;

    public SentMailsPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
    }

    public boolean isMailSend(String addressee, String message) {
        LOGGER.debug("Checking is letter with addressee " + addressee + " and message " + message + " exist on page");
        addresseeFromField = addressTitle.getText();
        messageFromField = messageTitle.getText();
        return addresseeFromField.equals(addressee) && messageFromField.equals(message);
    }

    public SentMailsPage deleteLetter() {
        webDriverDecorator.waitHighlightClick(moreButton);
        webDriverDecorator.waitHighlightClick(deleteButton);
        webDriverDecorator.switchTo().alert().accept();
        LOGGER.debug("Letter was deleted from sent mail page");
        return this;
    }

}
