package Element;

import Helper.TestData.PersonalInformationData;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CheckoutPersonalInformationElement extends ElementBase {

    @FindBy(id = "first_name")
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    private WebElement lastNameInput;

    @FindBy(xpath = "//form[@name='bookingForm']//input[@id='email']")
    private WebElement emailInput;

    @FindBy(id = "phone_number")
    private WebElement phoneInput;

    public CheckoutPersonalInformationElement(WebDriver driver) {
        super(driver);
    }

    /***
     * Fill in personal information
     * @param personalInformationData PersonalInformationData
     */
    public void fillInPersonalInformation(PersonalInformationData personalInformationData) {
        fillInPersonalInformation(personalInformationData.getFirstName(),
                personalInformationData.getLastName(),
                personalInformationData.getEmail(),
                personalInformationData.getPhoneNumber());
    }

    /***
     *
     * @param firstName user first name
     * @param lastName user last name
     * @param email user e-mail
     * @param phoneNumber user phone number
     */
    private void fillInPersonalInformation(String firstName, String lastName, String email, String phoneNumber) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        phoneInput.sendKeys(phoneNumber);
    }
}
