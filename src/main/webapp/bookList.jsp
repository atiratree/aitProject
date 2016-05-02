<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="ait.Database" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.io.IOException" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ait.entity.Book" %>
<%@ page import="ait.utils.LoginUtils" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="./css/books.css">
    <script src="./js/booksScript.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>
    <body>
    <%

        if((Boolean) request.getAttribute(LoginUtils.LOGGED_IN_ATTR))
        {
    %>
        <div id="signOutDiv">
            <Button type="button" onclick="singIn()">Sign Out</Button>
        </div>
    <%
        }else{
    %>
        <div id="signInDiv">
            <Button type="button" onclick="singIn()">Login</Button>
        </div>
    <%
        }
    %>
        <div id="container">
            <%
                Book[] books= new Book[10];
                for(int i =0; i <10; i++)
                {
                    Book book= new Book("Java","AmitSolanki","1234fde","UNIBZ",1991,"http://images.amazon.com/images/P/0002005018.01.MZZZZZZZ.jpg");
                    books[i]=book;                }
                for(int i=0; i< 10; i++){

            %>
                <div id="contnetDiv">
                    <label> <%=books[i].getBookTitle()%> </label>
                    <label> <%=books[i].getBookAuthor()%> </label>
                    <label> <%=books[i].getIsbnNumber()%> </label>
                    <label> <%=books[i].getPublisgerName()%> </label>
                    <label> <%=books[i].GetPublicationYear()%> </label>
                    <img src= <%=books[i].GetImageURL()%> > </img>
                    <Button type="button" onclick="addToCart()">Add TO Cart</Button>
                </div>

                <% } %>
        </div>



</body>
</html>