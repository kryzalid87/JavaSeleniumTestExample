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
    private FakeSmtpServer fakeSmtpServer;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();

        hotelCheckoutUrl = WebsiteUrlBuilder.getHotelCheckoutUrl(website.getScheme(), website.getUrl(), hotel.getHotelName());
        loginPageUrl = WebsiteUrlBuilder.getLoginPageUrl(website.getScheme(), website.getUrl());

        fakeSmtpServer = Mockito.mock(FakeSmtpServer.class);
        Mockito.when(fakeSmtpServer.CheckForMail()).thenReturn("");
    }

    /**
     * 1. Open website home page
     * 2. Search for a hotel for given search criteria
     * 3. Click on a Details button for a target hotel
     * 4. Choose cheapest room and click Book now button
     * 5. Fill all needed personal information, and credit card data
     * 6. Click on Complete booking button
     * 7. Check if no alert is displayed and if there is correct mail delivered to user mailbox
     */
    @Test
    @DisplayName("Guest should be able to book a room")
    void guestShouldBeAbleToBookARoom(){
        HomePage homePage = goTo(HomePage.class, mainPageUrl);

        var hotels = homePage.getSearchElement().searchForHotel(hotel.getCity(), now, now.plusDays(hotel.getHowManyNights()), hotel.getAdults(), hotel.getKids());
        var hotelDetails = hotels.openHotelDetails(hotel.getHotelName());
        var hotelCheckout = hotelDetails.goToHotelCheckoutForCheapestRoom(hotel.getRoomName());

        hotelCheckout.fillInPersonalInformation(personalInformation);
        hotelCheckout.fillInCardData(cardData);
        hotelCheckout.getCompleteBookingButton().click();

        Assertions.assertAll(
                () -> Assertions.assertFalse(hotelCheckout.isAlertPresent(), "Alert is displayed"),
                () -> Assertions.assertEquals("Room booked", fakeSmtpServer.CheckForMail())
        );
    }

    /**
     * 1. Login as given {USER}
     * 2. Open website home page
     * 3. Search for a hotel for given search criteria
     * 4. Click on a Details button for a target hotel
     * 5. Choose cheapest room and click Book now button
     * 6. Check if all personal information are pre-filled
     * 7. Fill in credit card data
     * 7. Click on Complete booking button
     * 8. Check if no alert is displayed and if there is correct mail delivered to user mailbox
     */
    @Ignore("There is a problem with login page page object - needs further investigation")
    @Test
    @DisplayName("Registered user should be able to book a room")
    void registeredUserShouldBeAbleToBookARoom(){
        LoginPage loginPage = goTo(LoginPage.class, loginPageUrl);
        loginPage.logIn(userData.getEmail(), userData.getPassword());

        var homePage = goTo(HomePage.class, mainPageUrl);

        var hotels = homePage.getSearchElement().searchForHotel(hotel.getCity(), now, now.plusDays(hotel.getHowManyNights()), hotel.getAdults(), hotel.getKids());
        var hotelDetails = hotels.openHotelDetails(hotel.getHotelName());
        var hotelCheckout = hotelDetails.goToHotelCheckoutForCheapestRoom(hotel.getRoomName());

        var personalInformationElement = hotelCheckout.getPersonalInformationElement();

        Assertions.assertAll(
                () -> Assertions.assertEquals(personalInformation.getFirstName(), personalInformationElement.getFirstNameInput().getText()),
                () -> Assertions.assertEquals(personalInformation.getLastName(), personalInformationElement.getLastNameInput().getText()),
                () -> Assertions.assertEquals(personalInformation.getEmail(), personalInformationElement.getEmailInput().getText()),
                () -> Assertions.assertEquals(personalInformation.getPhoneNumber(), personalInformationElement.getPhoneInput().getText())
        );

        hotelCheckout.fillInCardData(cardData);
        hotelCheckout.getCompleteBookingButton().click();

        Assertions.assertAll(
                () -> Assertions.assertFalse(hotelCheckout.isAlertPresent(), "Alert is displayed"),
                () -> Assertions.assertEquals("Room booked", fakeSmtpServer.CheckForMail())
        );
    }

    /**
     * 1. Open given {HOTEL_CHECKOUT} link in a browser
     * 2. Check that there is no data available
     */
    @Test
    @DisplayName("There should be not possibility to access checkout from a link")
    void userShouldNotBeAbleToOpenHotelCheckoutByLink() {
        var hotelCheckout = goTo(HotelCheckout.class, hotelCheckoutUrl);

        Assertions.assertAll(
                () -> Assertions.assertNull(hotelCheckout, "Hotel details are not displayed"),
                () -> Assertions.assertFalse(hotelCheckout.getCountdown().isDisplayed(), "Countdown element is displayed"),
                () -> Assertions.assertFalse(hotelCheckout.getCheckoutDetails().isDisplayed(), "Checkout data are displayed")
        );
    }
}
