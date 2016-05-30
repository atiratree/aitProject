package ait.servlet;

import ait.servlet.utils.LoginUtils;
import ait.servlet.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by studamit on 28/05/2016.
 */

@WebServlet(name = "orderHistory")
public class OrderHistoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginUtils.isLoggedIn(request)) {
            LoginUtils.setLoginAttributes(request);
            request.getRequestDispatcher(Path.ORDER_HISTORY_FILE).forward(request, response);
        } else
            response.sendRedirect(Path.DATASETS_URL);
    }
}
