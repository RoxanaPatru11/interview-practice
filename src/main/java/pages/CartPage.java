package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import enums.ClientDetails;
import enums.ProductsEnv1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CartPage {
    public final Page cartPage;

    public CartPage(Page page) {
        this.cartPage = page;
    }

    private static final String CONTINUE_SHOPPING = "[data-test='continue-shopping']";
    private static final String CHECKOUT_ORDER = "[data-test='checkout']";
    private static final String INVENTORY_ITEM = "[data-test='inventory-item']";
    private static final String ITEM_QUANTITY = "[data-test='item-quantity']";
    private static final String ITEM_PRICE = "[data-test='inventory-item-price']";
    private static final String REMOVE_BUTTON = "[data-test^='remove-']";
    private static final String PRIMARY_HEADER = "[data-test='primary-header']";
    private static final String FIRST_NAME = "[data-test='firstName']";
    private static final String LAST_NAME = "[data-test='lastName']";
    private static final String POSTAL_CODE = "[data-test='postalCode']";
    private static final String CONTINUE_BUTTON = "[data-test='continue']";
    private static final String SUBTOTAL_PRICE = "[data-test='subtotal-label']";
    private static final String TAX = "[data-test='tax-label']";
    private static final String TOTAL = "[data-test='total-label']";
    private static final String FINISH = "[data-test='finish']";
    private static final String COMPLETE_HEADER = "[data-test='complete-header']";
    private static final String BACK_TO_PRODUCTS = "[data-test='back-to-products']";


    public void checkTheCart(List<Map<String, Object>> expectedProducts) {
        Iterator<Map<String, Object>> iterator = expectedProducts.iterator();

        List<ElementHandle> items = cartPage.querySelectorAll(INVENTORY_ITEM);
        assertEquals("no of products is not the same", expectedProducts.size(), items.size());

        for (ElementHandle itemDescription : items) {
            while (iterator.hasNext()) {
                Map<String, Object> product = iterator.next();

                if (itemDescription.innerText().contains(product.get("productName").toString())) {

                    double expectedPrice = Double.parseDouble(product.get("productPrice").toString());
                    double getPrice = Double.parseDouble(Arrays.stream(itemDescription.querySelector(ITEM_PRICE).innerText().split("\\$")).toList().get(1));
                    assertEquals("Prices are not the same", expectedPrice, getPrice, 0.001);

                    int expectedQuantity = Integer.parseInt(product.get("quantity").toString());
                    int getQuantity = Integer.parseInt(itemDescription.querySelector(ITEM_QUANTITY).innerText());
                    assertEquals("Quantities are not the same", expectedQuantity, getQuantity);
                }
            }
        }
    }

    public void continueShopping() {
        cartPage.click(CONTINUE_SHOPPING);
        assertThat(cartPage.locator(PRIMARY_HEADER)).containsText("Swag Labs", new LocatorAssertions.ContainsTextOptions().setTimeout(5000));

    }

    public void checkoutOrder(ClientDetails clientDetails, List<Map<String, Object>> expectedProducts) {
        cartPage.locator(CHECKOUT_ORDER).click();
        clientInformations(clientDetails);
        cartPage.locator(CONTINUE_BUTTON).click();
        orderOverview(expectedProducts);
        orderConfirmation();
    }

    void clientInformations(ClientDetails clientDetails) {
        cartPage.locator(FIRST_NAME).fill(clientDetails.getFirstName());
        cartPage.locator(LAST_NAME).fill(clientDetails.getLastName());
        cartPage.locator(POSTAL_CODE).fill(clientDetails.getPostalCode());
    }


    public void orderOverview(List<Map<String, Object>> expectedProducts) {
        double subtotalPrice = Double.parseDouble(Arrays.stream(cartPage.locator(SUBTOTAL_PRICE).innerText().split("\\$")).toList().get(1));
        double tax = Double.parseDouble(Arrays.stream(cartPage.locator(TAX).innerText().split("\\$")).toList().get(1));
        double total = Double.parseDouble(Arrays.stream(cartPage.locator(TOTAL).innerText().split("\\$")).toList().get(1));

        assertEquals("Total price from UI", subtotalPrice + tax, total, 0.001);

        double expectedTotal = 0;
        Iterator<Map<String, Object>> iterator = expectedProducts.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> product = iterator.next();
            expectedTotal = expectedTotal + Double.valueOf(product.get("productPrice").toString());
        }

        assertEquals("Total price", expectedTotal + tax, total, 0.001);

        cartPage.locator(FINISH).click();
    }

    public void orderConfirmation() {
        assertThat(cartPage.locator(COMPLETE_HEADER)).containsText("Thank you for your order!");
        cartPage.locator(BACK_TO_PRODUCTS).click();
        assertThat(cartPage.locator(PRIMARY_HEADER)).containsText("Swag Labs", new LocatorAssertions.ContainsTextOptions().setTimeout(5000));
    }

    public void removeItemFromCart(ProductsEnv1 product) {
        ElementHandle itemDescription = cartPage.querySelectorAll(INVENTORY_ITEM).stream().filter(element -> element.innerText()
                .contains(product.getProductName())).toList().get(0);

        itemDescription.querySelector(REMOVE_BUTTON).click();
        assertNull(itemDescription.querySelector(REMOVE_BUTTON));
    }
}
