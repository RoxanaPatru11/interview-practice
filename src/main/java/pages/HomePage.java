package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.ElementState;
import com.microsoft.playwright.options.LoadState;
import enums.HomeMenu;
import enums.ProductsEnv1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.*;

public class HomePage implements CheckIfYouAreOnPage {

    private static final String PRIMARY_HEADER = "[data-test='primary-header']";
    private static final String MENU_BUTTON = "[id='react-burger-menu-btn']";
    private static final String INVENTORY_ITEM_DESCRIPTION = "[data-test='inventory-item-description']";
    private static final String ITEM_PRICE = "[data-test='inventory-item-price']";
    private static final String ADD_TO_CART_BUTTON = "[data-test^='add-to-cart-']";
    private static final String REMOVE_BUTTON = "[data-test^='remove-']";
    private static final String SHOPPING_CART_BADGE = "[id='shopping_cart_container']";

    public final Page homepage;

    public HomePage(Page homepage) {
        this.homepage = homepage;
    }

    @Override
    public void successfullyCheckIfYouAreOnPage(Page page) {
        page.waitForLoadState();

        assertThat(homepage.locator(PRIMARY_HEADER)).containsText("Swag Labs", new LocatorAssertions.ContainsTextOptions().setTimeout(5000));
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @Override
    public void successfullyCheckIfYouAreOnPage() {
        successfullyCheckIfYouAreOnPage(homepage);
    }

    public void navigateToMenu(HomeMenu homeMenuName) {
        homepage.locator(homeMenuName.getSelector()).click();
    }

    public void openMenu() {
        homepage.locator(PRIMARY_HEADER).locator(MENU_BUTTON).click();
    }

    public List<Map<String, Object>> addProductsToCart(List<ProductsEnv1> products, List<Map<String, Object>> existingProductsInCart) {
        int noOfExpectedProductsInCart = extractNoOfExistingProductsFromCart();

        for (ProductsEnv1 product : products) {
            assertListIsNotNull(INVENTORY_ITEM_DESCRIPTION, 7000, homepage);
            ElementHandle itemDescription = homepage.querySelectorAll(INVENTORY_ITEM_DESCRIPTION).stream().filter(element -> element.innerText()
                    .contains(product.getProductName())).toList().get(0);

            itemDescription.querySelector(ADD_TO_CART_BUTTON).click();

            assertTrue(itemDescription.querySelector(REMOVE_BUTTON).isEnabled());

            int noOfActualProducts = Integer.parseInt(homepage.querySelector(SHOPPING_CART_BADGE).innerText());
            noOfExpectedProductsInCart++;
            assertEquals("Product wasn't added to the cart", noOfExpectedProductsInCart, noOfActualProducts);

            double itemPrice = Double.valueOf(itemDescription.querySelector(ITEM_PRICE).innerText().replace("$", ""));

            Map<String, Object> productDetails = new HashMap<>();
            productDetails.put("productName", product.getProductName());
            productDetails.put("productPrice", itemPrice);
            productDetails.put("quantity", 1);

            existingProductsInCart.add(productDetails);
        }

        return existingProductsInCart;
    }

    public void removeProductFromCartUsingHomePage(ProductsEnv1 product) {
        assertListIsNotNull(INVENTORY_ITEM_DESCRIPTION, 5000, homepage);
        ElementHandle itemDescription = homepage.querySelectorAll(INVENTORY_ITEM_DESCRIPTION).stream().filter(element -> element.innerText()
                .contains(product.getProductName())).toList().get(0);

        assertTrue(itemDescription.querySelector(REMOVE_BUTTON).isEnabled());
        int noOfExistingProducts = extractNoOfExistingProductsFromCart();

        itemDescription.querySelector(REMOVE_BUTTON).click();
        assertTrue(itemDescription.querySelector(ADD_TO_CART_BUTTON).isEnabled());

        assertEquals("Product wasn't removed from the cart", noOfExistingProducts - 1, extractNoOfExistingProductsFromCart());
    }

    int extractNoOfExistingProductsFromCart() {
        int noOfExistingProducts = 0;
        String existingProducts = homepage.querySelector(SHOPPING_CART_BADGE).innerText();

        if (!existingProducts.isEmpty()) {
            noOfExistingProducts = Integer.parseInt(existingProducts);
        }

        return noOfExistingProducts;
    }


    public void assertElementNotNull(String selector, int timeInMillis, Page page) {
        await().atMost(ofMillis(timeInMillis)).pollInterval(ofSeconds(1)).untilAsserted(() -> {
            ElementHandle targetElement = page.querySelector(selector);
            assertNotNull("selector is null", targetElement);
            targetElement.waitForElementState(ElementState.STABLE);
            targetElement.waitForElementState(ElementState.EDITABLE);
        });
    }

    public void assertListIsNotNull(String selector, int timeInMillis, Page page) {
        await().atMost(ofMillis(timeInMillis)).pollInterval(ofSeconds(1)).untilAsserted(() -> {
            List<ElementHandle> elementHandleList = page.querySelectorAll(selector);
            assertNotNull("selector is null", elementHandleList);
            assertFalse("List is empty", elementHandleList.isEmpty());
        });
    }

}


