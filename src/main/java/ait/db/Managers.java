package ait.db;

import ait.entity.ShoppingCart;
import ait.entity.ShoppingCartItem;

/**
 * Created by suomiy on 5/5/16.
 */
public class Managers {
    private static final UserManager userManager = new UserManager();
    private static final Manager<ShoppingCart> shoppingCartManager = new Manager<>(ShoppingCart.class);
    private static final Manager<ShoppingCartItem> shoppingCartItemManager = new Manager<>(ShoppingCartItem.class);

    public static UserManager getUserManager() {
        return userManager;
    }

    public static Manager<ShoppingCart> getShoppingCartManager() {
        return shoppingCartManager;
    }

    public static Manager<ShoppingCartItem> getShoppingCartItemManager() {
        return shoppingCartItemManager;
    }
}
