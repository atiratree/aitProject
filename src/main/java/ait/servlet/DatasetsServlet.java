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
 * Created by suomiy on 3/13/16.
 */
@WebServlet(name = "datasets")
public class DatasetsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        LoginUtils.setLoginAttributes(request);
//        request.getRequestDispatcher("datasets.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginUtils.setLoginAttributes(request);
        request.getRequestDispatcher(Path.DATASETS_FILE).forward(request, response);
    }
}
