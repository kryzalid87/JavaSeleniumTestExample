package Element;

import org.openqa.selenium.WebDriver;

public class CurrencyDropDownElement extends DropDownElement {

    public CurrencyDropDownElement(WebDriver driver) {
        super(driver, "dropdownCurrency");
    }
}
