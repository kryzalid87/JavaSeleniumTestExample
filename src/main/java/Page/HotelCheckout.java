package Page;

import Const.Tag;
import Element.CardDataElement;
import Element.CheckoutPersonalInformationElement;
import Helper.TestData.CardData;
import Helper.TestData.CheckoutData;
import Helper.TestData.PersonalInformationData;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class HotelCheckout extends PageBase {

    @FindBy(id = "countdown")
    private WebElement countdown;

    @FindBy(className = "hotel_details_panel__checkout")
    private WebElement checkoutDetails;

    @FindBy(id = "completeBooking")
    private WebElement completeBookingButton;

    private CardDataElement cardDataElement;
    private CheckoutPersonalInformationElement personalInformationElement;

    public HotelCheckout(WebDriver driver) {
        super(driver);

        cardDataElement = new CardDataElement(driver);
        personalInformationElement = new CheckoutPersonalInformationElement(driver);
    }

    public CheckoutData getCheckoutData() {
        var checkoutData = checkoutDetails.findElements(By.tagName(Tag.LI));
        return null;
    }

    /***
     * Fill personal data information on checkout page
     * @param personalInformationData PersonalInformationData
     */
    public void fillInPersonalInformation(PersonalInformationData personalInformationData) {
        personalInformationElement.fillInPersonalInformation(personalInformationData);
    }

    /***
     * Fill credit card data
     * @param cardData CarData
     */
    public void fillInCardData(CardData cardData) {
        cardDataElement.fillInCardData(cardData);
    }
}
