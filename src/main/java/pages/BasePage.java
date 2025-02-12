package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public abstract class BasePage implements CheckIfYouAreOnPage {

    public final Page basePage;

    public BasePage(Page basePage) {
        this.basePage = basePage;
    }

    private static final String USERNAME = "[data-test='username']";
    private static final String PASSWORD = "[data-test='password']";
    private static final String LOGIN_BUTTON = "[data-test='login-button']";
    private static final String PRIMARY_HEADER = "[data-test='primary-header']";

    public void login(String username, String password) {
        basePage.locator(USERNAME).fill(username);
        basePage.locator(PASSWORD).fill(password);
        basePage.locator(LOGIN_BUTTON).click();
    }

    public void navigateTo(String url) {
        basePage.navigate(url);
    }

    @Override
    public void successfullyCheckIfYouAreOnPage(Page page) {
        page.waitForLoadState();
        assertThat(basePage.locator(PRIMARY_HEADER)).containsText("Swag Labs");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}
