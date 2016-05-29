package ait.db;

import ait.entity.CartItem;
import ait.entity.ShoppingCart;
import ait.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ShoppingCart> findByUser(User user) {
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(Tables.ShoppingCart.USER_ID, user.getId());
        List<ShoppingCart> shoppingCart = find(conditionBuilder);

        return shoppingCart;
    }

    public List<Long> findIdsByUser(User user) {
        return findByUser(user).stream().map(shoppingCart -> shoppingCart.getId()).collect(Collectors.toList());
    }

    @Override
    public ShoppingCart create(ShoppingCart entity) throws DbException {
        ShoppingCart cart = super.create(entity);
        List<CartItem> items = new ArrayList<>();

        CartItemManager cartItemManager = Managers.getCartItemManager();
        for (CartItem cartItem : cart.getItems()) {
            cartItem.setShoppingCartId(cart.getId());
            items.add(cartItemManager.create(cartItem));
        }

        cart.setItems(items);

        return cart;
    }
}
