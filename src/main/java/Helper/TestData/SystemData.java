package Helper.TestData;

public class SystemData {

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
