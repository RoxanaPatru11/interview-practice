package enums;

import lombok.Getter;

@Getter
public enum HomeMenu {

    ALL_ITEMS("All Items", "[data-test='inventory-sidebar-link']"),
    ABOUT("About", "[data-test='about-sidebar-link']"),
    LOGOUT("Logout", "[data-test='logout-sidebar-link']"),
    RESET_APP_STATE("Reset App State", "[data-test='reset-sidebar-link']"),
    SHOPPING_CART("Shopping Cart", "[data-test='shopping-cart-link']");

    private final String menu;
    private final String selector;

    HomeMenu(String menu, String selector) {
        this.menu = menu;
        this.selector = selector;
    }
}
