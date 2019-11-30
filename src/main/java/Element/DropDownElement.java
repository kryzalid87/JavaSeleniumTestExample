package Element;

import Page.PageBase;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;

public class DropDownElement extends ElementBase {

    @Getter
    private WebElement dropDown;

    public DropDownElement(WebDriver driver, String dropdownId) {
        super(driver);

        dropDown = driver.findElement(By.id(dropdownId));
    }

    /***
     * Change value for given drop down and return new page instance
     * @param pageClass page object class
     * @param valueToChange new value for given drop down
     * @param <TPage> page object type which extends PageBase
     * @return new page instance
     */
    public <TPage extends PageBase> TPage changeValue(Class<TPage> pageClass, String valueToChange){
        dropDown.click();

        var currencyLocator = String.format("//a[contains(@class, 'dropdown-item') and (text()='%s' or descendant::text()[contains(.,'%s')])]", valueToChange, valueToChange);
        if(dropDown.getText().contains(valueToChange)){
            return getPageInstance(pageClass);
        } else {
            driver.findElement(By.xpath(currencyLocator)).click();
        }

        return getPageInstance(pageClass);
    }

    /***
     * @param pageClass page object class
     * @param <TPage> page object type which extends PageBase
     * @return new page instance
     */
    private <TPage extends PageBase> TPage getPageInstance(Class<TPage> pageClass){
        try {
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
