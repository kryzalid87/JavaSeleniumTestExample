import Helper.WebsiteUrlBuilder;
import Page.HotelDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HotelDetailsTest extends TestBase {

    private String hotelDetailUrl;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();

        hotelDetailUrl = WebsiteUrlBuilder.getHotelDetailsUrl(website.getScheme(), website.getUrl(), hotel.getHotelName());
    }

    /**
     *
     */
    @Test
    void hotelDetailsShouldBeAccessibleViaUrl(){
        var hotelDetail = goTo(HotelDetail.class, hotelDetailUrl);
        Assertions.assertAll(
                () -> Assertions.assertEquals(hotel.getHotelName(), hotelDetail.getHotelName(), "Hotel name is displayed"),
                () -> Assertions.assertTrue(hotelDetail.getFacilityList().containsAll(hotel.getFacilities()), "Hotel facilities are displayed")
        );
    }
}
