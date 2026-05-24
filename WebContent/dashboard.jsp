<%@ page import="pkg.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.time.LocalDate" %>

<%
Integer userId = (Integer) session.getAttribute("userId");

if(userId == null){
    response.sendRedirect("login.jsp");
}
%>

<html>
<head>
<title>Dashboard</title>
<meta charset="UTF-8">
</head>

<body>

<h1>Welcome <%= session.getAttribute("name") %></h1>

<%
double total = 0;

try {

    Connection con = DBConnection.getConnection();

    String query =
    "SELECT SUM(amount) FROM expenses WHERE user_id=? AND expense_date=?";

    PreparedStatement ps = con.prepareStatement(query);

    ps.setInt(1, userId);
    ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));

    ResultSet rs = ps.executeQuery();

    if(rs.next()) {
        total = rs.getDouble(1);
    }

} catch(Exception e){
    e.printStackTrace();
}

double savings = 160 - total;
%>

<h2>Today's Expense: ₹<%= total %></h2>

<%
if(total > 160){
%>

<h2 style="color:red;">
Expensive Day ⚠️
</h2>

<%
} else {
%>

<h2 style="color:green;">
Savings: ₹<%= savings %>
</h2>

<%
}
%>

<a href="addExpense.jsp">Add Expense</a>

</body>
</html>