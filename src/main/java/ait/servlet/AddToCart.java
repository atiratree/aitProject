package ait.servlet;

import ait.entity.CartType;
import ait.entity.User;
import ait.servlet.utils.LoginUtils;
import ait.servlet.utils.Path;
import ait.servlet.utils.RequestParams;
import ait.servlet.utils.ShoppingCartUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by studamit on 27/05/2016.
 */
@WebServlet(name = "/addToCart")
/**
 * Servlet class to add the items into cart.
 */
public class AddToCart extends HttpServlet {
    /**
     * Do post function.
     * @param request:HttpServletRequest request
     * @param response:HttpServletResponse response
     * @throws ServletException: in case of failed state.
     * @throws IOException: in case of failed state.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Do get function. add the items into cart.
     * @param request:HttpServletRequest request
     * @param response:HttpServletResponse response
     * @throws ServletException: in case of failed state.
     * @throws IOException: n case of failed state.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CartType cartType = ShoppingCartUtils.getCartType(request.getParameter("visualisation"));
        User user = LoginUtils.getUserFromSession(request);

        // adding the items into the cart
        if (user != null) {
            if (cartType != null) {
                ShoppingCartUtils.saveCartTypeInSession(request,user,cartType);
            }

            //request.getRequestDispatcher(Path.DATASETS_FILE).forward(request, response);
            response.sendRedirect(Path.DATASETS_URL);
//            // in case of buying the items
//            else if (carts.contains("buyItems")) {
//                ArrayList<CartType> cartItemsFromSession = ShoppingCartUtils.getCartItemsFromSession(request);
//                if (cartItemsFromSession != null && cartItemsFromSession.size() > 0) {
//                    request.getRequestDispatcher(Path.CART_File).forward(request, response);
//                } else {
//                    request.getRequestDispatcher(Path.DATASETS_FILE).forward(request, response);
//                }
//            }
        } else
            request.getRequestDispatcher(Path.LOGIN_FILE).forward(request, response);
    }
}
