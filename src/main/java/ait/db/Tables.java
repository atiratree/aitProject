package ait.db;

/**
 * Created by suomiy on 5/5/16.
 */
public interface Tables {
    String SCHEMA_NAME = "public";
    String SEQUENCE_NAME = "sequence";

    /**
     * Database table columns for Id entity.
     */
    interface IdEntity {
        String ID_COLUMN = "id";
    }

    /**
     * Database table columns for User entity.
     */
    interface User extends IdEntity {
        String NAME = "name";
        String SURNAME = "surname";
        String EMAIL = "email";
        String PASSWORD_HASH = "password_hash";
    }

    /**
     * Database table columns for Shopping Cart entity.
     */
    interface ShoppingCart extends IdEntity {
        String USER_ID = "user_id";
        String CREATION_DATE = "creation_date";
    }

    /**
     * Database table columns for the CartItem entity.
     */
    interface CartItem extends IdEntity {
        String SHOPPING_CART_ID = "shopping_cart_id";
        String CART_TYPE = "cart_type";
    }

    /**
     * Database table columns for the Temperature entity.
     */
    interface Temperature extends IdEntity {
        String YEAR = "year";
        String MONTH = "month";
        String MEASUREMENT_TYPE = "measurement_type";
        String TEMPERATURE = "temperature";
        String PLACE = "place";
    }
}
