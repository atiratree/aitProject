package ait.servlet.utils;

import ait.db.DbException;
import ait.db.Managers;
import ait.entity.User;
import ait.entity.Visualisation;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.NotAuthorizedException;
import java.util.logging.Logger;

/**
 * Created by studamit on 02/05/2016.
 */
public class LoginUtils {
    private static final Logger log = Logger.getLogger(LoginUtils.class.getName());

    private static final String SESSION_USER_ATTR = "SESSION_USER_ATTR";

    public static final String USER_ATTR = "USER_ATTR";
    public static final String LOGGED_IN_ATTR = "LOGGED_IN_ATTR";
    public static final String USERNAME_ATTR = "USERNAME_ATTR";

    public static final String SIGNING_UP_ATTR = "SIGNING_UP_ATTR";

    /**
     * To let user sign in.
     *
     * @param request  HTTPServeltRequest request
     * @param response HTTPServletResponse response
     * @param email    Email of the logging user.
     * @param password Password of the logging user.
     * @return: true if user logged in successfully and vice-versa.
     */
    public static boolean login(HttpServletRequest request, HttpServletResponse response, String email, String password) {
        User user = null;
        boolean result = ParamsValidator.validateEmail(request, email);

        if (result) {
            user = Managers.getUserManager().findOneByEmail(email);
            result = user != null;

            if (result) {
                result = ParamsValidator.validatePassword(request, user, password);
            }
        }

        if (result) {
            login(request, response, user);
        } else {
            logout(request);
        }
        return result;
    }

    /**
     * To let user sign-up.
     *
     * @param request  HTTPServletRequest request
     * @param response HTTPServletResponse response
     * @param email    Sign-up user's email.
     * @param name     Sign-up user's name.
     * @param surname  Sign-up users's surname.
     * @param password Sign-up user's password.
     * @return true if sign up was successful
     */
    public static boolean signUp(HttpServletRequest request, HttpServletResponse response, String email, String name, String surname, String password) {
        boolean result = ParamsValidator.validateNewEmail(request, email);
        result = ParamsValidator.validateNewFirstName(request, name) && result;
        result = ParamsValidator.validateNewSurname(request, surname) && result;
        result = ParamsValidator.validateNewPassword(request, password) && result;

        if (result) {
            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = new User(email, name, surname, hashed);
            try {
                user = Managers.getUserManager().create(user);
                login(request, response, user);
            } catch (DbException e) {
                result = false;
                log.severe(e.toString());
            }
        }

        return result;
    }

    /**
     * To perform user login and saving the credentials into the session.
     *
     * @param request  HTTPServlet request
     * @param response HTTPServlet response
     * @param user     user to save in session
     */
    private static void login(HttpServletRequest request, HttpServletResponse response, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_USER_ATTR, user);
    }

    /**
     * To perform user log-out.
     *
     * @param request HttpServletRequest
     */
    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.removeAttribute(SESSION_USER_ATTR);
        session.removeAttribute(ShoppingUtils.SHOPPING_CART);
    }


    /**
     * To save the user's attribute in session.
     *
     * @param request HttpServletRequest
     */
    public static void setLoginAttributes(HttpServletRequest request) {
        User user = getUserFromSession(request);
        boolean isLoggedIn = isLoggedIn(user);
        request.setAttribute(LOGGED_IN_ATTR, isLoggedIn);

        if (isLoggedIn) {
            request.setAttribute(USER_ATTR, user);
            request.setAttribute(USERNAME_ATTR, user.getName());
        }
    }

    /**
     * To check if the user is logged in.
     *
     * @param request HttpServletRequest
     * @return true if the user is logged in and vice-versa.
     */
    public static boolean isLoggedIn(HttpServletRequest request) {
        User user = getUserFromSession(request);
        return isLoggedIn(user);
    }

    /**
     * To checl if the user has access to cart item.
     *
     * @param request HttpServletRequest
     * @param type    Visualisation items whose access to check
     *                throw :new NotAuthorizedException: in case of no authorization of cart item.
     */
    public static void checkAuthorization(HttpServletRequest request, Visualisation type) {
        if (!ShoppingUtils.doesUserOwnVisualisation(request, type)) {
            throw new NotAuthorizedException("No access to this resource!");
        }
    }

    /**
     * To get the logged in user from session.
     *
     * @param request HttpServletRequest
     * @return user from session.
     */
    public static User getUserFromSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(SESSION_USER_ATTR);
    }

    /***
     * To check is user is logged in.
     *
     * @param user checking user.
     * @return logged in user.
     */
    private static boolean isLoggedIn(User user) {
        return user != null;
    }
}
