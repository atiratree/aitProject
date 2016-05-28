package ait.servlet;

import ait.db.DbException;
import ait.db.Tables;
import ait.entity.CartType;
import ait.entity.User;
import ait.servlet.utils.LoginUtils;
import ait.servlet.utils.Path;
import ait.servlet.utils.ShoppingCartUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by studamit on 02/05/2016.
 */
@WebServlet(name = "cart")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // write here the logic of the databse
        // to insert the data into the database about the cart
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginUtils.isLoggedIn(request)) {
            request.getRequestDispatcher("/").forward(request, response);
        }
        //if the user is logged in,add items to cart
        else {
            // extract the cart items and save them into the session
            try {
                onReceiveCartRequest(request, response);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    private void onReceiveCartRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DbException {
        String carts = request.getQueryString();
        User user = LoginUtils.getUserFromSession(request);
        ArrayList<CartType> cartItemsFromSession = ShoppingCartUtils.getCartItemsFromSession(request);

        if (user != null) {
            if (cartItemsFromSession != null && cartItemsFromSession.size() > 0) {

                // handling request of deleting a cart
                if (carts.contains("deleteItem")) {
                    CartType deletingCartType = ShoppingCartUtils.setCartTypes(carts);

                    if (deletingCartType != null) {
                        ShoppingCartUtils.deleteCartItemsFromSession(request, deletingCartType);
                        request.getRequestDispatcher(Path.CART_File).forward(request, response);
                    }
                }
                //handling to buy the item
                else if (carts.contains("buyItems")) {
                    // add the listener for the buying items
                }
            } else
                request.getRequestDispatcher(Path.DATASETS_FILE).forward(request, response);
        } else
            request.getRequestDispatcher(Path.LOGIN_FILE).forward(request, response);
    }

}
