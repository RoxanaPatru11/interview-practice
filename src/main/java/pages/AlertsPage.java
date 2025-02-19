package pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class AlertsPage {
    public final Page alertsPage;

    public AlertsPage(Page page){
        this.alertsPage = page;
    }
    private static final String CREATE_REQUEST="[id='createRequestButton']";

    private static final String DESCRIPTION="[id='description']";


    public void handlePopups(){
        alertsPage.onceDialog(alert->{
            String message= alert.message();
            System.out.println(message);
            alert.accept();
        });

        System.out.println("aaa");
    }

    public void createRequest(){
        alertsPage.locator(CREATE_REQUEST).click();
        alertsPage.locator(DESCRIPTION).fill("my description");
    }

    public void handleFrames(){
        FrameLocator frameLocator= alertsPage.frameLocator("[id='mce_0_ifr']");
        String innerText= frameLocator.locator("[id='tinymce']").innerText();
        System.out.println(innerText);
    }
}
