package Page;

import Helper.Price;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HotelsList extends PageHeader {

    @Getter
    @FindBy(className = "product-long-item")
    private List<WebElement> hotels;

    private By detailsButtonXpath = By.xpath(".//button[contains(text(), 'Details')]");
    private By startPriceXpath = By.xpath(".//div[@class='price']/span[@class='text-secondary']");

    public HotelsList(WebDriver driver) {
        super(driver);
    }

    /***
     * @param hotelName hotel name
     * @return new HotelDetail instance if hotel was found
     */
    public HotelDetail openHotelDetails(String hotelName) {
        var hotel = getHotelDetailsByName(hotelName);
        if (hotel.isDisplayed()) {
            var detailsButton = hotel.findElement(detailsButtonXpath);
            detailsButton.click();
            return new HotelDetail(driver);
        }
        throw new ElementNotVisibleException(String.format("Hotel %s was not found", hotelName));
    }

    /***
     * @param hotelName hotel name
     * @return new price object for found hotel was found
     */
    public Price getStayPrice(String hotelName) {
        var hotel = getHotelDetailsByName(hotelName);
        if (hotel.isDisplayed()) {
            var price = hotel.findElement(startPriceXpath).getText().split(" ");
            return new Price(price[0], Double.valueOf(price[1]));
        }
        throw new ElementNotVisibleException(String.format("Hotel %s was not found", hotelName));
    }

    /***
     * @param hotelName hotel name
     * @return hotel details web element
     */
    private WebElement getHotelDetailsByName(String hotelName) {
        return hotels.stream()
                .filter(x -> x.getText().contains(hotelName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("There was no hotel displayed with name %s", hotelName)));
    }

}
