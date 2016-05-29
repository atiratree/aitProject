package ait.db;

import ait.entity.CartItem;
import ait.entity.ShoppingCart;
import ait.entity.User;
import ait.entity.Visualisation;

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
     * @param shoppingCart : shoppingCart
     * @return : list of cart items of this shoppingCart
     */
    public List<CartItem> find(ShoppingCart shoppingCart) {
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(Tables.CartItem.SHOPPING_CART_ID, shoppingCart.getId());
        List<CartItem> cartItems = find(conditionBuilder);

        return cartItems;
    }

    public List<CartItem> find(User user, Visualisation visualisation) {
        return find(user, visualisation, null);
    }

    public List<CartItem> find(User user, Visualisation visualisation, Integer limit) {
        List<Long> cartIds = Managers.getShoppingCartManager().findIdsByUser(user);
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(Tables.CartItem.VISUALISATION, visualisation)
                .in(Tables.CartItem.SHOPPING_CART_ID,cartIds);
        if(limit != null){
            conditionBuilder.limit(limit);
        }
        return find(conditionBuilder);
    }
}
