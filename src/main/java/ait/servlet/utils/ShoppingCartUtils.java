package ait.servlet.utils;

import ait.db.DbException;
import ait.db.Managers;
import ait.entity.CartItem;
import ait.entity.CartType;
import ait.entity.ShoppingCart;
import ait.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by studamit on 12/05/2016.
 */
public class ShoppingCartUtils {

    public static String SHOPPING_CART = "";
    public static String[] CART_ITEMS = {};


    /*
    Setitng the type of the cart,
    1 is for the Temperature
    2 is for the salenity
     */
    public static void saveCartTypeInSession(HttpServletRequest request, User user, CartType cartType) {
        // saving the cart items into the session
        if (!isItemInSession(request, user, cartType)) {
            HttpSession session = request.getSession(true);
            ArrayList<CartType> itemsFromSession = getCartItemsFromSession(request);

            if (itemsFromSession != null)
                session.removeAttribute(SHOPPING_CART);
            else
                itemsFromSession = new ArrayList<CartType>();

            itemsFromSession.add(cartType);
            session.setAttribute(SHOPPING_CART, itemsFromSession);
        }
    }

    private static void saveCartTypesInSession(HttpServletRequest request, ArrayList<CartType> cartTypes) {

        HttpSession session = request.getSession(true);
        session.setAttribute(SHOPPING_CART, cartTypes);
    }

    public static ArrayList<CartType> getCartItemsFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<CartType> cartItemsFromSession = (ArrayList<CartType>) session.getAttribute(SHOPPING_CART);

        return cartItemsFromSession;
    }

    public static ShoppingCart saveShoppingCartInDatabase(ShoppingCart shoppingCart) throws DbException {
        ShoppingCart savedCart = Managers.getShoppingCartManager().create(shoppingCart);
        return savedCart;
    }

    public static void saveCartItemsInDatabase(long shoppingCartId, CartType cartTypes) throws DbException {

        CartItem cartItem = new CartItem(shoppingCartId, cartTypes);
        Managers.getCartItemManager().create(cartItem);
    }

    public static CartType getCartType(String cartType) {
        try {
            return CartType.valueOf(cartType);
        } catch (IllegalArgumentException x) {
            return null;
        }
    }

    public static boolean hasItemBought(User user, CartType buyingItem) {
        boolean itemBought = false;
        if (user != null) {
            List<ShoppingCart> shoppingCarts = getShoppingCarts(user);

            for (int i = 0; i < shoppingCarts.size(); i++) {
                List<CartItem> cartItems = getCartItems(shoppingCarts.get(i));

                for (int j = 0; j < cartItems.size(); j++) {
                    if (cartItems.get(j).getCart_type() == buyingItem) {
                        itemBought = true;
                        return itemBought;
                    }
                }
            }
        }
        return itemBought;
    }

    public static List<ShoppingCart> getShoppingCarts(User user) {
        List<ShoppingCart> shoppingCarts = Managers.getShoppingCartManager().findByUserId(user);
        return shoppingCarts;
    }

    public static List<CartItem> getCartItems(ShoppingCart shoppingCart) {
        List<CartItem> cartItems = Managers.getCartItemManager().findShoppingCartById(shoppingCart.getId());
        return cartItems;
    }

    public static boolean isItemInSession(HttpServletRequest request, User user, CartType buyingItem) {
        boolean itemExist = false;
        ArrayList<CartType> cartTypes = ShoppingCartUtils.getCartItemsFromSession(request);
        if (user != null && cartTypes != null) {
            if (cartTypes.size() > 0) {
                for (int i = 0; i < cartTypes.size(); i++) {
                    //create the div for the temperature div
                    if (cartTypes.get(i) == buyingItem) {
                        itemExist = true;
                        return itemExist;
                    }
                }
            }
        }
        return itemExist;
    }

    // delete the cart item either from the session or from the database
    public static boolean isCartItemDeleted(HttpServletRequest request, User user, CartType cartType) throws DbException {

        boolean isItemDeleted = false;
        boolean isInSession = isItemInSession(request, user, cartType);

        // if the cart is in session then delete form session else if that is in database
        // then delete from database
        if (isInSession) {
            deleteCartItemsFromSession(request, cartType);
            isItemDeleted = true;
        } else {
            deleteCartItemFromDatabse(cartType);
            isItemDeleted = true;
        }
        return isItemDeleted;
    }

    // to delete a item from the session
    public static boolean deleteCartItemsFromSession(HttpServletRequest request, CartType cartType) {
        ArrayList<CartType> cartItems = getCartItemsFromSession(request);
        boolean isInSession = false;

        if (cartItems != null) {
            for (int i = 0; i < cartItems.size(); i++) {
                if (cartItems.get(i).equals(cartType)) {
                    cartItems.remove(i);
                    isInSession = true;
                    break;
                }
            }
            // delete the previous session cart and add now the new one
            HttpSession session = request.getSession();
            session.removeAttribute(ShoppingCartUtils.SHOPPING_CART);
            saveCartTypesInSession(request, cartItems);
        }
        return isInSession;
    }

    public static boolean deleteCartItemFromDatabse(CartType cartType) throws DbException {
        boolean isDeleted = false;
        CartItem deletingItem = Managers.getCartItemManager().findCartByType(cartType.ordinal());

        if (deletingItem != null) {
            Managers.getCartItemManager().delete(deletingItem);
            isDeleted = true;
        }
        return isDeleted;
    }
}

