<%@ page import="ait.servlet.utils.LoginUtils" %>
<%@ page import="ait.servlet.utils.RequestUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="./css/datasets.css">
    <script src="./js/datasets.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>
<body>
<div id="mainDiv">
    <%
        if (RequestUtils.getBooleanParam(request, LoginUtils.LOGGED_IN_ATTR)) {
    %>
    <div id="signOutDiv">
        <Button type="button" onclick="logout()">Sign Out</Button>
    </div>
    <%
    } else {
    %>
    <div id="signInDiv">
        <Button type="button" onclick="login()">Login</Button>
    </div>
    <%
        }
    %>
    <div id="container">
        Visualisation List
    </div>
</div>
</body>
</html>