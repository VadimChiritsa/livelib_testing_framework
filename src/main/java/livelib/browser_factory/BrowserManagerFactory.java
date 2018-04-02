package livelib.browser_factory;

public class BrowserManagerFactory {

    public static BrowserManager getManager(BrowserName name) {
        BrowserManager browserManager = null;
        switch (name) {
            case CHROME:
                browserManager = new ChromeBrowserManager();
                break;
            case FIREFOX:
                browserManager = new FirefoxBrowserManager();
                break;
            case EDGE:
                browserManager = new EdgeBrowserManager();
                break;
            case OPERA:
                browserManager = new OperaBrowserManager();
                break;
            default:
                browserManager = new ChromeBrowserManager();
                break;
        }
        return browserManager;
    }

}
