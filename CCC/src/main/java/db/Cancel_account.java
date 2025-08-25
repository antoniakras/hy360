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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author antoniakrasoudaki
 */
public class Cancel_account {
    
    List<String> Name_List = new ArrayList<>();
    String Name = new String ();
    Connection con;
    
      public boolean cancel(String user_type, String user_name) throws SQLException, ClassNotFoundException {
        
        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("hy360");
        int port = 3306;
        String username = new String("root");
        String password = new String("") ;
    
        
        Class.forName("com.mysql.jdbc.Driver");

        
         con = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName+"?characterEncoding=UTF-8" , username, password);
        
  
        
         create_name_list(con,user_type);
         

         
         if (Name_List.contains(user_name)) {
            //if account exists check for debt.
            if(get_debt(con,user_type,user_name)!= 0){
            //debt is not 0 , account can't be deleted.               
                return false;
                
            }else{
            //delete account.
                
            Statement stmt = con.createStatement();

            String deleteAcc = new String(
                   "DELETE FROM  `"+user_type+"` WHERE Name='"+user_name+"'"

            );

            stmt.executeUpdate(deleteAcc);
            
            return true;
                        
            }
           
         }else{
             //if account doesn't exist exit.
            return false;
         }
        
    }
    
      public void create_name_list(Connection con, String user_type) throws SQLException {
                 
              String queryName = new String(
                "SELECT `Name` FROM `"+ user_type +"` WHERE 1"
        );
                   final JDialog dialog = new JDialog();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryName);

        while (rs.next()) {
            Name = rs.getString("Name");
            Name_List.add(Name);
        }
   
     } 
      
    public int get_debt(Connection con, String user_type, String user_name) throws SQLException {
        
        int debt=-1;
        
         String queryDebt = new String(
                "SELECT `Debt` FROM `"+user_type+"` WHERE Name= '"+user_name+"';"
        );
         
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryDebt);
        
        while(rs.next()){
            debt = rs.getInt("Debt");
        }     
         return debt;      
         
    }
  
    
}
