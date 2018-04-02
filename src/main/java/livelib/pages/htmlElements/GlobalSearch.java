package livelib.pages.htmlElements;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@FindBy(xpath = "//form[@action = '/find']")
public class GlobalSearch extends HtmlElement {

    private static final int TIMEOUT_IN_SECONDS = 5;
    private WebDriverDecorator webDriverDecorator;
    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(id = "find-text")
    private TextInput searchByTextInput;

    public void setWebDriverDecorator(WebDriverDecorator webDriverDecorator) {
        this.webDriverDecorator = webDriverDecorator;
    }

    public void fillGlobalSearchInput(String input) {
        webDriverDecorator.waitHighlightInput(searchByTextInput, input);
    }
}
