package ait.servlet;

import ait.db.DbException;
import ait.entity.Visualisation;
import ait.servlet.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "cart")
public class CartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(RequestParams.ACTION);
        boolean validRequest = false;

        if (LoginUtils.isLoggedIn(request)) {
            if (RequestParams.DELETE.equals(action)) {
                Visualisation visualisation = ParamsValidator.validateVisualisation(request.getParameter(RequestParams.VISUALISATION_ID));
                if (visualisation != null) {
                    ShoppingUtils.removeFromCart(request, visualisation);
                    validRequest = true;
                    response.sendRedirect(Path.CART_URL);
                }

            } else if (RequestParams.BUY.equals(action)) {
                validRequest = true;
                try {
                    ShoppingUtils.buy(request);
                    response.sendRedirect(Path.ORDER_HISTORY_URL);
                } catch (DbException e) {
                    response.sendRedirect(Path.CART_URL);
                }
            }
        }

        if (!validRequest) {
            response.sendRedirect(Path.DATASETS_URL);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginUtils.isLoggedIn(request)) {
            LoginUtils.setLoginAttributes(request);
            request.getRequestDispatcher(Path.CART_FILE).forward(request, response);
        } else {
            response.sendRedirect(Path.DATASETS_URL);
        }
    }
}
