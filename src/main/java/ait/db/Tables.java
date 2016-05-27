package ait.db;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by suomiy on 5/5/16.
 */
public interface Tables {
    String SCHEMA_NAME = "public";
    String SEQUENCE_NAME = "sequence";

    interface IdEntity {
        String ID_COLUMN = "id";
    }

    interface User extends IdEntity {
        String NAME = "name";
        String SURNAME = "surname";
        String EMAIL = "email";
        String PASSWORD_HASH = "password_hash";
    }

    interface ShoppingCart extends IdEntity {
        String USER_ID = "user_id";
        String CREATION_DATE = "creation_date";
    }

    interface CartItem extends IdEntity {
        String SHOPPING_CART_ID = "shopping_cart_id";
        String CART_TYPE = "cart_type";
    }
}
