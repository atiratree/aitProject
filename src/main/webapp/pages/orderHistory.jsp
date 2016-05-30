<%@ page import="ait.db.Managers" %>
<%@ page import="ait.entity.CartItem" %>
<%@ page import="ait.entity.ShoppingCart" %>
<%@ page import="ait.entity.User" %>
<%@ page import="ait.entity.Visualisation" %>
<%@ page import="ait.servlet.utils.LoginUtils" %>
<%@ page import="ait.servlet.utils.Path" %>
<%@ page import="ait.servlet.utils.RequestParams" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%--
  Created by IntelliJ IDEA.
  User: studamit
  Date: 28/05/2016
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order History</title>
    <link rel="stylesheet" type="text/css" href="../js/lib/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/datasets.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <script src="../js/lib/jquery.min.js"></script>
    <script src="../js/common.js"></script>
</head>

<body>
<div class="container">
    <h1> Visualisation Store
    </h1>
    <div class="block" style="padding-bottom: 2em">
        <div class="inline smallMarginLeft pull-right">
            <button type="button" class="btn btn-default" onclick="homeButtonClicked()">Home</button>
        </div>
        <div class="inline smallMarginLeft pull-right">
            <button type="button" class="btn btn-default" onclick="logout()">Sign out</button>
        </div>
    </div>
    <div class="block" style="margin-top: 3em;">
        <%
            User user = (User) request.getAttribute(LoginUtils.USER_ATTR);
            DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
        %>
        <div class="well well-blue ">
            <label>Welcome <%= user.getName() %>
            </label><br/>
            <label>Your order history:</label>
        </div>

        <div class="panel-group">
            <% for (ShoppingCart cart : Managers.getShoppingCartManager().findByUser(user)) { %>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>Cart : <%= formatter.format(cart.getCreationDate())%>
                    </strong>
                </div>
                <div class="panel-body">
                    <% for (CartItem item : cart.getItems()) {
                        Visualisation visualisation = item.getVisualisation();
                    %>
                    <a href="<%=String.format("%s?%s=%s",Path.VISUALISATION_URL, RequestParams.VISUALISATION_ID, visualisation.name())%>">
                        <strong><%= visualisation.getLabel()%>
                        </strong>
                    </a>
                    <p>
                        <i><%= visualisation.getTypeOfGraph()%>:</i> <%= visualisation.getDescription()%>
                    </p>
                    <%
                        } %>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
