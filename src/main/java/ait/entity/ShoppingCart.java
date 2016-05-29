package ait.entity;

import ait.db.Column;
import ait.db.Tables;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Amit on 4/27/16.
 */
public class ShoppingCart extends IdEntity implements Tables.User {

    public ShoppingCart() {
    }

    public ShoppingCart(Long userId, OffsetDateTime creationDate) {
        this.userId = userId;
        this.creationDate = creationDate;
    }

    @Column
    private long userId;

    @Column
    private OffsetDateTime creationDate;

    private List<CartItem> items = new ArrayList<>();

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    /**
     * adds item only if it isn't already in this shopping cart
     *
     * @param item
     */
    public void addItem(CartItem item) {
        Visualisation visualisation = item.getVisualisation();
        if (visualisation != null && !containsVisualisation(visualisation)) {
            items.add(item);
        }
    }

    public void removeItem(Visualisation visualisation) {
        Iterator<CartItem> it = items.iterator();
        while (it.hasNext()) {
            CartItem item = it.next();
            if (item.getVisualisation() == visualisation) {
                it.remove();
                break;
            }
        }
    }

    public boolean containsVisualisation(Visualisation visualisation) {
        return items.stream().map(CartItem::getVisualisation).anyMatch(t -> t == visualisation);
    }

    @Override
    public String toString() {
        return String.format("Id: %d, %d %s", getId(), getUserId(), getCreationDate().toString());
    }

}
