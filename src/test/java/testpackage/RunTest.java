package testpackage;

import enums.HomeMenu;
import enums.ProductsEnv1;
import org.junit.jupiter.api.Test;
import properties.EnvironmentReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class RunTest extends GeneralDataTest {

    @Test
    public void placeAnOrder() {
        System.out.println("Add products in the cart");


        List<Map<String, Object>> existingProductsInCart = new ArrayList<>();
        List<ProductsEnv1> products = new ArrayList<>(Arrays.asList(ProductsEnv1.PRODUCT1, ProductsEnv1.PRODUCT2));
        existingProductsInCart  = homePage.addProductsToCart(products, existingProductsInCart );


        System.out.println("Remove an item from the cart from Products page");
        homePage.removeProductFromCartUsingHomePage(ProductsEnv1.PRODUCT1);
        existingProductsInCart= removeItemFromTheList(existingProductsInCart);


        System.out.println("");



        navigateToMenu(HomeMenu.LOGOUT);

    }


}
