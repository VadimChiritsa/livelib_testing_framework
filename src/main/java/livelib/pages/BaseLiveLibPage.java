package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import livelib.pages.htmlElements.*;
import livelib.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseLiveLibPage extends AbstractPage {
    private static final String URL = PropertiesReader.getPropertyValue("BASE_URL");
    private static final int DEFAULT_TIME_FOR_WAIT = 5 * 1000;
    private static final String USER_LOGIN = "//span[@class='login-str']";

    private SignInForm signInForm;
    private RegistrationForm registrationForm;
    private ConfirmEmailPopup confirmEmailPopup;
    private UserMenu userMenu;
    private Headers headers;
    private GlobalSearch globalSearch;

    @FindBy(id = "mainmenu-context-more")
    private WebElement dropDownList;

    @FindBy(xpath = "//span[@class='mainmenu-more']")
    private WebElement dropDownListButton;

    @FindBy(id = "msgs-new-header")
    private WebElement messagesButton;

    @FindBy(xpath = "//span[@class='bookshelf']")
    private WebElement userBooksButton;

    @FindBy(xpath = "//span[@class='msg-mail']")
    private WebElement userMailButton;

    @FindBy(xpath = "//div[@id = 'tinyalert']//span[@class = 'i-clear']")
    private WebElement closeTinyAlertButton;

    @FindBy(xpath = "//span[@class='i-close']")
    private WebElement closeStartPopup;

    public BaseLiveLibPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
        signInForm.setWebDriverDecorator(webDriverDecorator);
        registrationForm.setWebDriverDecorator(webDriverDecorator);
        confirmEmailPopup.setWebDriverDecorator(webDriverDecorator);
        userMenu.setWebDriverDecorator(webDriverDecorator);
        headers.setWebDriverDecorator(webDriverDecorator);
        globalSearch.setWebDriverDecorator(webDriverDecorator);
    }

    public BaseLiveLibPage open() {
        webDriverDecorator.get(URL);
        LOGGER.debug("Page " + URL + " was opened");
        return this;
    }

    public GlobalSearchResultPage search(String input) {
        globalSearch.fillGlobalSearchInput(input + Keys.ENTER);
        LOGGER.debug("Search for " + input + " was executed");
        return new GlobalSearchResultPage(webDriverDecorator);
    }

    public BooksPage navigateToBooksPage() {
        headers.clickBooksTab();
        LOGGER.debug("Navigated to books page");
        return new BooksPage(webDriverDecorator);
    }

    public GenresPage navigateToGenresPage() {
        headers.clickGenresTab();
        headers.clickAllGenresLink();
        LOGGER.debug("Navigated to genres page");
        return new GenresPage(webDriverDecorator);
    }

    public AuthorPage navigateToAuthorPage() {
        headers.clickAuthorsTab();
        LOGGER.debug("Navigated to author page");
        return new AuthorPage(webDriverDecorator);
    }

    public ReviewPage navigateToReviewPage() {
        headers.clickReviewTab();
        LOGGER.debug("Navigated to review page");
        return new ReviewPage(webDriverDecorator);
    }

    public QuotesPage navigateToQuotesPage() {
        headers.clickQuotesTab();
        LOGGER.debug("Navigated to quotes page");
        return new QuotesPage(webDriverDecorator);
    }

    public SelectionsPage navigateToSelectionsPage() {
        headers.clickSelectionsTab();
        LOGGER.debug("Navigated to selections page");
        return new SelectionsPage(webDriverDecorator);
    }

    public GroupsPage navigateToGroupsPage() {
        headers.clickGroupsTab();
        LOGGER.debug("Navigated to groups page");
        return new GroupsPage(webDriverDecorator);
    }

    public BaseLiveLibPage openSignInForm() {
        signInForm.open();
        LOGGER.debug("Sign in form was opened");
        return this;
    }

    public BaseLiveLibPage fillCredentialsForSigningIn(String login, String password) {
        signInForm.fillCredentials(login, password);
        LOGGER.debug("Login input as " + login + " and password input as " + password + " were filled for signing in ");
        return this;
    }

    public BaseLiveLibPage clickSubmitButton() {
        signInForm.clickSubmit();
        LOGGER.debug("Submit button was clicked");
        return this;
    }

    public MainPage signIn(String login, String password) {
        openSignInForm();
        fillCredentialsForSigningIn(login, password);
        clickSubmitButton();
        webDriverDecorator.waitForTextAppearedInElement(By.xpath(USER_LOGIN), login);
        return new MainPage(webDriverDecorator);
    }

    public BaseLiveLibPage signInForLoginTests(String login, String password) {
        signInForm.clickAllActionsOnSignInForm(login, password);
        LOGGER.debug("Signing in for login tests");
        return this;
    }

    public BaseLiveLibPage openRegistrationForm() {
        registrationForm.open();
        LOGGER.debug("Registration form was opened");
        return this;
    }

    public BaseLiveLibPage fillCredentialsForRegistration(String login, String password) {
        registrationForm.fillCredentials(login, password);
        LOGGER.debug("Email input and password input was filled as " + login + " and " + password + " respectively");
        return this;
    }

    public BaseLiveLibPage closeStartPopup() {
        webDriverDecorator.waitHighlightClick(closeStartPopup);
        LOGGER.debug("Start popup was closed");
        return this;
    }

    public BaseLiveLibPage clickRegisterButton() {
        registrationForm.clickRegisterButton();
        LOGGER.debug("Register button was clicked");
        return this;
    }

    public BaseLiveLibPage fillAndSendRegistrationData(String login, String password) {
        registrationForm.fillAndSendRegistrationData(login, password);
        LOGGER.debug("Email input and password input was filled as " + login + " and " + password + " respectively and send to server");
        return this;
    }

    public BaseLiveLibPage closeConfirmEmailPopup() {
        confirmEmailPopup.close();
        LOGGER.debug("Confirm Email Popup was closed");
        return this;
    }

    public BaseLiveLibPage acceptAlert() {
        new WebDriverWait(webDriverDecorator, DEFAULT_TIME_FOR_WAIT)
                .until(ExpectedConditions.alertIsPresent()).accept();
        LOGGER.debug("Alert was accepted");
        return this;
    }

    public BaseLiveLibPage closeSignInForm() {
        refresh();
        LOGGER.debug("Sign in form  was closed");
        return this;
    }

    public BaseLiveLibPage logOut() {
        userMenu.logout();
        LOGGER.debug("Logout from site was made");
        return this;
    }

    public BaseLiveLibPage closeTinyAlert() {
        webDriverDecorator.waitHighlightClick(closeTinyAlertButton);
        LOGGER.debug("Tiny alert was closed");
        return this;
    }

    public BaseLiveLibPage clickDropDownListOnHeader() {
        webDriverDecorator.waitHighlightClick(dropDownListButton);
        LOGGER.debug("Dropdown list was clicked");
        return this;
    }

    public GenresPage clickGenreLink(String genreName) {
        String locatorGenre = "//div[@class='genre-columns']//a[@href='/genre/" + genreName + "']";
        WebElement genreLink = webDriverDecorator.findElement(By.xpath(locatorGenre));
        webDriverDecorator.waitHighlightClick(genreLink);
        LOGGER.debug("Genre " + genreName + " page was opened");
        return new GenresPage(webDriverDecorator);
    }

    public BaseLiveLibPage clickGenres() {
        headers.clickGenresTab();
        LOGGER.debug("Genres tab was clicked");
        return this;
    }

    public boolean hasUserBooksButton() {
        LOGGER.debug("Start checking is user books button appeared or no");
        return userBooksButton.isEnabled();
    }

    public boolean hasUserMessagesButton() {
        LOGGER.debug("Start checking is user messages button appeared or no");
        return messagesButton.isEnabled();
    }

    public boolean hasRegistrationLink() {
        LOGGER.debug("Start checking is registration link appeared or no");
        return registrationForm.getRegistrationFormLink().isEnabled();
    }

    public boolean hasConfirmEmailPopup() {
        LOGGER.debug("Start checking is confirm email popup appeared or no");
        return confirmEmailPopup.isDisplayed();
    }

    public boolean hasUserMenu() {
        LOGGER.debug("Start checking is user menu list appeared or no");
        return userMenu.getUserMenuLink().isEnabled();
    }

    public boolean conditionForRegistrationTest() {
        LOGGER.debug("Start checking is registration successful or no");
        if (webDriverDecorator.isChrome()) {
            return this.hasConfirmEmailPopup();
        } else {
            return this.hasUserMenu();
        }
    }

    public MyReadBooksPage navigateToMyReadBooksPage() {
        webDriverDecorator.waitHighlightClick(userBooksButton);
        LOGGER.debug("Navigated to my read books page");
        return new MyReadBooksPage(webDriverDecorator);
    }

    public boolean dropDownListIsAppeared() {
        jsExecutor.highlightElement(dropDownList);
        LOGGER.debug("Start checking is dropdown list appeared or no");
        return webDriverDecorator.isElementPresent(dropDownList);
    }

    public MyMailPage navigateToMyMailPage() {
        webDriverDecorator.waitHighlightClick(userMailButton);
        LOGGER.debug("Navigated to my mail page");
        return new MyMailPage(webDriverDecorator);
    }
}