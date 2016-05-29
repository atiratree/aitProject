package ait.db;

import ait.entity.Temperature;

/**
 * Created by suomiy on 5/5/16.
 */
public class Managers {
    private static final UserManager userManager = new UserManager();
    private static final ShoppingCartManager shoppingCartManager = new ShoppingCartManager();
    private static final CartItemManager cartItemManager = new CartItemManager();
    private static final Manager<Temperature> temperatureManager = new Manager<>(Temperature.class);

    /**
     * To get the user manager.
     * @return: the user manager.
     */
    public static UserManager getUserManager() {
        return userManager;
    }

    /**
     * To get the CartItem manager.
     * @return: the cart item manager.
     */
    public static CartItemManager getCartItemManager() {
        return cartItemManager;
    }

    /**
     * To get the ShoppingCart manager.
     * @return: the shopping cart manager.
     */
    public static ShoppingCartManager getShoppingCartManager() {
        return shoppingCartManager;
    }

    /**
     * To get the temperature manager.
     * @return: the temperature manager.
     */
    public static Manager<Temperature> getTemperatureManager() {
        return temperatureManager;
    }
}
