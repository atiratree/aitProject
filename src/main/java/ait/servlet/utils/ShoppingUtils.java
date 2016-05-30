package ait.servlet.utils;

import ait.db.DbException;
import ait.db.Managers;
import ait.entity.CartItem;
import ait.entity.ShoppingCart;
import ait.entity.User;
import ait.entity.Visualisation;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.time.ZoneId;


public class ShoppingUtils {

    public static String SHOPPING_CART = "cart";

    /**
     * To check if the visualization is in the session.
     *
     * @param request       HttpServletRequest reques
     * @param visualisation checking Visualisation
     * @return true is present and vice-versa.
     */
    public static boolean isItemInSessionCart(HttpServletRequest request, Visualisation visualisation) {
        return getSessionCart(request).containsVisualisation(visualisation);
    }

    /**
     * To check if user bought the visualisation
     *
     * @param request       HttpServletRequest request
     * @param visualisation checking visualisation
     * @return true if present and vice-versa.
     */
    public static boolean doesUserOwnVisualisation(HttpServletRequest request, Visualisation visualisation) {
        User user = LoginUtils.getUserFromSession(request);
        return user != null && visualisation != null && Managers.getCartItemManager().find(user, visualisation, 1).size() == 1;
    }

    /**
     * To add the visualisation into the cart.
     *
     * @param request HttpServletRequest request
     * @param type    adding visualisation
     */
    public static void addToCart(HttpServletRequest request, Visualisation type) {
        if (doesUserOwnVisualisation(request, type)) {
            return;
        }

        ShoppingCart cart = getSessionCart(request);
        CartItem item = new CartItem(type);
        cart.addItem(item);
    }

    /**
     * Remove the visualisation from cart.
     *
     * @param request       HttpServletRequest request
     * @param visualisation removing visualisation
     */
    public static void removeFromCart(HttpServletRequest request, Visualisation visualisation) {
        ShoppingCart cart = getSessionCart(request);
        cart.removeItem(visualisation);
    }

    /**
     * To get the shopping cart from session..
     *
     * @param request HttpServletRequest request
     * @return Shopping cart from session.
     */
    public static ShoppingCart getSessionCart(HttpServletRequest request) {
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute(SHOPPING_CART);
        if (cart == null) {
            cart = new ShoppingCart();
            setSessionCart(request, cart);
        }

        return cart;
    }

    /**
     * TO set the shopping cart intot the session.
     *
     * @param request HttpServletRequest request
     * @param cart    Setting shopping cart.
     */
    private static void setSessionCart(HttpServletRequest request, ShoppingCart cart) {
        request.getSession().setAttribute(SHOPPING_CART, cart);
    }

    /**
     * T buy the cart items.
     *
     * @param request HttpServletRequest request
     * @throws DbException
     */
    public static void buy(HttpServletRequest request) throws DbException {
        ShoppingCart cart = getSessionCart(request);
        if (!cart.getItems().isEmpty()) {
            cart.setUserId(LoginUtils.getUserFromSession(request).getId());
            cart.setCreationDate(OffsetDateTime.now(ZoneId.of("UTC")));

            Managers.getShoppingCartManager().create(cart);
            setSessionCart(request, null);
        }
    }
}

