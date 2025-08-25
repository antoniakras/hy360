package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.PayDebt;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PayDebtServlet", urlPatterns = {"/PayDebtServlet"})
public class PayDebtServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String usertype = request.getParameter("usertype");
        int amount =0;
        PayDebt obj= new PayDebt ();

        /**
         * an exei pathsei to pay debt tote ta exei thn
         * parametro amount , alliws oxi
         * 
         */
        if (request.getParameterMap().containsKey("amount")) {
            amount = Integer.parseInt(request.getParameter("amount"));
            System.out.println(amount);
        }
        
         boolean success = false;
        
        try {
           success= obj.payment(amount, usertype, username);
        } catch (SQLException ex) {
            Logger.getLogger(PayDebtServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PayDebtServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

       

        System.out.println(username);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(success);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
    }

}
