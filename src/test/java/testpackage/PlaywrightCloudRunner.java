package testpackage;

import annotations.PlaywrightPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class PlaywrightCloudRunner {
    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext browserContext;
    protected static Page page;

    @PlaywrightPage
    public LoginPage loginPage;
    @PlaywrightPage
    public HomePage homePage;
    @PlaywrightPage
    public CartPage cartPage;
    Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage("pages"))
            .setScanners(new SubTypesScanner()));

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

        initPages(this, page);
    }

    public void initPages(Object object, Page page) {
        Class<?> clazz = object.getClass().getSuperclass().getName().contains("PlaywrightCloudRunner") ?
                object.getClass().getSuperclass() : object.getClass().getSuperclass().getSuperclass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(PlaywrightPage.class)) {
                Class<?>[] type = {Page.class};
                try {
                    if (reflections.getSubTypesOf(field.getType()).size() >= 2) {
                        var subTypes = reflections.getSubTypesOf(field.getType()).stream().toList();
                        field.set(object, subTypes.get(0).getConstructor(type).newInstance(page));
                    } else {
                        field.set(object, field.getType().getConstructor(type).newInstance(page));
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
