package pages;

import com.microsoft.playwright.Page;


public class LoginPage {

    private static final String USERNAME = "[data-test='username']";
    private static final String PASSWORD = "[data-test='password']";
    private static final String LOGIN_BUTTON = "[data-test='login-button']";
    public final Page loginPage;

    public LoginPage(Page page) {
        this.loginPage = page;
    }

    public void login(String username, String password) {
        loginPage.locator(USERNAME).fill(username);
        loginPage.locator(PASSWORD).fill(password);
        loginPage.locator(LOGIN_BUTTON).click();
    }

    public void navigateTo(String url) {
        loginPage.navigate(url);
    }
}
