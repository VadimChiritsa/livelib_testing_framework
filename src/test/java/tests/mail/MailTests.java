package tests.mail;

import livelib.pages.SentMailsPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import static livelib.utils.PropertiesReader.getPropertyValue;

public class MailTests extends BaseTest {
    private String validUserMail;
    private String letter;
    private String invalidUserMail;
    private SentMailsPage sentMailsPage;

    @BeforeClass(alwaysRun = true)
    public void setValuesFromProperties() {
        validUserMail = getPropertyValue("TEST_SENT_LETTER_VALID_USER");
        invalidUserMail = getPropertyValue("TEST_SENT_LETTER_INVALID_USER");
        letter = getPropertyValue("TEST_SENT_MAIL_TEXT");
        baseLiveLibPage = baseLiveLibPage.signIn(getTestUserLogin(), getTestUserPassword());
    }

    @Test(alwaysRun = true)
    public void writeLetterToValidMail() {
        sentMailsPage = baseLiveLibPage
                .navigateToMyMailPage()
                .navigateToCreateNewLetterPage()
                .setAddressField(validUserMail)
                .setMessageField(letter)
                .sendMail();
        Assert.assertTrue(sentMailsPage.isMailSend(validUserMail, letter));
    }

    @Test(alwaysRun = true, dependsOnMethods = "writeLetterToValidMail")
    public void writeLetterToInValidMail() {
        sentMailsPage = baseLiveLibPage
                .navigateToMyMailPage()
                .navigateToCreateNewLetterPage()
                .setAddressField(invalidUserMail)
                .setMessageField(letter)
                .sendMail()
                .navigateToMyMailPage()
                .navigateToSentMailPage();
        Assert.assertFalse(sentMailsPage.isMailSend(invalidUserMail, letter));
    }

    @AfterClass(alwaysRun = true)
    public void clean() {
        baseLiveLibPage
                .navigateToMyMailPage()
                .navigateToSentMailPage()
                .deleteLetter();
    }
}
