package livelib.utils;

import livelib.decorator.WebDriverDecorator;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSExecutor {

    private static final String SCRIPT_HIGHLIGHT_BORDER_5PX_GREEN = "arguments[0].style.border='5px solid green'";
    private static final String SCRIPT_HIGHLIGHT_BORDER_0PX = "arguments[0].style.border='0px'";
    private static final String SCRIPT_SCROLL = "window.scrollTo(arguments[0],arguments[1])";
    private static final int SLEEP_TIME = 500;
    private static final int OFFSET_X = 250;
    private static final int OFFSET_Y = 250;

    private WebDriverDecorator driverDecorator;

    public JSExecutor(WebDriverDecorator driverDecorator) {
        this.driverDecorator = driverDecorator;
    }

    public void executeScript(String script, Object... object) {
        ((JavascriptExecutor) driverDecorator.getActualDriver()).executeScript(script, object);
    }

    public void highlightElement(WebElement element) {
        executeScript(SCRIPT_HIGHLIGHT_BORDER_5PX_GREEN, element);
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            LogManager.getLogger().error(e.getMessage());
        }
        executeScript(SCRIPT_HIGHLIGHT_BORDER_0PX, element);
    }

    public void scrollToElement(WebElement element, int offSetX, int offSetY) {
        int x = element.getLocation().x - offSetX;
        int y = element.getLocation().y - offSetY;
        executeScript(SCRIPT_SCROLL, x, y);
    }

    public void scrollToElement(WebElement element) {
        int x = element.getLocation().x - OFFSET_X;
        int y = element.getLocation().y - OFFSET_Y;
        executeScript(SCRIPT_SCROLL, x, y);
    }
}

