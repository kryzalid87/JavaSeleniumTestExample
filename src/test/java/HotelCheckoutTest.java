import Helper.FakeSmtpServer;
import Helper.WebsiteUrlBuilder;
import Page.HotelCheckout;
import Page.HomePage;
import Page.LoginPage;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
class HotelCheckoutTest extends TestBase {

    private String hotelCheckoutUrl;
    private String loginPageUrl;

    private LocalDate now = LocalDate.now().plusMonths(1); //One month from now

    @BeforeEach
    void beforeEach() {
        super.beforeEach();

        hotelCheckoutUrl = WebsiteUrlBuilder.getHotelCheckoutUrl(website.getScheme(), website.getUrl(), hotel.getHotelName());
        loginPageUrl = WebsiteUrlBuilder.getLoginPageUrl(website.getScheme(), website.getUrl());
    }

    /**
     *
     */
    @Test
    @DisplayName("CGuest should be able to book a room")
    void guestShouldBeAbleToBookARoom(){
        HomePage homePage = goTo(HomePage.class, mainPageUrl);

        var hotels = homePage.getSearchElement().searchForHotel(hotel.getCity(), now, now.plusDays(hotel.getHowManyNights()), hotel.getAdults(), hotel.getKids());
        var hotelDetails = hotels.openHotelDetails(hotel.getHotelName());
        var hotelCheckout = hotelDetails.goToHotelCheckoutForCheapestRoom(hotel.getRoomName());

        hotelCheckout.fillInPersonalInformation(personalInformation);
        hotelCheckout.fillInCardData(cardData);
        hotelCheckout.getCompleteBookingButton().click();

        Assertions.assertFalse(hotelCheckout.isAlertPresent(), "Alert is displayed");
    }

    @Ignore("There is a problem with login page page object - needs further investigation")
    @Test
    @DisplayName("Registered user should be able to book a room")
    void registeredUserShouldBeAbleToBookARoom(){
        var fakeSmtpServer = Mockito.mock(FakeSmtpServer.class);
        Mockito.when(fakeSmtpServer.CheckForMail()).thenReturn("");

        LoginPage loginPage = goTo(LoginPage.class, loginPageUrl);
        loginPage.logIn(userData.getEmail(), userData.getPassword());

        var homePage = goTo(HomePage.class, mainPageUrl);

        var hotels = homePage.getSearchElement().searchForHotel(hotel.getCity(), now, now.plusDays(hotel.getHowManyNights()), hotel.getAdults(), hotel.getKids());
        var hotelDetails = hotels.openHotelDetails(hotel.getHotelName());
        var hotelCheckout = hotelDetails.goToHotelCheckoutForCheapestRoom(hotel.getRoomName());

        hotelCheckout.fillInPersonalInformation(personalInformation);
        hotelCheckout.fillInCardData(cardData);
        hotelCheckout.getCompleteBookingButton().click();

        Assertions.assertEquals("Room booked", fakeSmtpServer.CheckForMail());
    }

    /**
     *
     */
    @Test
    @DisplayName("There should be not possibility to access checkout from a link")
    void userShouldNotBeAbleToOpenHotelCheckoutByLink() {
        var hotelCheckout = goTo(HotelCheckout.class, hotelCheckoutUrl);

        Assertions.assertAll(
                () -> Assertions.assertFalse(hotelCheckout.getCountdown().isDisplayed(), "Countdown element is displayed"),
                () -> Assertions.assertFalse(hotelCheckout.getCheckoutDetails().isDisplayed(), "Checkout data are displayed")
        );
    }
}
