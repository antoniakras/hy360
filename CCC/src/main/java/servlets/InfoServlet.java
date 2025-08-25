package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.Info;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "InfoServlet", urlPatterns = {"/InfoServlet"})
public class InfoServlet extends HttpServlet {

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

        String username = new String();
        String date = request.getParameter("date");
        String usertype = request.getParameter("usertype");
        String companyname = new String();

        String result = new String();
        if (usertype.equals("client") || usertype.equals("company")) {
            if (usertype.equals("company")) {
                companyname = request.getParameter("companyname");
                System.out.println(companyname);
            }
            username = request.getParameter("username");
        } else {

            if (request.getParameterMap().containsKey("username")) {
                username = request.getParameter("username");
                System.out.println(username);

            }
        }

        Info obj = new Info();
        try {
            result = obj.get_info(usertype, date, username,companyname);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean success = false;

        System.out.println(date);
        System.out.println(usertype);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(result);
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
