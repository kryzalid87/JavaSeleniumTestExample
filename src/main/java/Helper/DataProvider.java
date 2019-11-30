package Helper;

import Helper.TestData.TestData;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class DataProvider {

    /***
     * Create TestData object from given test file
     * @param testFilePath path of test JSON file
     * @return new TestData object
     * @throws IOException
     */
    public TestData getTestData(String testFilePath) throws IOException {
        try(var reader = new FileReader(testFilePath)){
            return new Gson().fromJson(reader, TestData.class);
        } catch (IOException e) {
            throw e;
        }
    }
}
