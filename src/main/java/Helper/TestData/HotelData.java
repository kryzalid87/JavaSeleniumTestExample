package Helper.TestData;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class HotelData {

    private String hotelName;
    private String country;
    private String city;
    private double priceStart;
    private String currency;
    private String roomName;
    private List<String> facilities;
    private int adults;
    private int kids;
    private int howManyNights;
}
