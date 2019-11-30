package Page;

import Const.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends PageHeader {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /***
     * @param product Product type
     * @return search tab web element
     */
    public WebElement getSearchTabElement(Product product){
        var locator = String.format("//a[@data-name='%s']", product.name);
        return driver.findElement(By.xpath(locator));
    }

}
