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

    public ShoppingCartManager() {
        super(ShoppingCart.class);
    }

    /**
     * To get all the shopping carts of the user.
     *
     * @param user: users whose shopping cart to get from database.
     * @return list of shopping cart of user.
     */
    public List<ShoppingCart> findByUser(User user) {
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(ShoppingCart.USER_ID, user.getId());
        List<ShoppingCart> shoppingCart = find(conditionBuilder);
        shoppingCart.sort((c1, c2) -> c2.getCreationDate().compareTo(c1.getCreationDate()));

        return shoppingCart;
    }

    /**
     * @param user user
     * @return id's of shopping carts of the user.
     */
    public List<Long> findIdsByUser(User user) {
        return findByUser(user).stream().map(shoppingCart -> shoppingCart.getId()).collect(Collectors.toList());
    }

    /**
     * Create the shopping cart in the database.
     *
     * @param entity: creating entity
     * @return Created shopping cart from the database.
     * @throws DbException
     */
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

    /**
     * Find the shopping cart from database given the condition.
     *
     * @param conditionBuilder: condition for the finding entities.
     * @return list of ShoppingCarts
     */
    @Override
    public List<ShoppingCart> find(ConditionBuilder conditionBuilder) {
        List<ShoppingCart> carts = super.find(conditionBuilder);
        carts.forEach(cart -> cart.setItems(Managers.getCartItemManager().find(cart)));
        return carts;
    }
}
