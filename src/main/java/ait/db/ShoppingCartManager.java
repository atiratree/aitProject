package ait.db;

import ait.entity.ShoppingCart;
import ait.entity.User;

import java.util.List;

/**
 * Created by studamit on 12/05/2016.
 */
public class ShoppingCartManager extends Manager<ShoppingCart> {
    /**
     * ShoppingCart manager constructor.
     */
    public ShoppingCartManager() {
        super(ShoppingCart.class);
    }

    /**
     * To get all the shopping carts of the user.
     * @param user: users whose shopping cart to get from database.
     * @return: list of shopping cart of user.
     */
    public List<ShoppingCart> findByUserId(User user) {
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(Tables.ShoppingCart.USER_ID, user.getId());
        List<ShoppingCart> shoppingCart = find(conditionBuilder);

        return shoppingCart;
    }
}
