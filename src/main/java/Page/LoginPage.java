package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageHeader {

    @FindBy(name = "username")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class, 'loginbtn')]")
    private WebElement loginButton;

    LoginPage(WebDriver driver) {
        super(driver);
    }

    /***
     * Login with given credentials
     * @param email user e-mail
     * @param password user password
     */
    public void logIn(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
