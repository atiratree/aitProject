package ait.db;

/**
 * Created by suomiy on 5/5/16.
 */
public class Managers {
    private static final UserManager userManager = new UserManager();

    public static UserManager getUserManager() {
        return userManager;
    }
}
