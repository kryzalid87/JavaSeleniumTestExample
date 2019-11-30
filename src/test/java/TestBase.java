import Helper.TestData.*;
import Helper.DataProvider;
import Helper.WebsiteUrlBuilder;
import Page.CustomPageFactory;
import Page.PageBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class TestBase {

    private static TestData testData;
    static HotelData hotel;
    static WebsiteData website;
    static PersonalInformationData personalInformation;
    static CardData cardData;
    static UserData userData;

    static String mainPageUrl;

    private WebDriver driver;

    @BeforeAll
    public static void beforeAll() throws IOException {
        testData = new DataProvider().getTestData("./test.json");
        hotel = testData.getHotel();
        website = testData.getWebsite();
        personalInformation = testData.getPersonalInformation();
        cardData = testData.getCardData();
        userData = testData.getUserData();

        mainPageUrl = WebsiteUrlBuilder.getHomePageUrl(website.getScheme(), website.getUrl());
    }

    @BeforeEach
    void beforeEach() {
        driver = WebDriverFactory.getInstance(testData.getWebDriver());
    }

    @AfterEach
    void AfterEach() {
        driver.quit();
    }

    /***
     *
     * @param pageClass - page class
     * @param url URL for page which should be opened
     * @param <TPage> page type
     * @return new TPage instance
     */
    <TPage extends PageBase> TPage goTo(Class<TPage> pageClass, String url) {
        driver.get(url);
        return CustomPageFactory.getInstance(pageClass, driver);
    }
}
