package ait.servlet;

import ait.entity.User;
import ait.utils.LoginUtils;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // if the credentials are correct, redirect to the Books.jsp page
         //else stay on the same page
        User user = new User("user", "user");

        if (!email.equalsIgnoreCase("") && email.equalsIgnoreCase("amit@yahoo.com")) {
            if (!password.equalsIgnoreCase("") && password.equalsIgnoreCase("p")) {
                // redirect to Books page
                LoginUtils.login(request, user);
                response.sendRedirect("/");

            } else response.sendRedirect("login.html");
        } else response.sendRedirect("login.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.html").forward(request, response);
    }
}
