/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import db.Create_account;
import servlets.CreateServlet;
/**
 *
 * @author antoniakrasoudaki
 */
public class GetData {
     
     boolean create_result;
    
      public void Connect() throws SQLException, ClassNotFoundException {
        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("hy360");
        int port = 3306;
        String username = new String("root");
        String password = new String("") ;
    
        
        Class.forName("com.mysql.jdbc.Driver");

        
        Connection con = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName+"?characterEncoding=UTF-8" , username, password);
   
        Statement stmt = con.createStatement();           
        String addAcc = new String(
                    "INSERT INTO `client`(`Name`, `Account`, `Lim`, `Debt`, `Credit`) VALUES ( 'pena' ,'0','0','0','0')"
            );

            stmt.executeUpdate(addAcc);
//           
//       String u_name;
//           CreateServlet obj = new CreateServlet();
//          u_name=obj.get_u_name();
          
//                  Statement stmt = con.createStatement();           
//        String addAcc = new String(
//                    "INSERT INTO `client`(`Name`, `Account`, `Lim`, `Debt`, `Credit`) VALUES ( '"+user+"' ,'0','0','0','0')"
//            );
//
//            stmt.executeUpdate(addAcc);
            
            
//            create_result=Create_account.create(con,);
            
        //con.close;

     }

    
}
