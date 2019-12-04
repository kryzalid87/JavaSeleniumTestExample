import Const.Product;
import Page.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HomePageTest extends TestBase {

    /**
     * 1. Open website home page
     * 2. Choose another language from the language change drop-down
     * 3. Check if website elements are translated
     */
    @ParameterizedTest
    @CsvSource({"French, Hôtels, Rechercher",
            "German, Hotels, Suche",
            "Spanish, Hoteles, Buscar"})
    @DisplayName("There should be website language change possible")
    void thereShouldBeLanguageChangePossible(String language, String hotelsTabName, String searchButtonText) {
        var homePage = goTo(HomePage.class, mainPageUrl);

        homePage = homePage.getLanguageDropDown().changeValue(HomePage.class, language);

        var tab = homePage.getSearchTabElement(Product.HOTELS);
        var searchButton = homePage.getSearchElement().getHotelSearchButton();

        Assertions.assertAll(
                () -> Assertions.assertTrue(hotelsTabName.equalsIgnoreCase(tab.getText()), "Hotel name is correctly translated"),
                () -> Assertions.assertTrue(searchButtonText.equalsIgnoreCase(searchButton.getText()), "Search button is correctly translated")
        );
    }
}
