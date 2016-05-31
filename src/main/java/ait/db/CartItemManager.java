package ait.db;

import ait.entity.CartItem;
import ait.entity.ShoppingCart;
import ait.entity.User;
import ait.entity.Visualisation;

import java.util.List;

/**
 * Created by studamit on 19/05/2016.
 */

public class CartItemManager extends Manager<CartItem> {

    public CartItemManager() {
        super(CartItem.class);
    }

    /**
     * @param shoppingCart shoppingCart
     * @return list of cart items of this shoppingCart
     */
    public List<CartItem> find(ShoppingCart shoppingCart) {
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(CartItem.SHOPPING_CART_ID, shoppingCart.getId());
        List<CartItem> cartItems = find(conditionBuilder);

        return cartItems;
    }

    /**
     * @param user
     * @param visualisation
     * @return list of cartItems of this user and this visualisation
     */
    public List<CartItem> find(User user, Visualisation visualisation) {
        return find(user, visualisation, null);
    }

    /**
     * @param user
     * @param visualisation
     * @param limit         maximum number of CartItems to return
     * @return list of CartItems of this user and this visualisation
     */
    public List<CartItem> find(User user, Visualisation visualisation, Integer limit) {
        List<Long> cartIds = Managers.getShoppingCartManager().findIdsByUser(user);
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(CartItem.VISUALISATION, visualisation)
                .in(CartItem.SHOPPING_CART_ID, cartIds);
        if (limit != null) {
            conditionBuilder.limit(limit);
        }
        return find(conditionBuilder);
    }
}
