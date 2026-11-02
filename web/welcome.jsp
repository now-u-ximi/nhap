<%-- 
    Document   : welcome
    Created on : Apr 26, 2025, 8:58:34 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <%
            pe.model.AccountDto account
                    = (pe.model.AccountDto) session.getAttribute("account");
            if (account == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String fullName = account.getFullName() == null ? "" : account.getFullName();
            fullName = fullName.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
        %>
        <h1>Welcome to <%=fullName%></h1>

        <p>
            <a href="MainController?action=active">Active members</a> |
            <a href="MainController?action=inactive">Inactive members</a> |
            <a href="MainController?action=logout">Logout</a>
        </p>
    </body>
</html>
