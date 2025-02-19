package testpackage;

import enums.ClientDetails;
import enums.HomeMenu;
import enums.ProductsEnv1;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class RunTest extends GeneralDataTest {

    @Tag("myTest")
    @Test
    public void addAndRemoveProducts() {
        System.out.println("Add products in the cart");
        List<Map<String, Object>> existingProductsInCart = new ArrayList<>();
        List<ProductsEnv1> productsToBeAddedInCart = new ArrayList<>(Arrays.asList(ProductsEnv1.PRODUCT1, ProductsEnv1.PRODUCT2));
        existingProductsInCart = homePage.addProductsToCart(productsToBeAddedInCart, existingProductsInCart);

        System.out.println("Remove an item from the cart from Products page");
        homePage.removeProductFromCartUsingHomePage(ProductsEnv1.PRODUCT1);
        existingProductsInCart = removeItemFromTheList(existingProductsInCart, ProductsEnv1.PRODUCT1);

        System.out.println("Add another product in the cart");
        productsToBeAddedInCart = new ArrayList<>(List.of(ProductsEnv1.PRODUCT3));
        existingProductsInCart = homePage.addProductsToCart(productsToBeAddedInCart, existingProductsInCart);

        navigateToCart();
        cartPage.checkTheCart(existingProductsInCart);
        cartPage.removeItemFromCart(ProductsEnv1.PRODUCT3);
        existingProductsInCart = removeItemFromTheList(existingProductsInCart, ProductsEnv1.PRODUCT3);
        cartPage.continueShopping();

        navigateToMenu(HomeMenu.LOGOUT);
    }

    @Test
    public void placeAnOrder() {
        System.out.println("Add products in the cart");
        List<Map<String, Object>> existingProductsInCart = new ArrayList<>();
        List<ProductsEnv1> productsToBeAddedInCart = new ArrayList<>(Arrays.asList(ProductsEnv1.PRODUCT1, ProductsEnv1.PRODUCT2));
        existingProductsInCart = homePage.addProductsToCart(productsToBeAddedInCart, existingProductsInCart);

        System.out.println("Place the order");
        navigateToCart();
        cartPage.checkTheCart(existingProductsInCart);
        cartPage.checkoutOrder(ClientDetails.CLIENT1, existingProductsInCart);

        navigateToMenu(HomeMenu.LOGOUT);
    }
}
