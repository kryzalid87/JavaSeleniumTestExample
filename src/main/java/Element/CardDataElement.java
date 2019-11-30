package Element;

import Helper.TestData.CardData;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CardDataElement extends ElementBase {

    @FindBy(id = "cardHolderName")
    private WebElement cardHolderNameElement;

    @FindBy(id = "cardNumber")
    private WebElement cardNumberElement;

    @FindBy(id = "cardCVC")
    private WebElement cardCVCElement;

    public CardDataElement(WebDriver driver) {
        super(driver);
    }

    /***
     * Fill credit card data
     * @param cardData - credit card data
     */
    public void fillInCardData(CardData cardData){
        fillInCardData(cardData.getNameOnCard(),
        cardData.getCardNumber(),
        cardData.getCvc());
    }

    /***
     * Fill credit card data
     * @param nameOnCard - name on credit card
     * @param cardNumber - credit card number
     * @param cvc - CVC number
     */
    private void fillInCardData(String nameOnCard, String cardNumber, int cvc){
        cardHolderNameElement.sendKeys(nameOnCard);
        cardNumberElement.sendKeys(cardNumber);
        cardCVCElement.sendKeys(String.valueOf(cvc));
    }
}
