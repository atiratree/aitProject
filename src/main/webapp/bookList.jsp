<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="ait.Database" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="ait.entity.User" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>
<body>

    <h2>Hello, please log in:</h2>
    <br><br>
    <form action="j_security_check" method=post>
        <p><strong>Please Enter Your User Name: </strong>
            <input type="text" name="j_username" size="25">
        <p><p><strong>Please Enter Your Password: </strong>
        <input type="password" size="15" name="j_password">
        <p><p>
        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
    </form>
    <section>
        <h4>bookList servlet's Info</h4>
        <% User user = (User) request.getAttribute("bookListUser");%>

        <%= user.getName() + " " + user.getSurname()%>
    </section>

    <%
        try (Connection connection = Database.getConnection()){
            Statement statement = connection.createStatement();
            String sql = "select * from public.user";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                System.out.println(rs.getString("id") + rs.getString("name") + rs.getString("surname"));
        }
    %>

    <!--<form action="registration" method="get" autocomplete>
        <label>First Name </label> <input type="text" name="firstName" placeholder="First Name" required/> <br/>
        <label>Last Name </label> <input type="text" name="lastName" placeholder="Last Name" required/> <br>
        <label>Email </label> <input type="email" name="email" placeholder="EMail" required/> <br>
        <label>Phone Number </label> <input type="tel" name="telePhoneNumber" required/> <br>
        <label>Organization </label> <input type="text" name="organization" placeholder="organization"/> <br>

        <label>Address </label> <input type="text" name="address" placeholder="organization" required/> <br>
        <label>URL Homepage </label> <input type="url" name="urlhomepage" placeholder="Homepage URL"/> <br>
        <label>City </label> <input type="text" name="city" placeholder="organization" list="cityList"/>
        <datalist id="cityList">
            <option value="Bolzano">
            <option value="Brixen">
            <option value="Brunico">
        </datalist>
        <br>
        <label>Zipcode </label> <input type="number" name="zipcode" required/> <br>
        <label>Payment Mode </label> <input type="text" name="paymentMode" list="paymentList"/>
        <datalist id="paymentList">
            <option value="Paypal">
            <option value="Cash">
            <option value="Credit Card">
        </datalist>
        <br>
        <label>Total To Pay </label> <input type="num" min=10 max=150 name="totalPaid" required/> <label>Euro</label><br>
        <label>Start Date </label> <input type="date" name="startDate"/> <br>
        <label>Favorite Color </label> <input type="color" name="favoriteColor"/> <br>
        <input type="submit" value="Submit">
    </form> -->
</body>
</html>