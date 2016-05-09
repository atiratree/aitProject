package ait.db;

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
        String PASSWORD_HASH = "passwordHash";
    }
}
