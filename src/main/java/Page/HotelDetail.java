package Page;

import Const.Tag;
import Element.RoomListElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class HotelDetail extends PageHeader {

    @FindBy(tagName = Tag.H2)
    WebElement hotelName;

    @FindBy(id = "detail-content-sticky-nav-01")
    private WebElement overview;

    @Getter
    private RoomListElement roomListElement;

    HotelDetail(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(hotelName));

        roomListElement = new RoomListElement(driver);
    }

    /**

     * @return hotel Name
     */
    public String getHotelName(){
        return hotelName.getText();
    }

    /**

     * @return list of facilities
     */
    public List<String> getFacilityList() {
        var facilities = overview.findElements(By.tagName(Tag.LI));

        return facilities.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /***
     * Book cheapest room available
     * @param roomName Room name
     * @return new HoteCheckout page instance
     */
    public HotelCheckout goToHotelCheckoutForCheapestRoom(String roomName){
        return roomListElement.bookCheapestRoom(roomName);
    }
}


