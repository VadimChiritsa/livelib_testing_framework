package livelib.pages.htmlElements;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//ul[@class='menu-container']")
public class MyMailHeader extends HtmlElement {

    private static final int TIMEOUT_IN_SECONDS = 5;
    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//a[contains(@href,'/messages/in')]")
    WebElement buttonReceivedLetters;
    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//a[contains(@href,'/messages/out')]")
    WebElement buttonSendLetters;
    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//a[contains(@href,'/message/send')]")
    WebElement buttonNewLetter;
    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//a[contains(@href,'/messages/notifications')]")
    WebElement buttonNotifications;
    private WebDriverDecorator webDriverDecorator;

    public void setWebDriverDecorator(WebDriverDecorator webDriverDecorator) {
        this.webDriverDecorator = webDriverDecorator;
    }

    public void navigateToReceivedLetters() {
        webDriverDecorator.waitHighlightClick(buttonReceivedLetters);
    }

    public void navigateToSendLetters() {
        webDriverDecorator.waitHighlightClick(buttonSendLetters);
    }

    public void navigateToNotifications() {
        webDriverDecorator.waitHighlightClick(buttonNotifications);
    }

    public void navigateToNewLetterPage() {
        webDriverDecorator.waitHighlightClick(buttonNewLetter);
    }
}
