package ait.entity;

import ait.db.Column;

/**
 * Created by suomiy on 5/11/16.
 */
public class CartItem extends IdEntity {
    public CartItem() {
    }

    public CartItem(Visualisation visualisation) {
        this.visualisation = visualisation;
    }

    public CartItem(long shoppingCartId, Visualisation visualisation) {

        this.shoppingCartId = shoppingCartId;
        this.visualisation = visualisation;
    }

    @Column
    private long shoppingCartId;

    @Column
    private Visualisation visualisation;

    public long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Visualisation getVisualisation() {
        return visualisation;
    }

    public void setVisualisation(Visualisation visualisation) {
        this.visualisation = visualisation;
    }

    @Override
    public String toString() {
        int type = visualisation.ordinal();
        return String.format("CartItem: %d, %d %d", getId(), getShoppingCartId(), type);
    }
}
