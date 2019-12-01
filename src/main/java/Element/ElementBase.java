package Element;

import Helper.TestData.SharedConfig;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementBase implements ElementBaseInterface {

    protected WebDriver driver;
    WebDriverWait wait;
    private final JavascriptExecutor jsExecutor;

    ElementBase(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, SharedConfig.getWebDriverTimout());

        PageFactory.initElements(driver, this);

        jsExecutor = (JavascriptExecutor) driver;
    }

    /***
     * Wait for given web element will be invisible
     * @param webElement - WebElement for which state change wait should be executed
     */
    @Override
    public void waitForElementToBeInvisible(WebElement webElement) {
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    /***
     * Wait for given web element will be visible
     * @param webElement - WebElement for which state change wait should be executed
     */
    @Override
    public void waitForElementToBeVisible(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /***
     * Wait for given web element will be clickable
     * @param webElement - WebElement for which state change wait should be executed
     */
    @Override
    public void waitForElementToBeClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /***
     * Set value of given element
     * @param element - WebElement for which value should be set
     * @param value - value to be set
     */
    @Override
    public void setValue(WebElement element, int value) {
        setValue(element, String.valueOf(value));
    }

    /***
     * Set value of given element
     * @param element - WebElement for which value should be set
     * @param value - value to be set
     */
    @Override
    public void setValue(WebElement element, String value){
        jsExecutor.executeScript(String.format("arguments[0].value='%s';", value), element);
    }

    /***
     * Click with a use of JavaScript
     * @param webElement - WebElement to be clicked
     */
    @Override
    public void safetyClick(WebElement webElement){
        scrollToElement(webElement);

        wait.until( el -> {
            try{
                jsExecutor.executeScript("arguments[0].click();", webElement);
                return true;
            } catch (ElementClickInterceptedException click){
                return false;
            }
        });
    }

    /***
     * Scroll to given web element
     * @param webElement - WebElement to which scroll should be done
     */
    @Override
    public void scrollToElement(WebElement webElement){
        jsExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
    }
}
