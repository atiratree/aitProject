package ait.entity;

import ait.db.Column;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by suomiy on 5/11/16.
 */
public class ShoppingCart extends IdEntity {


    public enum Status {
        MIN, MAX, AVG
    }

    @Column
    private Status status;

    @Column
    private OffsetDateTime date;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public List<ShoppingCartItem> getItems() {
        //Managers.getShoppingCartItemManager().find( Condition cart_id = getId());
        return null;
    }

    public void setItems(List<ShoppingCartItem> items) {
        //Managers.getShoppingCartItemManager();
    }

}
