package ait.servlet.utils;

import ait.db.DbException;
import ait.db.Managers;
import ait.entity.CartType;
import ait.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.*;
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
    public static final String TOKEN = "TOKEN";
    public static final String USERNAME_ATTR = "USERNAME_ATTR";

    public static final String SIGNING_UP_ATTR = "SIGNING_UP_ATTR";

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

    public static boolean signUp(HttpServletRequest request, HttpServletResponse response, String email, String name, String surname, String password) {
        boolean result = ParamsValidator.validateNewEmail(request, email);
        result = ParamsValidator.validateNewFirstName(request, name) && result;
        result = ParamsValidator.validateNewSurname(request, surname) && result;
        result = ParamsValidator.validateNewPassword(request, password) && result;

        if (result) {
            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = new User(email, name, surname, hashed);
            try {
                Managers.getUserManager().create(user);
                login(request, response, user);
            } catch (DbException e) {
                result = false;
                log.severe(e.toString());
            }
        }

        return result;
    }


    private static void login(HttpServletRequest request, HttpServletResponse response, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_USER_ATTR, new LoginObject(user));

        Cookie userCookie = new Cookie(TOKEN, OnlineUsers.getUserToken(user));
        userCookie.setMaxAge(-1); //Store cookie for a session
        response.addCookie(userCookie);
    }

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.removeAttribute(SESSION_USER_ATTR);
        session.removeAttribute(ShoppingCartUtils.SHOPPING_CART);
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

    public static void checkAuthorization(CartType type, HttpServletRequest request) {
        User user = getUserFromSession(request);

        if (!ShoppingCartUtils.hasItemBought(user, type)) {
            throw new NotAuthorizedException("No access to this resource!");
        }
    }

    public static User getUserFromSession(HttpServletRequest request) {
        LoginObject object = (LoginObject) request.getSession().getAttribute(SESSION_USER_ATTR);
        return object == null ? null : object.getUser();
    }

    private static boolean isLoggedIn(User user) {
        return user != null;
    }

    public static class LoginObject implements HttpSessionBindingListener {

        private User user;

        public LoginObject(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        @Override
        public void valueBound(HttpSessionBindingEvent event) {
            LoginObject attribute = (LoginObject) event.getValue();
            if (attribute != null) {
                OnlineUsers.addUser(attribute.getUser());
            }
        }

        @Override
        public void valueUnbound(HttpSessionBindingEvent event) {
            LoginObject attribute = (LoginObject) event.getValue();
            if (attribute != null) {
                OnlineUsers.removeUser(attribute.getUser());
            }
        }
    }
}
