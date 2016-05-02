package ait.servlet;

import ait.utils.LoginUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by suomiy on 3/13/16.
 */
@WebServlet(name = "/bookList")
public class BookListServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        LoginUtils.setLoginAttributes(request);
//        request.getRequestDispatcher("bookList.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginUtils.setLoginAttributes(request);
        request.getRequestDispatcher("bookList.jsp").forward(request, response);
    }
}
