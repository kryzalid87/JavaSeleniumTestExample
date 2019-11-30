import Const.Product;
import Page.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HomePageTest extends TestBase {

    /**
     *
     */
    @ParameterizedTest
    @CsvSource({"French, Hôtels, Rechercher",
            "German, Hotels, Suche",
            "Spanish, Hoteles, Buscar"})
    @DisplayName("There should be price displayed on hotel search result list")
    void thereShouldBeLanguageChangePossible(String language, String hotelsTabName, String searchButtonText) {
        var homePage = goTo(HomePage.class, mainPageUrl);

        homePage = homePage.getLanguageDropdown().changeValue(HomePage.class, language);

        var tab = homePage.getSearchTabElement(Product.HOTELS);
        var searchButton = homePage.getSearchElement().getHotelSearchButton();

        Assertions.assertAll(
                () -> Assertions.assertTrue(hotelsTabName.equalsIgnoreCase(tab.getText())),
                () -> Assertions.assertTrue(searchButtonText.equalsIgnoreCase(searchButton.getText()))
        );
    }
}
