package livelib.pages.htmlElements;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextInput;

@FindBy(id = "form-login-popup")
public class SignInForm extends HtmlElement {

    private static final int TIMEOUT_IN_SECONDS = 5;
    private WebDriverDecorator webDriverDecorator;
    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//a[@class = 'authorize-links'][contains(text(), 'Вход')]")
    private Link signInLink;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(id = "user[login]")
    private TextInput loginForSignInInput;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(id = "user[password]")
    private TextInput passwordForSignInInput;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//input[@class='lrow-btn']")
    private Button submitButton;

    public void setWebDriverDecorator(WebDriverDecorator webDriverDecorator) {
        this.webDriverDecorator = webDriverDecorator;
    }

    public void open() {
        webDriverDecorator.waitHighlightClick(signInLink);
    }

    public void fillCredentials(String login, String password) {
        fillLoginInput(login);
        fillPasswordInput(password);
    }

    private void fillLoginInput(String login) {
        webDriverDecorator.waitHighlightInput(loginForSignInInput, login);
    }

    private void fillPasswordInput(String password) {
        webDriverDecorator.waitHighlightClick(passwordForSignInInput);
        webDriverDecorator.waitHighlightInput(passwordForSignInInput, password);
    }

    public void clickSubmit() {
        webDriverDecorator.waitHighlightClick(submitButton);
    }

    public void clickAllActionsOnSignInForm(String login, String password) {
        open();
        fillCredentials(login, password);
        clickSubmit();
    }
}
