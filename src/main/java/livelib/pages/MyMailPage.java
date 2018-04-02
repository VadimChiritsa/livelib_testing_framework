package livelib.pages;

import livelib.decorator.WebDriverDecorator;
import livelib.pages.htmlElements.MyMailHeader;

public class MyMailPage extends BaseLiveLibPage {

    MyMailHeader mailHeader;

    public MyMailPage(WebDriverDecorator webDriverDecorator) {
        super(webDriverDecorator);
        mailHeader.setWebDriverDecorator(webDriverDecorator);
    }

    public CreateNewMailPage navigateToCreateNewLetterPage() {
        mailHeader.navigateToNewLetterPage();
        LOGGER.debug("Creating new letter has started");
        return new CreateNewMailPage(webDriverDecorator);
    }

    public SentMailsPage navigateToSentMailPage() {
        mailHeader.navigateToSendLetters();
        LOGGER.debug("Navigated to sent mail page");
        return new SentMailsPage(webDriverDecorator);
    }
}
