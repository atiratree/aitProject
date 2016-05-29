package ait.servlet;

import ait.entity.CartType;
import ait.servlet.utils.LoginUtils;
import ait.servlet.utils.Path;
import ait.servlet.utils.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by studamit on 01/05/2016.
 */
@WebServlet(name = "login")
public class LoginServlet extends HttpServlet {
    private static final String ACTION_SIGN_UP = "signup";
    private static final String ACTION_LOGOUT = "logout";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(RequestParams.EMAIL);
        String password = request.getParameter(RequestParams.PASSWORD);
        String action = request.getParameter(RequestParams.ACTION);
        boolean result = false;

        if (ACTION_SIGN_UP.equals(action)) {
            String firstName = request.getParameter(RequestParams.FIRST_NAME);
            String surname = request.getParameter(RequestParams.SURNAME);

            result = LoginUtils.signUp(request, response, email, firstName, surname, password);
            if (!result) {
                request.setAttribute(RequestParams.EMAIL, email);
                request.setAttribute(RequestParams.FIRST_NAME, firstName);
                request.setAttribute(RequestParams.SURNAME, surname);
                request.setAttribute(LoginUtils.SIGNING_UP_ATTR, true);
            }
        } else {
            result = LoginUtils.login(request, response, email, password);
            if (!result) {
                request.setAttribute(RequestParams.EMAIL, email);
                request.setAttribute(LoginUtils.SIGNING_UP_ATTR, false);
            }
        }

        if (result) {
            response.sendRedirect(Path.DATASETS_URL);
        } else {
            request.getRequestDispatcher(Path.LOGIN_FILE).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(RequestParams.ACTION);
        if (ACTION_LOGOUT.equals(action)) {
            LoginUtils.logout(request);
        }

        if (LoginUtils.isLoggedIn(request)) {
            response.sendRedirect(Path.DATASETS_URL);
        } else {
            request.getRequestDispatcher(Path.LOGIN_FILE).forward(request, response);
        }
    }
}
