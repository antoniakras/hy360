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
public class Return {
    Connection con;
    
    public boolean check_transaction(Connection con,String client, int id ) throws SQLException{
        
        ResultSet rs;
        Statement stmt = con.createStatement();
        
         String queryCheck = new String(
                "SELECT * FROM `transactions` WHERE CLIENT_NAME= '"+client+"' AND ID= '"+id+"' AND TYPE= 'Purchase' ;"
        );
        rs=stmt.executeQuery(queryCheck);
    
        if(rs.next()){

            return true;
        }
        else{
            
             return false;
        }
    
    }
    
    
    public void update_transaction(Connection con,String client_name , String retailer_name,int price,int id) throws SQLException{
       Statement stmt = con.createStatement();
       LocalDateTime today =  LocalDateTime.now();
       
       //Delete old purchase

            String deleteTran = new String(
                   "DELETE FROM  `transactions` WHERE CLIENT_NAME='"+client_name+"' AND ID= '"+id+"' AND TYPE= 'Purchase' "

            );

            stmt.executeUpdate(deleteTran);
       
       //Update new return transaction
           
         String addTran = new String(
                    "INSERT INTO `transactions`(`Client_name`, `Retailer_name`,`Date`, `Amount`,`ID` ,`Type`) VALUES ( '"+client_name+"' ,'"+retailer_name+"' ,'"+today+"','"+price+"','"+id+"','Return')"
            );

            stmt.executeUpdate(addTran);  
    
    }
    
    public void update_client(Connection con,String client_name,int price) throws SQLException{
        Statement stmt = con.createStatement();
        ResultSet rs;
        int credit=0;
        
               String queryCredit = new String(
                "SELECT `Credit` FROM `client` WHERE Name= '"+client_name+"';"
        );
                   
        rs = stmt.executeQuery(queryCredit);
        while(rs.next()){
            credit = rs.getInt("Credit");
        }
        
        //update credit 
        
          String Credit_update = new String(
                " UPDATE `client` Set Credit ='"+(credit+price)+"' WHERE Name= '"+client_name+"';"
        );
         
          stmt.executeUpdate(Credit_update);
    
    }
    
    public void update_retailer(Connection con,String retailer_name,int price) throws SQLException{
         ResultSet rs;
        Statement stmt = con.createStatement();
        
        double earnings=0;
        double old_earnings=0;
        
        earnings =(price * 0.3);
        //get old earnings
                    String queryOldEarn = new String(
                "SELECT `Earnings` FROM `retailer` WHERE Name= '"+retailer_name+"';"
        );
        
        
        rs = stmt.executeQuery(queryOldEarn);
        while(rs.next()){
            old_earnings = rs.getInt("Earnings");
        }
        
        //update earnings 
          String Earn_update = new String(
                " UPDATE `retailer` Set Earnings = '"+(old_earnings-earnings)+"' WHERE Name= '"+retailer_name+"';"
        );
         
          stmt.executeUpdate(Earn_update);
        
    }
    
    public void update_company(Connection con,String company,int price) throws SQLException{
        
             ResultSet rs;
        Statement stmt = con.createStatement();
        double credit=0;
        double profit=0;
        
                   String queryCredit = new String(
                "SELECT `Credit` FROM `company` WHERE Name= '"+company+"';"
        );
        
        
        rs = stmt.executeQuery(queryCredit);
        while(rs.next()){
            credit = rs.getInt("Credit");
        }
        
        profit= (price*0.7);
        
         String Profit_update = new String(
                " UPDATE `company` Set Credit = '"+(credit-profit)+"' WHERE Name= '"+company+"';"
        );
         
          stmt.executeUpdate(Profit_update);
    
    }
    
    public boolean make_return(String client, String retailer, String company, String product_id) throws ClassNotFoundException, SQLException{
        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("hy360");
        int port = 3306;
        String username = new String("root");
        String password = new String("") ;
    
        
         Class.forName("com.mysql.jdbc.Driver");

        
         con = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName+"?characterEncoding=UTF-8" , username, password);
        
        //search transaction table to confirm purchase
        int id =  Integer.parseInt(product_id);
        
        
        if(check_transaction(con,client,id )){
            
            
            //get product price
        ResultSet rs;
        Statement stmt = con.createStatement();
        int price=0;
        
         String queryPrice = new String(
                "SELECT `Price` FROM `products` WHERE ID= '"+product_id+"';"
        );
        rs=stmt.executeQuery(queryPrice);
        
        while(rs.next()){
            price = rs.getInt("Price");
        }
            
        update_transaction(con,client,retailer,price,id);
            
        update_client(con,client,price);
        
        update_retailer( con, retailer, price);
        
        update_company(con,company,price);
        
        
        }else{
            
            //purchase transaction doesn't exist.
            return false;
        }
              
        return true;
    }
    
}
