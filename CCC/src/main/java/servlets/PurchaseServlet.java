package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.Purchase;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author t's'z
 */
@WebServlet(name = "PurchaseServlet", urlPatterns = {"/PurchaseServlet"})
public class PurchaseServlet extends HttpServlet {

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

        String productid = request.getParameter("productid");
        String retailer = request.getParameter("retailer");
        String company = request.getParameter("company");
        String usertype = request.getParameter("usertype");
        String username = request.getParameter("username");

        boolean success = false;

        System.out.println(username);
        System.out.println(productid);
        System.out.println(retailer);
        System.out.println(company);
        Purchase obj = new Purchase();
        try {
            success = obj.buy(username, retailer, company, productid);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PurchaseServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

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
