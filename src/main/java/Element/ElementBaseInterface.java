package Element;

import org.openqa.selenium.WebElement;

public interface ElementBaseInterface {
    void waitForElementToBeInvisible(WebElement webElement);

    void waitForElementToBeVisible(WebElement webElement);

    void waitForElementToBeClickable(WebElement webElement);

    void setValue(WebElement element, int value);

    void setValue(WebElement element, String value);

    void safetyClick(WebElement webElement);

    void scrollToElement(WebElement webElement);
}
