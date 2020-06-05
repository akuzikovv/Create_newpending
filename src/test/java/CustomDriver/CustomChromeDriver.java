package CustomDriver;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomChromeDriver implements DriverSource {

    @Override
    public WebDriver newDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--whitelist-ip *");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("window-size=1280x800");
        chromeOptions.addArguments("no-sandbox");
        chromeOptions.addArguments("–disable-dev-shm-usage");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-setuid-sandbox");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
//        EventHandler handler = new EventHandler();
//        eventDriver.register(handler);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return eventDriver;
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}