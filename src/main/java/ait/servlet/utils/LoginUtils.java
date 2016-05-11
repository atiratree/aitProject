package ait.servlet.utils;

import ait.db.DbException;
import ait.db.Managers;
import ait.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    public static boolean login(HttpServletRequest request, String email, String password) {
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
            login(request, user);
        } else {
            logout(request);
        }
        return result;
    }

    public static boolean signUp(HttpServletRequest request, String email, String name, String surname, String password) {
        boolean result = ParamsValidator.validateNewEmail(request, email);
        result = ParamsValidator.validateNewFirstName(request, name) && result;
        result = ParamsValidator.validateNewSurname(request, surname) && result;
        result = ParamsValidator.validateNewPassword(request, password) && result;

        if (result) {
            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = new User(email, name, surname, hashed);
            try {
                Managers.getUserManager().create(user);
                login(request, user);
            } catch (DbException e) {
                result = false;
                log.severe(e.toString());
            }
        }

        return result;
    }


    private static void login(HttpServletRequest request, User user) {
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

    public static User getUserFromSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(SESSION_USER_ATTR);
    }

    private static boolean isLoggedIn(User user) {
        return user != null;
    }
}
