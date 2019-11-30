package Element;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginDropdownElement extends ElementBase {

    @Getter
    @FindBy(className = "dropdown-login")
    private WebElement myAccount;

    private By loginLocator = By.xpath(".//a[contains(@class,'dropdown-item') and text()='Login']");

    public LoginDropdownElement(WebDriver driver) {
        super(driver);
    }

    /***
     * @return login button web element
     */
    public WebElement getLoginButton(){
       return myAccount.findElement(loginLocator);
    }
}
