package ait.servlet.utils;

import ait.db.Managers;
import ait.entity.User;
import ait.entity.Visualisation;
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

    /**
     * To validate the e-mail of the user.
     * @param request:HttpServletRequest
     * @param email: Validating email.
     * @return: true if the e-mail is valid and vice-versa.
     */
    public static boolean validateEmail(HttpServletRequest request, String email) {
        boolean valid = EmailValidator.getInstance().isValid(email);
        evaluateErrorMessage(request, valid, ParamsValidator.CREDENTIALS_VALIDITY,
                ParamsValidator.INVALID_CREDENTIALS_MSG, INVALID_CREDENTIALS);
        return valid;
    }

    /**
     * To validate the password.
     * @param request: HttpServletRequest request
     * @param user: User whose password to validate
     * @param password: Validating password.
     * @return: true if the password is valid or vice-versa.
     */
    public static boolean validatePassword(HttpServletRequest request, User user, String password) {
        boolean valid = user != null && BCrypt.checkpw(password, user.getPasswordHash());
        evaluateErrorMessage(request, valid, ParamsValidator.CREDENTIALS_VALIDITY,
                ParamsValidator.INVALID_CREDENTIALS_MSG, INVALID_CREDENTIALS);
        return valid;
    }

    /**
     * To validate the new user's password.
     * @param request:HttpServletRequest
     * @param password: Validating password.
     * @return: true if the password is valid or vice-versa.
     */
    public static boolean validateNewPassword(HttpServletRequest request, String password) {
        boolean valid = password != null && password.length() >= 5;
        evaluateErrorMessage(request, valid, ParamsValidator.NEW_PASSWORD_VALIDITY,
                ParamsValidator.INVALID_NEW_PASSWORD_MSG, NEW_PASSWORD);
        return valid;
    }

    /**
     * To validate the new e-mail.
     * @param request:HttpServletRequest
     * @param email: Validating e-mail.
     * @return true if the emial is valid or vice-versa.
     */
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

    /**
     * To validate the new first name.
     * @param request:HttpServletRequest
     * @param firstName: first name to validate.
     * @return: true if the  first name is valid or vice-versa.
     */
    public static boolean validateNewFirstName(HttpServletRequest request, String firstName) {
        boolean valid = !ObjectUtils.isEmpty(firstName);
        evaluateErrorMessage(request, valid, ParamsValidator.NEW_FIRST_NAME_VALIDITY,
                ParamsValidator.INVALID_NEW_FIRST_NAME_MSG, EMPTY_STRING);
        return valid;
    }

    /**
     * To validate the new surname.
     * @param request :HttpServletRequest
     * @param surname: Validating surname
     * @return: true if the surname is valid or vice-versa.
     */
    public static boolean validateNewSurname(HttpServletRequest request, String surname) {
        boolean valid = !ObjectUtils.isEmpty(surname);
        evaluateErrorMessage(request, valid, ParamsValidator.NEW_SURNAME_VALIDITY,
                ParamsValidator.INVALID_NEW_SURNAME_MSG, EMPTY_STRING);
        return valid;
    }

    /**
     * To validate the visualisation.
     * @param request:HttpServletRequest
     * @param visualisation: Validating visualisation.
     * @return: ture if the visualisation is valid or vice-versa.
     */
    public static boolean validateVisualisationAuthorization(HttpServletRequest request, String visualisation) {
        try {
            LoginUtils.checkAuthorization(request, Visualisation.valueOf(visualisation));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Visualisation validateVisualisation(String visualisation) {
        try {
            return Visualisation.valueOf(visualisation);
        } catch (Exception ex) {
            return null;
        }
    }

    private static void evaluateErrorMessage(HttpServletRequest request, boolean valid, String validityAttrName, String msgAttrName, String msgValueName) {
        if (!valid) {
            request.setAttribute(validityAttrName, false);
            request.setAttribute(msgAttrName, msgValueName);
        }
    }
}
