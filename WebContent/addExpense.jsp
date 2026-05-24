<!DOCTYPE html>
<html>
<head>
<title>Add Expense</title>
<meta charset="UTF-8">
</head>
<body>

<h2>Add Expense</h2>

<form action="AddExpenseServlet" method="post">

Amount:
<input type="number" name="amount"><br><br>

Payment Type:
<select name="paymentType">

<option>PhonePe</option>
<option>Google Pay</option>
<option>Cash</option>

</select>

<br><br>

Description:
<input type="text" name="description">

<br><br>

<input type="submit" value="Add Expense">

</form>

</body>
</html>