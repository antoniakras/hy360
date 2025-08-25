/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author antoniakrasoudaki
 */
public class Info {
    
    Connection con;
    boolean found;
    
    public String trans(Connection con,String user_type, String user_name ,String Date ) throws SQLException{
      StringBuilder sb = new 
                      StringBuilder();
      LocalDateTime tmpDate;
      float amount;
      int id;
              ResultSet rs;
        Statement stmt = con.createStatement();
        
             final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);    
JOptionPane.showMessageDialog(dialog, user_type +" "+user_name +" "+Date);
        
        if(user_type.equals("client")){
            //get all client transactions
            
           String queryCheck = new String(
                "SELECT * FROM `transactions` WHERE DATE= '"+Date+"' AND Client_name= '"+user_name+"';"
        );
        rs=stmt.executeQuery(queryCheck);
        

        while (rs.next()) {
            
         sb.append(rs.getString("Client_name"));
         sb.append(" ");
          sb.append(rs.getString("Retailer_name"));
          sb.append(" ");
          sb.append(rs.getString("Date"));
          sb.append(" ");
         amount = rs.getFloat("Amount");
         sb.append(String.valueOf(amount));
          sb.append(" ");
         id= rs.getInt("ID");
          sb.append(String.valueOf(id)); 
          sb.append(" ");
         sb.append(rs.getString("Type"));
          sb.append( "\n");
         
        }
        
        
        }else{
            //get all retailer transactions
                
           String queryCheck = new String(
                "SELECT * FROM `transactions` WHERE DATE= '"+Date+"' AND Retailer_name= '"+user_name+"';"
        );
        rs=stmt.executeQuery(queryCheck);
        

        while (rs.next()) {
            
         sb.append(rs.getString("Client_name"));
         sb.append(" ");
          sb.append(rs.getString("Retailer_name"));
          sb.append(" ");
          sb.append(rs.getString("Date"));
          sb.append(" ");
         amount = rs.getFloat("Amount");
         sb.append(String.valueOf(amount));
          sb.append(" ");
         id= rs.getInt("ID");
          sb.append(String.valueOf(id)); 
          sb.append(" ");
         sb.append(rs.getString("Type"));
          sb.append( "\n");
         
        }
        }
        
      String Result = sb.toString();
    
 
      return Result;
   
    }
    
     public String company_trans (Connection con,String user_name,String Date ) throws SQLException{
         StringBuilder sb = new 
                      StringBuilder();
      float amount;
      int id;
              ResultSet rs;
        Statement stmt = con.createStatement();
        
             final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);    
JOptionPane.showMessageDialog(dialog," "+user_name +" "+Date);
        
           String queryCheck = new String(
 //  "SELECT * FROM `transactions` WHERE DATE= '"+Date+"' AND Company= '"+company+"';"
        );
        rs=stmt.executeQuery(queryCheck);
        

        while (rs.next()) {
            
         sb.append(rs.getString("Client_name"));
         sb.append(" ");
          sb.append(rs.getString("Retailer_name"));
          sb.append(" ");
          sb.append(rs.getString("Date"));
          sb.append(" ");
         amount = rs.getFloat("Amount");
         sb.append(String.valueOf(amount));
          sb.append(" ");
         id= rs.getInt("ID");
          sb.append(String.valueOf(id)); 
          sb.append(" ");
         sb.append(rs.getString("Type"));
          sb.append( "\n");
         
        }
        
              
      String Result = sb.toString();

      return Result;
   
    }
    
    public String get_info(String user_type, String date , String user_name,String company) throws ClassNotFoundException, SQLException{
        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("hy360");
        int port = 3306;
        String username = new String("root");
        String password = new String("") ;
        String result = new String ();
             
         Class.forName("com.mysql.jdbc.Driver");

        
         con = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName+"?characterEncoding=UTF-8" , username, password);
        
        
         if(user_type.equals("client") || user_type.equals("retailer")){
             result=trans(con,user_type,user_name,date);
             
         } else {
         }
        
   
    return result;
    }
    
}
