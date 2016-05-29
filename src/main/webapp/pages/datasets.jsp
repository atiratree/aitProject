<%@ page import="ait.entity.Visualisation" %>
<%@ page import="ait.servlet.utils.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Datasets</title>
    <link rel="stylesheet" type="text/css" href="../js/lib/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/datasets.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <script src="../js/lib/jquery.min.js"></script>
    <script src="../js/common.js"></script>
    <meta charset="utf-8">
</head>
<body>

<div class="container">


    <h1> Visualisation Store
    </h1>
    <div class="block" style="padding-bottom: 2em">
        <div class="inline smallMarginLeft pull-right">
            <% if (RequestUtils.getBooleanParam(request, LoginUtils.LOGGED_IN_ATTR)) { %>
            <button type="button" class="btn btn-default" onclick="logout()">Sign out</button>
            <% } else { %>
            <button type="button" class="btn btn-default" onclick="login()">Sign in</button>
            <% } %>
        </div>
        <% if (RequestUtils.getBooleanParam(request, LoginUtils.LOGGED_IN_ATTR)) { %>
        <div class="inline smallMarginLeft pull-right">
            <button type="button" class="btn btn-default" onclick="window.location='<%=Path.ORDER_HISTORY_URL%>'">Order
                history
            </button>
        </div>
        <div class="inline smallMarginLeft pull-right">
            <button type="button" class="btn btn-default" onclick="window.location='<%=Path.CART_URL%>'">Cart</button>
        </div>
        <% } %>

    </div>
    <div class="block" style="margin-top: 3em;">
        <%
            Visualisation[] items = Visualisation.values();
            boolean allBought = true;
            for (Visualisation type : items) {
                if (!ShoppingUtils.doesUserOwnVisualisation(request, type)) {
                    allBought = false;
        %>
        <div class="panel-group">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>
                        <%= type.getLabel()%>
                    </strong>
                </div>
                <div class="panel-body">
                    <p>
                        <%= type.getDescription()%>
                    </p>
                    <% if (ShoppingUtils.isItemInSessionCart(request, type)) { %>
                    <button class="btn btn-info" id="temperatureButton" style="cursor: default" disabled>
                        Added to Cart
                    </button>
                    <%} else {%>
                    <form action="<%=Path.ADD_TO_CART_URL%>" method="post">
                        <button class="btn btn-default" name="<%=RequestParams.VISUALISATION_ID%>"
                                value="<%=type.name()%>">Add to Cart
                        </button>
                    </form>
                    <% }%>
                </div>
            </div>
        </div>
        <%
                }
            }
            if (allBought) {
        %>
        <div class="well well-blue">
            <label>All visualisation have been bought!
            </label>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>