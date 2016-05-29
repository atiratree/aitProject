<%@ page import="ait.entity.CartType" %>
<%@ page import="ait.entity.User" %>
<%@ page import="ait.servlet.utils.LoginUtils" %>
<%@ page import="ait.servlet.utils.ShoppingCartUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="./js/lib/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/datasets.css">
    <script src="./js/lib/jquery.min.js"></script>
    <script src="./js/common.js"></script>
    <script src="./js/datasets.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body style="width: 50%;">

<div class="container">

    <div class="loginInfo">
        <%
            boolean isLoggedIn = LoginUtils.isLoggedIn(request);
            User user = LoginUtils.getUserFromSession(request);
            if (isLoggedIn) {
        %>
        <button type="button" class="btn btn-default" onclick="logout()">Sign out</button>
        <%
        } else {
        %>
        <button type="button" class="btn btn-default" onclick="login()">Sign in</button>
        <%
            }
        %>
    </div>

    <%
        CartType[] items = CartType.values();

        for (CartType type : items) {

    %>
    <div class="panel-group">

        <div class="panel panel-default">
            <div class="panel-heading"><%out.println(type.getLabel());%></div>
            <div class="panel-body">
                <label><%out.println(type.getDescription());%></label><br/>
                <%
                    boolean hasItemBought = ShoppingCartUtils.hasItemBought(user, type);
                    boolean isItemIsInSession = ShoppingCartUtils.isItemInSession(request, user, type);

                    if (hasItemBought) {%>
                <button type="button" class="btn btn-default" disabled style="background-color: cadetblue">Already
                    bought
                </button>
                <% } else if (isItemIsInSession) { %>
                <button type="button" id="temperatureButton" disabled style="background-color: cadetblue">
                    Added to cart
                </button>

                <%} else {%>
                <button type="button" class="btn btn-default" onclick="addItemToCart('<%=type.name()%>')">
                    Add To cart
                </button>

                <%
                        }%>

            </div>
        </div>
    </div>
<%
    }
%>
    <button type="button" class="btn btn-default" onclick="buyItems()">Buy items</button>
    <button type="button" class="btn btn-default" onclick="boughtItems()">Bought Items</button>
</div>
</body>
</html>