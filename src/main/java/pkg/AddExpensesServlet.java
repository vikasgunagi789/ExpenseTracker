package pkg;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AddExpenseServlet")
public class AddExpensesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        HttpSession session = request.getSession();

        int userId = (Integer) session.getAttribute("userId");

        double amount =
        Double.parseDouble(request.getParameter("amount"));

        String paymentType =
        request.getParameter("paymentType");

        String description =
        request.getParameter("description");

        try {

            Connection con = DBConnection.getConnection();

            String query =
            "INSERT INTO expenses(user_id,amount,payment_type,description,expense_date) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userId);
            ps.setDouble(2, amount);
            ps.setString(3, paymentType);
            ps.setString(4, description);
            ps.setDate(5,
            java.sql.Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

            response.sendRedirect(
            	    request.getContextPath() + "/dashboard.jsp"
            	);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}