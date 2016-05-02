package ait.utils;

import ait.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by studamit on 02/05/2016.
 */
public class LoginUtils {
    private static final String SESSION_USER_ATTR = "user";

    public static final String USER_ATTR = "user";
    public static final String LOGGED_IN_ATTR = "loggedIn";
    public static final String USERNAME_ATTR = "name";

    public static void login(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_USER_ATTR, user);
    }

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.removeAttribute(SESSION_USER_ATTR);
    }


    public static void setLoginAttributes(HttpServletRequest request) {
        User user = getUserFromSession(request);
        boolean isLoggedIn = isLoggedIn(user);
        request.setAttribute(LOGGED_IN_ATTR, isLoggedIn);

        if (isLoggedIn) {
            request.setAttribute(USER_ATTR, user);
            request.setAttribute(USERNAME_ATTR, user.getName());
        }
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        User user = getUserFromSession(request);
        return isLoggedIn(user);
    }

    private static User getUserFromSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(SESSION_USER_ATTR);
    }

    private static boolean isLoggedIn(User user) {
        return user != null;
    }
}
