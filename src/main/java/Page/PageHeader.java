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
    private CurrencyDropDownElement currencyDropDown;

    @Getter
    private LanguageDropDownElement languageDropDown;

    PageHeader(WebDriver driver) {
        super(driver);

        searchElement = new SearchElement(driver);
        loginDropdownElement = new LoginDropdownElement(driver);
        currencyDropDown = new CurrencyDropDownElement(driver);
        languageDropDown = new LanguageDropDownElement(driver);
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
