package livelib.pages.htmlElements;

import livelib.decorator.WebDriverDecorator;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(id = "user-menu")
public class UserMenu extends HtmlElement {

    private static final int TIMEOUT_IN_SECONDS = 5;
    private WebDriverDecorator webDriverDecorator;
    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//a[@id = 'user-menu-target']/span[@class = 'usermenu-arrow']")
    private Button userMenuButton;

    @Timeout(TIMEOUT_IN_SECONDS)
    @FindBy(xpath = "//a[@href='/logout']")
    private Button logoutButton;

    public void setWebDriverDecorator(WebDriverDecorator webDriverDecorator) {
        this.webDriverDecorator = webDriverDecorator;
    }

    public void open() {
        webDriverDecorator.waitHighlightClick(userMenuButton);
    }

    public Button getUserMenuLink() {
        return userMenuButton;
    }

    public void logout() {
        open();
        webDriverDecorator.waitHighlightClick(logoutButton);
    }
}
