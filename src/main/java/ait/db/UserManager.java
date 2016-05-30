package ait.db;

import ait.entity.User;

/**
 * Created by suomiy on 5/5/16.
 */
public class UserManager extends Manager<User> {
    public UserManager() {
        super(User.class);
    }

    /**
     * @param email email of the user
     * @return user from the database.
     */
    public User findOneByEmail(String email) {
        ConditionBuilder conditionBuilder = new ConditionBuilder().where(Tables.User.EMAIL, email);
        return findOne(conditionBuilder);
    }
}
