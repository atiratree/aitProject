package ait.servlet;

import ait.entity.Visualisation;
import ait.servlet.utils.LoginUtils;
import ait.servlet.utils.ParamsValidator;
import ait.servlet.utils.Path;
import ait.servlet.utils.RequestParams;
import ait.utils.TemperatureMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by suomiy on 5/27/16.
 */
@WebServlet(name = "visualisation")
public class VisualisationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean validRequest = false;

        if (LoginUtils.isLoggedIn(request)) {
            String visualisation = request.getParameter(RequestParams.VISUALISATION_ID);
            if (ParamsValidator.validateVisualisationAuthorization(request, visualisation)) {
                Visualisation type = Visualisation.valueOf(visualisation);
                LoginUtils.setLoginAttributes(request);
                request.setAttribute(RequestParams.VISUALISATION_ID, type.name());
                request.setAttribute(RequestParams.VISUALISATION_LABEL, type.getLabel());
                request.setAttribute(RequestParams.VISUALISATION_MIN_YEAR, TemperatureMapper.getMinYear(type));
                request.setAttribute(RequestParams.VISUALISATION_MAX_YEAR, TemperatureMapper.getMaxYear(type));
                request.getRequestDispatcher(Path.VISUALISATION_FILE).forward(request, response);
                validRequest = true;
            }

        }

        if (!validRequest) {
            response.sendRedirect(Path.DATASETS_URL);
        }
    }
}