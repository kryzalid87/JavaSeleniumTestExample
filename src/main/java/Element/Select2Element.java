package Element;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

class Select2Element extends ElementBase {

    @FindBy(className = "select2-arrow")
    private WebElement selector;

    private By selectDropLocator = new By.ByCssSelector("#select2-drop:not([style*='display: none'])");
    private By inputLocator = new By.ByCssSelector(".select2-input");
    private By searchResultLocator = new By.ByCssSelector(".select2-results li.select2-result-selectable");

    Select2Element(WebDriver driver) {
        super(driver);
    }

    /***
     * @param value value to be searched
     */
    void searchForValue(String value) {
        selector.click();

        var drop = driver.findElement(selectDropLocator);
        var input = drop.findElement(inputLocator);

        wait.until(ExpectedConditions.visibilityOf(input));
        input.sendKeys(value);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchResultLocator, 1));
        var searchResults = drop.findElements(searchResultLocator);

        var results = searchResults.stream()
                .filter(el -> StringUtils.containsIgnoreCase(el.getText(), value))
                .findFirst().orElseThrow((() -> new NoSuchElementException(String.format("There was no search result with %s", value))));

        results.click();
    }
}
