package ait.db;

import ait.entity.CartItem;

import java.util.List;

/**
 * Created by studamit on 19/05/2016.
 */
public class CartItemManager extends Manager<CartItem> {

    public CartItemManager() {
        super(CartItem.class);
    }

    public List<CartItem> findShoppingCartById(long shoppingCartId) {
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(Tables.CartItem.SHOPPING_CART_ID, shoppingCartId);
        List<CartItem> cartItems = find(conditionBuilder);

        return cartItems;
    }

    public CartItem findCartByType(int cartType) {
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(Tables.CartItem.CART_TYPE, cartType);
        CartItem cartItem = findOne(conditionBuilder);

        return cartItem;
    }

}
