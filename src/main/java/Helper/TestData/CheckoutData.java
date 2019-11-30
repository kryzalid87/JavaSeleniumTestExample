package Helper.TestData;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CheckoutData {

    private String checkInDate;
    private String checkoutDate;
    private String nights;
    private String noOfRooms;
    private String total;

    public CheckoutData(String checkInDate, String checkoutDate, String nights, String noOfRooms, String total) {
        this.checkInDate = checkInDate;
        this.checkoutDate = checkoutDate;
        this.nights = nights;
        this.noOfRooms = noOfRooms;
        this.total = total;
    }
}
