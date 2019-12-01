package Helper.TestData;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WebDriverData {

    private Boolean useSeleniumGrid;
    private String type;
    private String remoteHub;
    private String driverPath;
    private int timeoutInSeconds;
}
