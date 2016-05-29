package ait.db;

import ait.entity.CartItem;

import java.util.List;

/**
 * Created by studamit on 19/05/2016.
 */

/**
 * Manager class for CartItem.
 */
public class CartItemManager extends Manager<CartItem> {

    /**
     *
     */
    public CartItemManager() {
        super(CartItem.class);
    }

    /**
     *
     * @param shoppingCartId : id of shopping cart
     * @return : list of cart items with id equals to shoppingCartId
     */
    public List<CartItem> findShoppingCartById(long shoppingCartId) {
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(Tables.CartItem.SHOPPING_CART_ID, shoppingCartId);
        List<CartItem> cartItems = find(conditionBuilder);

        return cartItems;
    }
}
