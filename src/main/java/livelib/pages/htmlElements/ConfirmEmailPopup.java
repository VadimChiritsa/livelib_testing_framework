package livelib.pages.htmlElements;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(id = "popup-confirm-email")
public class ConfirmEmailPopup extends HtmlElement {

    private static final int TIMEOUT_IN_SECONDS = 5;
    private WebDriverDecorator webDriverDecorator;
    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//div[@class= 'popup-close']")
    private Button closePopupButton;

    public void setWebDriverDecorator(WebDriverDecorator webDriverDecorator) {
        this.webDriverDecorator = webDriverDecorator;
    }

    public void close() {
        webDriverDecorator.waitHighlightClick(closePopupButton);
    }
}
