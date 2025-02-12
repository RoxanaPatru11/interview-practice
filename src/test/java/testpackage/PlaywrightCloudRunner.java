package testpackage;

import annotations.PlaywrightPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.BasePage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class PlaywrightCloudRunner {
    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext browserContext;
    protected static Page page;

    @PlaywrightPage
    protected BasePage basePage;

    @BeforeAll
    public static void createPlaywrightInstance() {
        playwright = Playwright.create();
    }

    @AfterAll
    static void closeBrowsers() {
        browserContext.close();
        browser.close();
    }

    @BeforeEach
    public void openPlaywrightPage() {
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();

        //BasePage basePage= new BasePage(page);

        initPage(this, page);
    }

    private void initPage(Object object, Page page) {
        Class<?> clazz = object.getClass().getSuperclass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PlaywrightPage.class)) {
                Class<?>[] type = {Page.class};
                try {
                    field.set(this, field.getType().getConstructor(type).newInstance(page));
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                         NoSuchMethodException e) {
                    System.out.println("Did not manage to call constructor for playwright page with name " + field.getName());
                }
            }
        }
    }
}
