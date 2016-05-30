package ait.servlet;

import ait.entity.Visualisation;
import ait.servlet.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by studamit on 27/05/2016.
 */
@WebServlet(name = "/addToCart")
public class AddToCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean validRequest = false;

        if (LoginUtils.isLoggedIn(request)) {
            Visualisation visualisation = ParamsValidator.validateVisualisation(request.getParameter(RequestParams.VISUALISATION_ID));
            if (visualisation != null) {
                ShoppingUtils.addToCart(request, visualisation);
                validRequest = true;
            }
        }

        if (validRequest) {
            response.sendRedirect(Path.DATASETS_URL);
        } else {
            response.sendRedirect(Path.LOGIN_URL);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Path.DATASETS_FILE).forward(request, response);

    }
}
