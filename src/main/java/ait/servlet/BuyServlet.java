package ait.servlet;

import ait.db.DbException;
import ait.entity.CartType;
import ait.entity.ShoppingCart;
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
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * Created by studamit on 19/05/2016.
 */
@WebServlet(name = "Servlet")
public class BuyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // save the cart and cart items in the database
        try {
            if (LoginUtils.isLoggedIn(request)) {

                ArrayList<CartType> cartTypesFromSession = ShoppingCartUtils.getCartItemsFromSession(request);
                User user = LoginUtils.getUserFromSession(request);
                OffsetDateTime dateTime = OffsetDateTime.now(ZoneId.of("UTC"));

                if (cartTypesFromSession != null && cartTypesFromSession.size() > 0) {
                    // save the shopping cart
                    ShoppingCart shoppingCart = new ShoppingCart(user.getId(), dateTime);
                    ShoppingCart savedCart = ShoppingCartUtils.saveShoppingCartInDatabase(shoppingCart);

                    for (int i = 0; i < cartTypesFromSession.size(); i++) {
                        boolean currentSavingCartItem = ShoppingCartUtils.hasItemBought(user, cartTypesFromSession.get(i));
                        if (!currentSavingCartItem) {
                            // save the cart item into the cartTypesFromSession
                            ShoppingCartUtils.saveCartItemsInDatabase(savedCart.getId(), cartTypesFromSession.get(i));

                            // now delete the current bought item from the cart session
                            ShoppingCartUtils.deleteCartItemsFromSession(request, cartTypesFromSession.get(i));
                        }
                    }
                    request.getRequestDispatcher(Path.BOUGHT_ITEMS_FILE).forward(request, response);

                } else
                    request.getRequestDispatcher(Path.DATASETS_FILE).forward(request, response);
            } else
                request.getRequestDispatcher(Path.LOGIN_FILE).forward(request, response);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
