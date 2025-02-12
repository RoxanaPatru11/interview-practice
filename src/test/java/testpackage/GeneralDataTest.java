package testpackage;

import org.junit.jupiter.api.Test;
import properties.EnvironmentReader;

public class GeneralDataTest extends PlaywrightCloudRunner {
    private static final String username = EnvironmentReader.getProperty("username");
    private static final String password = EnvironmentReader.getProperty("password");
    private static final String URL = EnvironmentReader.getProperty("url");


    @Test
    public void firstTest() {
        basePage.login(username, password);


    }


}
