package ait.entity;

import java.util.List;

/**
 * Created by suomiy on 5/11/16.
 */
public class ShoppingCart extends IdEntity {


    public List<ShoppingCartItem> getItems() {
        //Managers.getShoppingCartItemManager().find( Condition cart_id = getId());
        return null;
    }

    public void setItems(List<ShoppingCartItem> items) {
        //Managers.getShoppingCartItemManager();
    }

}
