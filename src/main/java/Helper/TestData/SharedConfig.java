package Helper.TestData;

import lombok.Getter;
import lombok.Setter;

public class SharedConfig {

    @Getter @Setter
    private static int webDriverTimout = 30;

    /***
     * @return system property hubUrl
     */
    public static String getHubAddress(){
        return System.getProperty("remoteHub");
    }

    /***
     * @return return system property testFile, or default test file "./test.json" when system variable is not specified
     */
    public static String getTestFile(){
        var testFile = System.getProperty("testFile");
        return (testFile != null) ? testFile : "./test.json";
    }
}
