/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author antoniakrasoudaki
 */
public class PayDebt {
    Connection con;
    
    public int check_credit(Connection con, int debt,String user_type, String user_name) throws SQLException{
        
        ResultSet rs ,r;
        Statement stmt = con.createStatement();

        int credit=-1;
        int curr_debt=-1;
        
        String queryCredit = new String(
                "SELECT `Credit` FROM `"+user_type+"` WHERE Name= '"+user_name+"';"
        );
        
        
        rs = stmt.executeQuery(queryCredit);
        while(rs.next()){
            credit = rs.getInt("Credit");
        }
        
        if(debt!=0){
        return credit - debt;  
        
        }else{
               String queryDebt = new String(
                "SELECT `Debt` FROM `"+user_type+"` WHERE Name= '"+user_name+"';"
        );
        r=stmt.executeQuery(queryDebt);
        while(r.next()){
            curr_debt = r.getInt("Debt");
        }
 
        return credit - curr_debt; 
        }      
    } 
    
    public void update_credit(Connection con, String user_type, String user_name, int debt) throws SQLException{
        Statement stmt = con.createStatement();
        ResultSet rs;
         int old_debt = -1;
         int credit;
        
         //UPDATE Orders SET Quantity = Quantity + 1 WHERE
          String queryCredit = new String(
                " UPDATE `"+user_type+"` Set Credit = "+check_credit(con,debt,user_type,user_name)+" WHERE Name= '"+user_name+"';"
        );
          
     
          stmt.executeUpdate(queryCredit);
              
         String querygetData = new String(
                "SELECT `Debt` FROM `"+user_type+"` WHERE Name= '"+user_name+"';"
        );
         
          rs = stmt.executeQuery(querygetData);
           while(rs.next()){
            old_debt = rs.getInt("Debt");
        }
           
           if(debt==0){
                          String queryUpdate = new String(
                "UPDATE `"+user_type+"` Set Debt = '"+0+"' WHERE Name= '"+user_name+"';"
            );
                stmt.executeUpdate(queryUpdate);  
           }else{
               
                          String query_update = new String(
                "UPDATE `"+user_type+"` Set Debt = '"+(old_debt-debt)+"' WHERE Name= '"+user_name+"';"
            );
                stmt.executeUpdate(query_update);  
           
           }
                               
    }
    
    
    public boolean payment(int amount,String user_type,String user_name) throws SQLException, ClassNotFoundException{
        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("hy360");
        int port = 3306;
        String username = new String("root");
        String password = new String("") ;
    
        
         Class.forName("com.mysql.jdbc.Driver");

        
         con = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName+"?characterEncoding=UTF-8" , username, password);

        
                final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);    
JOptionPane.showMessageDialog(dialog, user_type +" "+user_name+" "+amount);
         
         
        if(amount!=0){
            
        if(check_credit(con,amount,user_type,user_name)>=0){
            
            update_credit(con,user_type,user_name,amount);
            
        }else{
         return false;
        }
             
        }else{
        //user pays all dept.
        //check if credit is enough.
            int debt=-1;
        
        if(check_credit(con,0,user_type,user_name)>=0){
            update_credit(con,user_type,user_name,0);       
            
        }else{
            return false;
        }
            
        }
        
        return true;  
    }
      
    
}
