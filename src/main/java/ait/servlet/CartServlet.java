package ait.servlet;

import ait.servlet.utils.LoginUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    }
}
