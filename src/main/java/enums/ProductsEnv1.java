package enums;

import lombok.Getter;

@Getter
public enum ProductsEnv1{
    PRODUCT1("Sauce Labs Backpack"),
    PRODUCT2("Sauce Labs Bike Light"),
    PRODUCT3("Sauce Labs Bolt T-Shirt"),
    PRODUCT4("Sauce Labs Fleece Jacket");

    private final String productName;

    ProductsEnv1(String productName) {
        this.productName = productName;
    }
}
