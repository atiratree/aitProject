package ait.db;

/**
 * Created by suomiy on 5/5/16.
 */
public class Managers {
    private static final UserManager userManager = new UserManager();
    private static final ShoppingCartManager shoppingCartManager = new ShoppingCartManager();
    private static final CartItemManager cartItemManager = new CartItemManager();

    public static UserManager getUserManager() {
        return userManager;
    }

    public static CartItemManager getCartItemManager() {
        return cartItemManager;
    }

    public static ShoppingCartManager getShoppingCartManager() {
        return shoppingCartManager;
    }

}
