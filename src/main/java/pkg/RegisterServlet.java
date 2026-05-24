package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)

            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            Connection con = DBConnection.getConnection();

            if(con == null){

                out.println("Database Connection Failed");
                return;
            }

            String query =
            "INSERT INTO users(name,email,password) VALUES(?,?,?)";

            PreparedStatement ps =
            con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int i = ps.executeUpdate();

            if(i > 0){

                out.println("<h2>Registration Successful</h2>");
                out.println("<a href='login.jsp'>Login Here</a>");

            } else {

                out.println("Registration Failed");
            }

        } catch(Exception e){

            e.printStackTrace();
        }
    }
}