package testpackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class Run2Test extends GeneralDataTest{
    private static final String URL = "file:///C:/Users/roxana.patru/IdeaProjects/automated-tests/web1.html";
    private static final String URL2 = "https://the-internet.herokuapp.com/iframe";

    @BeforeEach
    public void setUp() {}

    @Test
    public void handleAlerts(){
        navigate(URL);
        alertsPage.handlePopups();
        alertsPage.createRequest();
    }

    @Test
    public void frames(){
        navigate(URL2);
        alertsPage.handleFrames();
    }







}
