<%-- 
    Document   : login
    Created on : Apr 26, 2025, 8:58:20 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <p style="color: red;"><%=error%></p>
        <%
            }

            String enteredUsername = (String) request.getAttribute("username");
            if (enteredUsername == null) {
                enteredUsername = "";
            }
            enteredUsername = enteredUsername.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
        %>
        <h1>Login</h1>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login"/>
            <table>
                <tr>
                    <td><label for="username">Username:</label></td>
                    <td>
                        <input type="text" id="username" name="username"
                               value="<%=enteredUsername%>" required="required"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td>
                        <input type="password" id="password" name="password"
                               required="required"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Login"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
