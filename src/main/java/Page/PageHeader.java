package Page;

import Element.CurrencyDropDownElement;
import Element.LanguageDropDownElement;
import Element.LoginDropdownElement;
import Element.SearchElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

public class PageHeader extends PageBase {

    @Getter
    private SearchElement searchElement;

    @Getter
    private LoginDropdownElement loginDropdownElement;

    @Getter
    private CurrencyDropDownElement currencyDropdown;

    @Getter
    private LanguageDropDownElement languageDropdown;

    public PageHeader(WebDriver driver) {
        super(driver);

        searchElement = new SearchElement(driver);
        loginDropdownElement = new LoginDropdownElement(driver);
        currencyDropdown = new CurrencyDropDownElement(driver);
        languageDropdown = new LanguageDropDownElement(driver);
    }

    /***
     * @return new LoginPage instance
     */
    public LoginPage openLoginPage(){
        loginDropdownElement.getMyAccount().click();
        loginDropdownElement.getLoginButton().click();
        return new LoginPage(driver);
    }


}
