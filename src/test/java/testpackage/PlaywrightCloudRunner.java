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
import properties.EnvironmentReader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @BeforeEach
    public void openPlaywrightPage() {
        String testName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

        BrowserType browserType;
        String browserPropertyValue = EnvironmentReader.getProperty("browser");

        if (browserPropertyValue.equals("firefox")) {
            browserType = playwright.firefox();
        } else if (browserPropertyValue.equals("chrome")) {
            browserType = playwright.chromium();
        } else {
            browserType = playwright.webkit();
        }

        BrowserType.LaunchOptions launchOptions = new BrowserType
                .LaunchOptions()
                .setHeadless(false);

        browser = browserType.launch(launchOptions);

        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("artifacts/video_" + testName)));

        page = browserContext.newPage();
        page.setViewportSize(1920, 1080);

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

    @AfterAll
    static void closeBrowsers() {
        browserContext.close();
        browser.close();
    }
}
