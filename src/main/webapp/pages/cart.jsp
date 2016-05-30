<%@ page import="ait.entity.CartItem" %>
<%@ page import="ait.entity.User" %>
<%@ page import="ait.entity.Visualisation" %>
<%@ page import="ait.servlet.utils.LoginUtils" %>
<%@ page import="ait.servlet.utils.Path" %>
<%@ page import="ait.servlet.utils.RequestParams" %>
<%@ page import="ait.servlet.utils.ShoppingUtils" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: studamit
  Date: 13/05/2016
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" type="text/css" href="../js/lib/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/datasets.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <script src="../js/lib/jquery.min.js"></script>
    <script src="../js/common.js"></script>>
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
        %>
        <div class="well well-honey">
            <label>Welcome <%= user.getName() %>
            </label><br/>
            <label>Your cart contains the following items:</label>
        </div>

        <div class="panel-group">
            <%
                List<CartItem> items = ShoppingUtils.getSessionCart(request).getItems();
                for (CartItem item : items) {
                    Visualisation visualisation = item.getVisualisation();
            %>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>
                        <%= visualisation.getLabel()%>
                    </strong>
                </div>
                <div class="panel-body">
                    <p><i><%= visualisation.getTypeOfGraph()%>:</i> <%=visualisation.getDescription()%>
                    </p>
                    <form action="<%=String.format("%s?%s=%s",Path.CART_URL, RequestParams.ACTION, RequestParams.DELETE)%>"
                          method="post">
                        <button class="btn btn-danger" name="<%=RequestParams.VISUALISATION_ID%>"
                                value="<%=visualisation.name()%>">Remove
                        </button>
                    </form>
                </div>
            </div>
            <%}%>
        </div>
        <form action="<%=String.format("%s?%s=%s",Path.CART_URL, RequestParams.ACTION, RequestParams.BUY)%>"
              method="post">
            <button class="btn btn-success minWidth8" <%= items.isEmpty() ? "disabled" : "" %> style="margin-bottom: 3em">
                Buy
            </button>
        </form>
    </div>
</div>
</body>
</html>
