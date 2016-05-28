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
@WebServlet(name = "boughtItems")
public class BoughtItemsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginUtils.isLoggedIn(request)) {
            request.getRequestDispatcher(Path.BOUGHT_ITEMS_FILE).forward(request, response);
        } else
            request.getRequestDispatcher(Path.LOGIN_FILE).forward(request, response);
    }
}
