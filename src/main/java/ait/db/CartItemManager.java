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

    /**
     * To get all the visualisation of the user from database.
     * @param user: finding user's visualisation
     * @param visualisation: finding visualisation
     * @return: a list of visualisations from database.
     */
    public List<CartItem> find(User user, Visualisation visualisation) {
        return find(user, visualisation, null);
    }

    /**
     * To find the visualisations of the user given the limit of visualisation to extract from database.
     * @param user: finding user's visualisation
     * @param visualisation: finding visualisation
     * @param limit: limit to get the visualisations
     * @return: a list of visualisations from the database.
     */
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
