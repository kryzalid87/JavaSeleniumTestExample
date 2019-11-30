package Helper;

import okhttp3.HttpUrl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WebsiteUrlBuilder {

    private final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /***
     *
     * @param scheme website scheme
     * @param baseUrl website base URL
     * @param country hotel country
     * @param city hotel city
     * @param startDate when stay will start
     * @param howManyDays how many nights in the hotel
     * @param adults how many adults will stay
     * @param children how many children will stay
     * @return hotels search result URL
     */
    public static String getHotelSearchResultUrl(String scheme, String baseUrl, String country, String city, LocalDate startDate, int howManyDays, int adults, int children) {
        var start = dateFormat.format(startDate);
        var end = dateFormat.format(startDate.plusDays(howManyDays));
        return new HttpUrl.Builder()
                .scheme(scheme)
                .host(baseUrl)
                .addPathSegments("thhotels/search")
                .addPathSegment(country)
                .addPathSegment(city)
                .addPathSegment(start)
                .addPathSegment(end)
                .addPathSegment(Integer.toString(adults))
                .addPathSegment(Integer.toString(children))
                .toString();
    }

    /***
     *
     * @param scheme website scheme
     * @param baseUrl website base URL
     * @param hotelName hotel name
     * @return URL to access given hotel checkout
     */
    public static String getHotelCheckoutUrl(String scheme, String baseUrl, String hotelName) {
        return getHotelActionUrl(scheme, baseUrl, hotelName, "thhotels/checkout");
    }

    /***
     *
     * @param scheme website scheme
     * @param baseUrl website base URL
     * @param hotelName hotel name
     * @return URL to access given hotel details
     */
    public static String getHotelDetailsUrl(String scheme, String baseUrl, String hotelName) {
        return getHotelActionUrl(scheme, baseUrl, hotelName, "thhotels/detail");
    }

    /***
     *
     * @param scheme website scheme
     * @param baseUrl website base URL
     * @param hotelName hotel name
     * @param actionSegment URL segment to perform URL build
     * @return URL to access given hotel action
     */
    private static String getHotelActionUrl(String scheme, String baseUrl, String hotelName, String actionSegment) {
        return new HttpUrl.Builder()
                .scheme(scheme)
                .host(baseUrl)
                .addPathSegments(actionSegment)
                .addPathSegment(hotelName.replace(" ", "-"))
                .toString();
    }

    /***
     *
     * @param scheme website scheme
     * @param baseUrl website base URL
     * @return home page URL
     */
    public static String getHomePageUrl(String scheme, String baseUrl) {
        return new HttpUrl.Builder()
                .scheme(scheme)
                .host(baseUrl)
                .toString();
    }

    /***
     *
     * @param scheme website scheme
     * @param baseUrl website base URL
     * @return home page URL
     */
    public static String getLoginPageUrl(String scheme, String baseUrl) {
        return new HttpUrl.Builder()
                .scheme(scheme)
                .addPathSegment("login")
                .host(baseUrl)
                .toString();
    }
}

