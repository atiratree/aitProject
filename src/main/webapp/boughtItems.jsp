<%@ page import="ait.entity.CartItem" %>
<%@ page import="ait.entity.ShoppingCart" %>
<%@ page import="ait.entity.User" %>
<%@ page import="ait.servlet.utils.LoginUtils" %>
<%@ page import="ait.servlet.utils.ShoppingCartUtils" %>
<%@ page import="java.util.List" %>
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
    <title>Bought Item</title>
    <link rel="stylesheet" type="text/css" href="./js/lib/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/tempreture.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="./js/lib/jquery.min.js"></script>
    <script src="./js/common.js"></script>
    <script src="./js/boughtItems.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/cart.css">
</head>

<body>
<div class="container">

    <div class="loginInfo">
        <%
            boolean isLoggedIn = LoginUtils.isLoggedIn(request);
            User user = LoginUtils.getUserFromSession(request);
            if (isLoggedIn) {
        %>
        <button type="button" class="btn btn-default" onclick="logOut()">Sign out</button>
        <button type="button" class="btn btn-default" onclick="homeButtonClicked()">Home</button>
        <div class="well">
            <label>Welcome :<% out.println(user.getName()); %></label><br/>
            <label>Your bought following items:</label>
        </div>

    </div>
    <div class="panel-group">
        <%
            List<ShoppingCart> boughtShoppingCart = ShoppingCartUtils.getShoppingCarts(user);
            if (boughtShoppingCart != null) {
                for (int i = 0; i < boughtShoppingCart.size(); i++) {
                    List<CartItem> cartItems = ShoppingCartUtils.getCartItems(boughtShoppingCart.get(i));

                    for (int j = 0; j < cartItems.size(); j++) {
        %>

        <div class="panel panel-default">
            <div class="panel-heading">Cart: <%out.println(boughtShoppingCart.get(i).getCreationDate());%></div>
            <div class="panel-body">
                <label><%=cartItems.get(j).getCart_type().getLabel()%>
                </label><br/>
                <label><%=cartItems.get(j).getCart_type().getDescription()%>
                </label><br/>
            </div>
        </div>
        <%
                    }
                }
            }
        %>
    </div>
        <%}%>
</body>
</html>
