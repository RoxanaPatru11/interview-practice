package testpackage;

import enums.HomeMenu;
import enums.ProductsEnv1;
import enums.ProductsEnv2;
import org.junit.jupiter.api.BeforeEach;
import properties.EnvironmentReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GeneralDataTest extends PlaywrightCloudRunner {
    private static final String username = EnvironmentReader.getProperty("username");
    private static final String password = EnvironmentReader.getProperty("password");
    private static final String URL = EnvironmentReader.getProperty("url");

    @BeforeEach
    public void setUp() {
        navigate(URL);
        userLogin(username, password);
    }

    public void navigate(String url) {
        loginPage.navigateTo(url);
    }

    public void userLogin(String username, String password) {
        loginPage.login(username, password);
        homePage.successfullyCheckIfYouAreOnPage();
    }

    public void navigateToMenu(HomeMenu homeMenu){
        homePage.openMenu();
        homePage.navigateToMenu(homeMenu);
    }

    public void navigateToCart(){
        homePage.navigateToMenu(HomeMenu.SHOPPING_CART);
    }

    public List<Map<String, Object>> removeItemFromTheList(List<Map<String, Object>> existingProductsInCart){
        Iterator<Map<String, Object>> iterator = existingProductsInCart.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> product = iterator.next();
            if ("Sauce Labs Backpack".equals(product.get("productName"))) {
                iterator.remove();
                break;
            }
        }

        return existingProductsInCart;
    }
}
