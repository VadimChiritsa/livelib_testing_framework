package livelib.pages.htmlElements;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.*;

@FindBy(xpath = "//form[@id='form-login']/ancestor::div[1]")
public class RegistrationForm extends HtmlElement {

    private static final int TIMEOUT_IN_SECONDS = 5;
    private WebDriverDecorator webDriverDecorator;
    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//a[@class = 'authorize-links'][contains(text(), 'Регистрация')]")
    private Link registrationFormLink;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//input[@id='popup-input-email-register']")
    private TextInput loginInput;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//input[@id='popup-input-password-register']")
    private TextInput passwordInput;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(id = "reg[messages_send]")
    private CheckBox sendNotificationsCheckbox;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(id = "reg[subs_send]")
    private CheckBox sendNewsCheckbox;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//a[@class='lrow-semiref']")
    private CheckBox inputPasswordByMyself;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//input[@id='register_submit']")
    private Button registerButton;

    public void setWebDriverDecorator(WebDriverDecorator webDriverDecorator) {
        this.webDriverDecorator = webDriverDecorator;
    }

    public void open() {
        webDriverDecorator.waitHighlightClick(registrationFormLink);
    }

    private void fillLoginInput(String login) {
        webDriverDecorator.waitHighlightInput(loginInput, login);
    }

    private void fillPasswordInput(String password) {
        webDriverDecorator.waitHighlightInput(passwordInput, password);
    }

    public void fillCredentials(String login, String password) {
        inputPasswordByMyself();
        fillLoginInput(login);
        fillPasswordInput(password);
    }

    public void inputPasswordByMyself() {
        webDriverDecorator.waitHighlightClick(inputPasswordByMyself);
    }

    public void clickRegisterButton() {
        webDriverDecorator.waitHighlightClick(registerButton);
    }

    public Link getRegistrationFormLink() {
        return registrationFormLink;
    }

    public void fillAndSendRegistrationData(String login, String password) {
        open();
        fillCredentials(login, password);
        clickRegisterButton();
    }
}
