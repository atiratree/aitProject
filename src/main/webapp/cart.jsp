<%@ page import="ait.entity.CartType" %>
<%@ page import="ait.entity.User" %>
<%@ page import="ait.servlet.utils.LoginUtils" %>
<%@ page import="ait.servlet.utils.ShoppingCartUtils" %>
<%@ page import="java.util.ArrayList" %>
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
    <link rel="stylesheet" type="text/css" href="./js/lib/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/tempreture.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="./js/lib/jquery.min.js"></script>
    <script src="./js/common.js"></script>
    <script src="./js/cart.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/cart.css">
</head>

<body>
<div class="container">
    <%
        User user = LoginUtils.getUserFromSession(request);
        if (user != null) {
    %>
    <div class="well">
        <label>Welcome :<% out.println(user.getName()); %></label><br/>
        <label>Your cart contains the following items:</label>
    </div>
    <div>
        <button type="button" class="btn btn-default" id="signOutButton" onclick="logout()">Sign out</button>
    </div>

    <div>
        <button type="button" class="btn btn-default" onclick="homeButtonClicked()">Home</button>
    </div>
    <%}%>


    <div class="panel-group">
        <%
            ArrayList<CartType> getSessionCartItems = ShoppingCartUtils.getCartItemsFromSession(request);
            for (int i = 0; i < getSessionCartItems.size(); i++) {

        %>

        <div class="panel panel-default" id="cartItemType<%=getSessionCartItems.get(i).ordinal()%>">
            <div class="panel-heading"><%out.println(getSessionCartItems.get(i).getLabel());%></div>
            <div class="panel-body">
                <label><%out.println(getSessionCartItems.get(i).getDescription());%></label><br/>
                <button type="button" class="btn btn-default"
                        onclick="deleteCartItem(<%=getSessionCartItems.get(i).ordinal()%>)">
                    Remove from cart
                </button>
            </div>
        </div>
        <%}%>
    </div>

    <button type="button" class="btn btn-default" onclick="checkOutItems()">Check out</button>
</div>
</body>
</html>
