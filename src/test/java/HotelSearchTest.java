import Helper.WebsiteUrlBuilder;
import Element.SearchElement;
import Page.HotelsList;
import Page.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

class HotelSearchTest extends TestBase {

    private LocalDate now = LocalDate.now().plusMonths(1); //One month from now

    private HotelsList hotelsList;

    private String hotelName;
    private String hotelSearchUrl;
    private String mainPageUrl;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();

        hotelSearchUrl = WebsiteUrlBuilder.getHotelSearchResultUrl(website.getScheme(), website.getUrl(), hotel.getCountry(), hotel.getCity(), now, hotel.getHowManyNights(), hotel.getAdults(), hotel.getKids());
        mainPageUrl = WebsiteUrlBuilder.getHomePageUrl(website.getScheme(), website.getUrl());

        hotelName = hotel.getHotelName();
    }

    /**
     * 1. Open website home page
     * 2. Fill in all needed search data, where search for a destination is made by city name
     * 3. Check if list of hotels contains search results
     */
    @Test
    @DisplayName("There should be possible to search for hotel by city name")
    void oneShouldBeAbleToSearchForHotelRooms(){
        HomePage homePage = goTo(HomePage.class, mainPageUrl);
        SearchElement searchElement = homePage.getSearchElement();

        var hotels = searchElement.searchForHotel(hotel.getCity(), now, now.plusDays(hotel.getHowManyNights()), hotel.getAdults(), hotel.getKids());

        Assertions.assertTrue( hotels.getHotels().size() > 0);
    }

    /**
     * 1. Open website home page
     * 2. Search for a hotel for given search criteria
     * 3. Check if price have correct currency and amount
     */
    @Test
    @DisplayName("There should be price displayed on hotel search result list")
    void hotelShouldHaveCorrectPrice() {
        hotelsList = goTo(HotelsList.class, hotelSearchUrl);
        var price = hotelsList.getStayPrice(hotelName);

        Assertions.assertAll(
                () -> Assertions.assertEquals(hotel.getPriceStart(), price.getAmount()),
                () -> Assertions.assertEquals(hotel.getCurrency(), price.getCurrency())
        );
    }

    /**
     * 1. Open website home page
     * 2. Search for a hotel for given search criteria
     * 3. Change currency
     * 3. Check if price is displayed in chosen currency, and the amount is recalculated with exchange rate
     */
    @ParameterizedTest
    @CsvSource({"EUR, 1.1", "GBP, 1.3,", "JPY, 109"})
    @DisplayName("There should be correct price displayed on hotel search result list - currency change")
    void thereShouldBeCurrencyChangePossible(String currencyCode, Double exchangeRate) {
        hotelsList = goTo(HotelsList.class, hotelSearchUrl);
        var initPrice = hotelsList.getStayPrice(hotelName);

        var currencyDropDown = hotelsList.getCurrencyDropDown();
        hotelsList = currencyDropDown.changeValue(HotelsList.class, currencyCode);
        var newPrice = hotelsList.getStayPrice(hotelName);

        Assertions.assertAll(
                () -> Assertions.assertEquals(currencyCode, newPrice.getCurrency()),
                () -> Assertions.assertEquals(initPrice.getAmount() * exchangeRate, newPrice.getAmount())
        );
    }

    /**
     * 1. Open website home page
     * 2. Search for a hotel for given search criteria
     * 3. Check if hotel have correct name, facilities, and if there are any room available for booking
     */
    @Test
    @DisplayName("Hotel details should be accessible from search result list")
    void oneShouldBeAbleToOpenHotelDetailsFromList() {
        hotelsList = goTo(HotelsList.class, hotelSearchUrl);
        var hotelDetails = hotelsList.openHotelDetails(hotelName);

        Assertions.assertAll(
                () -> Assertions.assertEquals(hotelName, hotelDetails.getHotelName(), "Hotel details have correct hotel name"),
                () -> Assertions.assertTrue(hotelDetails.getFacilityList().containsAll(hotel.getFacilities()), "Hotel details have correct facilities"),
                () -> Assertions.assertTrue(hotelDetails.getRoomListElement().getRooms().size() > 0, "There are rooms available to be booked")
        );
    }
}
