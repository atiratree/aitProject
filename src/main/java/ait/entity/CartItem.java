package ait.entity;

import ait.db.Column;

/**
 * Created by suomiy on 5/11/16.
 */
public class CartItem extends IdEntity {
    public CartItem() {
    }

    public CartItem(long shoppingCartId, CartType cart_type) {

        this.shoppingCartId = shoppingCartId;
        this.cart_type = cart_type;
    }

    @Column
    private long shoppingCartId;

    @Column
    private CartType cart_type;

    public long getCartID() {
        return this.shoppingCartId;
    }

    public void setCartID(int cartId) {
        this.shoppingCartId = cartId;
    }

    public void setCartType(CartType cart_type) {
        this.cart_type = cart_type;
    }

    public CartType getCart_type() {
        return this.cart_type;
    }

    @Override
    public String toString() {
        int type = cart_type.ordinal();
        //String.format("Id: %d, %d %s", getId(), getUserID(), getCreationDate().toString());
        return String.format("CartItem: %d, %d %d", getId(), getCartID(), type);
    }
}
