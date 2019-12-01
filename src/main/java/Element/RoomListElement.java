package Element;

import Const.TestString;
import Page.HotelCheckout;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Comparator;
import java.util.List;

public class RoomListElement extends ElementBase {

    @Getter
    @FindBy(className = "room-item")
    private List<WebElement> rooms;

    private By priceSelector = new By.ByCssSelector("p.price > span.number");

    public RoomListElement(WebDriver driver) {
        super(driver);
    }

    /***
     * Book cheapest room available
     * @param roomName room name
     * @return new HotelCheckout page instance
     */
    public HotelCheckout bookCheapestRoom(String roomName){

        var roomToBook =  rooms.stream()
                .filter( el -> el.getText().contains(roomName))
                .min(Comparator.comparingDouble(el -> Double.parseDouble(el.findElement(priceSelector).getText()))
                ).get();

        safetyClick(roomToBook);
        var bookNowButton = roomToBook.findElement(By.xpath(String.format("//button[text()='%s']", TestString.BOOK_NOW)));
        safetyClick(bookNowButton);

        return new HotelCheckout(driver);
    }
}
