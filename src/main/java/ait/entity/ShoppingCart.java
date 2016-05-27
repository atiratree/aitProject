package ait.entity;

import ait.db.Column;
import ait.db.Tables;

import java.time.OffsetDateTime;

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


    public long getUserID() {
        return userId;
    }

    public void setUserID(int userID) {
        this.userId = userID;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, %d %s", getId(), getUserID(), getCreationDate().toString());
    }
}
