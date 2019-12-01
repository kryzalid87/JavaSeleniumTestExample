package Page;

import Helper.TestData.SharedConfig;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase implements PageBaseInterface {

    protected WebDriver driver;
    WebDriverWait wait;

    PageBase(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, SharedConfig.getWebDriverTimout());
        waitForPageToBeLoaded();

        PageFactory.initElements(driver, this);
    }

    /***
     * Wait for page to be loaded
     */
    private void waitForPageToBeLoaded() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    /***
     * @return true if alert is present
     */
    public Boolean isAlertPresent(){
        return !(getBrowserAlert() == null);
    }

    /***
     * @return browser alert
     */
    private Alert getBrowserAlert(){
        waitForAlert();
        return driver.switchTo().alert();
    }

    /***
     * Wait for alert to be visible
     */
    private void waitForAlert(){
        wait.until(ExpectedConditions.alertIsPresent());
    }
}
