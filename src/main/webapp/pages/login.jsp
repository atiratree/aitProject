<%@ page import="ait.servlet.utils.LoginUtils" %>
<%@ page import="ait.servlet.utils.ParamsValidator" %>
<%@ page import="ait.servlet.utils.RequestParams" %>
<%@ page import="ait.servlet.utils.RequestUtils" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../js/lib/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <script src="../js/lib/jquery.min.js"></script>
    <script src="../js/login.js"></script>
    <title>Login</title>
</head>
<%
    final String HIDDEN = "hidden";
    String hideSignIn = "";
    String hideSignUp = "";

    if (RequestUtils.getBooleanParam(request, LoginUtils.SIGNING_UP_ATTR, false)) {
        hideSignIn = HIDDEN;
    } else {
        hideSignUp = HIDDEN;
    }
%>

<body>

<div id="mainDiv">
    <div id="signInDiv" <%= hideSignIn%> >
        <form class="container" method="post" action="/login" id="signInForm">
            <div class="block">
                <div class="inline">
                    <label>Email</label>
                </div>
                <div class="inline">
                    <input class="pull-right" required type="email" name="email"
                           value="suomiy@gmail.com<%= RequestUtils.getStringParam(request, RequestParams.EMAIL)%>">
                </div>
            </div>
            <div class="block">
                <div class="inline">
                    <label>Password</label>
                </div>
                <div class="inline">
                    <input class="pull-right" required type="password" name="password" value="suomi"/>
                </div>
            </div>
            <% if (!RequestUtils.getBooleanParam(request, ParamsValidator.CREDENTIALS_VALIDITY, true)) {%>
            <div class="block">
                <div class="inline ">
                    <label class="errorLabel"><%=RequestUtils.getStringParam(request, ParamsValidator.INVALID_CREDENTIALS_MSG)%>
                    </label>
                </div>
            </div>
            <% } %>
            <div class="block">
                <div class="inline"></div>
                <div class="inline">
                    <span class="pull-right">
                        <a class="toggleLogin">Sign Up</a>
                        <input class="btn btn-primary" type="submit" value="Sign In"/>
                    </span>

                </div>
            </div>

        </form>

    </div>

    <div id="signUpDiv" <%= hideSignUp%> >
        <form class="container" method="post" action="/login?action=signup" id="signUpForm">
            <div class="block">
                <div class="inline">
                    <label>Email</label>
                </div>
                <div class="inline">
                    <input class="pull-right widthFull" required type="email" name="email"
                           value="<%= RequestUtils.getStringParam(request, RequestParams.EMAIL)%>"/>
                </div>
                <% if (!RequestUtils.getBooleanParam(request, ParamsValidator.NEW_EMAIL_VALIDITY, true)) {%>
                <div class="inline ">
                    <label class="errorLabel paddingLeft"><%= RequestUtils.getStringParam(request, ParamsValidator.INVALID_NEW_EMAIL_MSG)%>
                    </label>
                </div>
                <% } %>
            </div>
            <div class="block">
                <div class="inline">
                    <label>First name</label>
                </div>
                <div class="inline">
                    <input class="pull-right widthFull" required type="text" name="firstname"
                           value="<%= RequestUtils.getStringParam(request, RequestParams.FIRST_NAME)%>"/>
                </div>
                <% if (!RequestUtils.getBooleanParam(request, ParamsValidator.NEW_FIRST_NAME_VALIDITY, true)) {%>
                <div class="inline ">
                    <label class="errorLabel paddingLeft"><%= RequestUtils.getStringParam(request, ParamsValidator.INVALID_NEW_FIRST_NAME_MSG)%>
                    </label>
                </div>
                <% } %>
            </div>
            <div class="block">
                <div class="inline">
                    <label>Surname</label>
                </div>
                <div class="inline">
                    <input class="pull-right widthFull" required type="text" name="surname"
                           value="<%= RequestUtils.getStringParam(request, RequestParams.SURNAME)%>"/>
                </div>
                <% if (!RequestUtils.getBooleanParam(request, ParamsValidator.NEW_SURNAME_VALIDITY, true)) {%>
                <div class="inline ">
                    <label class="errorLabel paddingLeft"><%= RequestUtils.getStringParam(request, ParamsValidator.INVALID_NEW_SURNAME_MSG)%>
                    </label>
                </div>
                <% } %>
            </div>
            <div class="block">
                <div class="inline">
                    <label>Password</label>
                </div>
                <div class="inline">
                    <input class="pull-right widthFull" minlength="5" required type="password" name="password"/>
                </div>
                <% if (!RequestUtils.getBooleanParam(request, ParamsValidator.NEW_PASSWORD_VALIDITY, true)) {%>
                <div class="inline ">
                    <label class="errorLabel paddingLeft"><%= RequestUtils.getStringParam(request, ParamsValidator.INVALID_NEW_PASSWORD_MSG)%>
                    </label>
                </div>
                <% } %>
            </div>
            <div class="block">
                <div class="inline"></div>
                <div class="inline">
                    <span class="pull-right">
                         <a class="toggleLogin">Sign In</a>
                        <input class="btn btn-primary" type="submit" value="Sign Up"/>
                    </span>
                </div>
            </div>
        </form>
    </div>
</div>


</body>
</html>