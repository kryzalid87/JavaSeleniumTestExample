import Helper.WebsiteUrlBuilder;
import Page.HotelDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HotelDetailsTest extends TestBase {

    private String hotelDetailUrl;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();

        hotelDetailUrl = WebsiteUrlBuilder.getHotelDetailsUrl(website.getScheme(), website.getUrl(), hotel.getHotelName());
    }

    /**
     * 1. Open website home page
     * 2. Check if hotel name, facilities are available. There should be no rooms available
     */
    @Test
    @DisplayName("Hotel details should be available via URL")
    void hotelDetailsShouldBeAccessibleViaUrl(){
        var hotelDetail = goTo(HotelDetail.class, hotelDetailUrl);

        Assertions.assertNotNull(hotelDetail, "Hotel details page have all needed elements");
        Assertions.assertAll(
                () -> Assertions.assertEquals(hotel.getHotelName(), hotelDetail.getHotelName(), "Hotel name is displayed"),
                () -> Assertions.assertTrue(hotelDetail.getFacilityList().containsAll(hotel.getFacilities()), "Hotel facilities are displayed"),
                () -> Assertions.assertEquals(0, hotelDetail.getRoomListElement().getRooms().size(), "No rooms are available")
        );
    }
}
