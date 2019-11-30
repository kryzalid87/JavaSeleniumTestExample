import Helper.TestData.TestData;
import Helper.TestData.WebDriverData;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

class WebDriverFactory {

    /***
     * Create new instance of WebDriver
     * @param webDriverData - Webdriver test data
     * @return new WebDriver instance
     */
    static WebDriver getInstance(WebDriverData webDriverData) {
        switch (webDriverData.getType().toLowerCase()) {
            case "firefox":
                return new FirefoxDriver();
            case "chrome":
            default:
                if (webDriverData.getUseSeleniumGrid()) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized",
                            "--no-sandbox");

                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                    capabilities.setPlatform(Platform.LINUX);
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);

                    try {
                        return new RemoteWebDriver(new URL(webDriverData.getRemoteHub()), capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.setProperty("webdriver.chrome.driver", webDriverData.getDriverPath());
                    var options = new ChromeOptions();
                    options.addArguments("--start-maximized",
                            "--incognito");
                    return new ChromeDriver(options);
                }

                return null;
        }
    }
}
