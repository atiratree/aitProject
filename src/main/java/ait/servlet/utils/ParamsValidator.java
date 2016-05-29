package ait.servlet.utils;

import ait.db.Managers;
import ait.entity.CartType;
import ait.entity.User;
import ait.utils.ObjectUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * Created by suomiy on 5/5/16.
 */
public class ParamsValidator {
    private static final Logger log = Logger.getLogger(ParamsValidator.class.getName());

    public static final String INVALID_CREDENTIALS_MSG = "INVALID_CREDENTIALS_MSG";
    public static final String INVALID_NEW_EMAIL_MSG = "INVALID_NEW_EMAIL_MSG";
    public static final String INVALID_NEW_FIRST_NAME_MSG = "INVALID_NEW_FIRST_NAME_MSG";
    public static final String INVALID_NEW_SURNAME_MSG = "INVALID_NEW_SURNAME_MSG";
    public static final String INVALID_NEW_PASSWORD_MSG = "INVALID_NEW_PASSWORD_MSG";

    public static final String CREDENTIALS_VALIDITY = "CREDENTIALS_VALIDITY";
    public static final String NEW_EMAIL_VALIDITY = "NEW_EMAIL_VALIDITY";
    public static final String NEW_FIRST_NAME_VALIDITY = "NEW_FIRST_NAME_VALIDITY";
    public static final String NEW_SURNAME_VALIDITY = "NEW_SURNAME_VALIDITY";
    public static final String NEW_PASSWORD_VALIDITY = "NEW_PASSWORD_VALIDITY";

    private static final String INVALID_CREDENTIALS = "Email or password is not valid.";
    private static final String INVALID_EMAIL = "Not a valid email address.";
    private static final String EXISTING_EMAIL = "This email address is already in use.";
    private static final String EMPTY_STRING = "Cannot be empty.";
    private static final String NEW_PASSWORD = "Has to be at least 5 characters long.";

    public static boolean validateEmail(HttpServletRequest request, String email) {
        boolean valid = EmailValidator.getInstance().isValid(email);
        evaluateErrorMessage(request, valid, ParamsValidator.CREDENTIALS_VALIDITY,
                ParamsValidator.INVALID_CREDENTIALS_MSG, INVALID_CREDENTIALS);
        return valid;
    }

    public static boolean validatePassword(HttpServletRequest request, User user, String password) {
        boolean valid = user != null && BCrypt.checkpw(password, user.getPasswordHash());
        evaluateErrorMessage(request, valid, ParamsValidator.CREDENTIALS_VALIDITY,
                ParamsValidator.INVALID_CREDENTIALS_MSG, INVALID_CREDENTIALS);
        return valid;
    }

    public static boolean validateNewPassword(HttpServletRequest request, String password) {
        boolean valid = password != null && password.length() >= 5;
        evaluateErrorMessage(request, valid, ParamsValidator.NEW_PASSWORD_VALIDITY,
                ParamsValidator.INVALID_NEW_PASSWORD_MSG, NEW_PASSWORD);
        return valid;
    }

    public static boolean validateNewEmail(HttpServletRequest request, String email) {
        boolean valid = EmailValidator.getInstance().isValid(email);
        evaluateErrorMessage(request, valid, ParamsValidator.NEW_EMAIL_VALIDITY,
                ParamsValidator.INVALID_NEW_EMAIL_MSG, INVALID_EMAIL);
        if (valid) {
            valid = Managers.getUserManager().findOneByEmail(email) == null;
            evaluateErrorMessage(request, valid, ParamsValidator.NEW_EMAIL_VALIDITY,
                    ParamsValidator.INVALID_NEW_EMAIL_MSG, EXISTING_EMAIL);
        }
        return valid;
    }

    public static boolean validateNewFirstName(HttpServletRequest request, String firstName) {
        boolean valid = !ObjectUtils.isEmpty(firstName);
        evaluateErrorMessage(request, valid, ParamsValidator.NEW_FIRST_NAME_VALIDITY,
                ParamsValidator.INVALID_NEW_FIRST_NAME_MSG, EMPTY_STRING);
        return valid;
    }

    public static boolean validateNewSurname(HttpServletRequest request, String surname) {
        boolean valid = !ObjectUtils.isEmpty(surname);
        evaluateErrorMessage(request, valid, ParamsValidator.NEW_SURNAME_VALIDITY,
                ParamsValidator.INVALID_NEW_SURNAME_MSG, EMPTY_STRING);
        return valid;
    }

    public static boolean validateVisualisation(HttpServletRequest request, String visualisation) {
        try {
            LoginUtils.checkAuthorization(CartType.valueOf(visualisation), request);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static void evaluateErrorMessage(HttpServletRequest request, boolean valid, String validityAttrName, String msgAttrName, String msgValueName) {
        if (!valid) {
            request.setAttribute(validityAttrName, false);
            request.setAttribute(msgAttrName, msgValueName);
        }
    }
}
