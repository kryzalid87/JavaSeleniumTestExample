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
     *
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
     *
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
     *
     */
    @ParameterizedTest
    @CsvSource({"EUR, 0.5", "GBP, 0.3,", "JPY, 10"})
    @DisplayName("There should be correct price displayed on hotel search result list - currency change")
    void thereShouldBeCurrencyChangePossible(String currencyCode, Double exchangeRate) {
        hotelsList = goTo(HotelsList.class, hotelSearchUrl);
        var initPrice = hotelsList.getStayPrice(hotelName);

        var currencyDropDown = hotelsList.getCurrencyDropdown();
        hotelsList = currencyDropDown.changeValue(HotelsList.class, currencyCode);
        var newPrice = hotelsList.getStayPrice(hotelName);

        Assertions.assertAll(
                () -> Assertions.assertEquals(currencyCode, newPrice.getCurrency()),
                () -> Assertions.assertEquals(initPrice.getAmount() * exchangeRate, newPrice.getAmount())
        );
    }

    /**
     *
     */
    @Test
    @DisplayName("Hotel details should be accessible from search result list")
    void oneShouldBeAbleToOpenHotelDetailsFromList() {
        hotelsList = goTo(HotelsList.class, hotelSearchUrl);
        var hotelDetails = hotelsList.openHotelDetails(hotelName);

        Assertions.assertAll(
                () -> Assertions.assertEquals(hotelName, hotelDetails.getHotelName()),
                () -> Assertions.assertTrue(hotelDetails.getFacilityList().containsAll(hotel.getFacilities()))
        );
    }
}
