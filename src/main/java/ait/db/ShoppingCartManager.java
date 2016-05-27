package ait.db;

import ait.entity.ShoppingCart;
import ait.entity.User;

import java.util.List;

/**
 * Created by studamit on 12/05/2016.
 */
public class ShoppingCartManager extends Manager<ShoppingCart> {
    public ShoppingCartManager() {
        super(ShoppingCart.class);
    }

    public List<ShoppingCart> findByUserId(User user) {
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(Tables.ShoppingCart.USER_ID, user.getId());
        List<ShoppingCart> shoppingCart = find(conditionBuilder);

        return shoppingCart;
    }
}
