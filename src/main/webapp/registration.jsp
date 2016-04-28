<%@ page import="java.util.Enumeration" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<body>
    <article>
        <header>
            <h1>Results received by servlet
            </h1>
        </header>

        <section>
                <h4>Query String</h4>
                <%= request.getQueryString()%>

                <h4>All Values</h4>
                <ul>
                    <%
                        Enumeration<String> params = request.getParameterNames();

                        while (params.hasMoreElements()) {
                            String paramName = params.nextElement();
                            String param = request.getParameter(paramName); %>
                    <li><%= paramName %>: <%= param %>
                    </li>
                    <%
                        } %>

                </ul>
        </section>

        <section>
            <h4>Some Selected Values</h4>
            <ul>
                <li>Name: <%= request.getParameter("firstName") %>
                <li>Last Name: <%= request.getParameter("lastName") %>
                <li>Email : <%= request.getParameter("email") %>
            </ul>
        </section>
        <footer>Unibz</footer>
    </article>
</body>
</html>