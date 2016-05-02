package ait.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by studamit on 01/05/2016.
 */
@WebServlet(name = "SignUpServlet")
public class SignUpServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String eMail=request.getParameter("email");
        String password=request.getParameter("password");
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");

        if( (!eMail.equalsIgnoreCase(""))&& (!password.equalsIgnoreCase(""))&&
                (!firstName.equalsIgnoreCase("")) && (!lastName.equalsIgnoreCase("")))
        {
            // save the user data into the database
            // and redirect user to Book.jsp page
            response.sendRedirect("Books.html");
        }
        else response.sendRedirect("login.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

}
