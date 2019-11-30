package Page;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;

public class CustomPageFactory {

    /***
     * @param pageClass page object class
     * @param driver WebDriver instance
     * @param <TPage> page object type which extends PageBase
     * @return new page object instance
     */
    public static <TPage extends PageBase> TPage getInstance(Class<TPage> pageClass, WebDriver driver) {
        try {
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
