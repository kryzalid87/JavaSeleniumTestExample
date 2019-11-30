package Element;

import Page.HotelsList;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchElement extends ElementBase {

    private final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FindBy(id ="checkin")
    private WebElement checkIn;

    @FindBy(id ="checkout")
    private WebElement checkOut;

    @FindBy(name ="adults")
    private WebElement adults;

    @FindBy(name ="children")
    private WebElement children;

    @Getter
    private Select2Element select2Element;

    public SearchElement(WebDriver driver) {
        super(driver);

        select2Element = new Select2Element(driver);
    }

    /***
     * Search for hotel on the main page
     * @param city - hotel city
     * @param checkinDate - check-in date
     * @param checkoutDate - check-out date
     * @param howManyAdults - how many adults will stay
     * @param howManyChildren - how many children will stay
     * @return new HotelList page instance
     */
    public HotelsList searchForHotel(String city, LocalDate checkinDate, LocalDate checkoutDate, int howManyAdults, int howManyChildren){
        select2Element.searchForValue(city);

        checkIn.sendKeys(dateFormat.format(checkinDate));
        checkOut.sendKeys(dateFormat.format(checkoutDate));
        setValue(adults, howManyAdults);
        setValue(children, howManyChildren);

        getHotelSearchButton().click();

        return new HotelsList(driver);
    }

    /***
     * @return hotel search button
     */
    public WebElement getHotelSearchButton(){
        return getSearchButton("HOTELS");
    }

    /***
     * @param elementName - button element name
     * @return search button
     */
    public WebElement getSearchButton(String elementName){
        var locator = String.format("//form[@name='%s']//button[@type='submit']", elementName);
        return driver.findElement(By.xpath(locator));
    }
}
