/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author antoniakrasoudaki
 */
public class Purchase {
    Connection con;
    
    public void update_transactions(Connection con,String client_name,String retailer_name,int price,int id) throws SQLException{
          Statement stmt = con.createStatement();
           LocalDateTime today =  LocalDateTime.now();
           
         String addTran = new String(
                    "INSERT INTO `transactions`(`Client_name`, `Retailer_name`,`Date`, `Amount`,`ID` ,`Type`) VALUES ( '"+client_name+"' ,'"+retailer_name+"' ,'"+today+"','"+price+"','"+id+"','Purchase')"
            );

            stmt.executeUpdate(addTran);  

    }
    
        public boolean check_user(Connection con,String client) throws SQLException{
        ResultSet rs;
        Statement stmt = con.createStatement();
        
         String queryCheck = new String(
                "SELECT * FROM `client` WHERE NAME= '"+client+"';"
        );
        rs=stmt.executeQuery(queryCheck);
    
        if(rs.next()){

            return true;
        }
        else{
            
             return false;
        }
    }
        
    public boolean check_data(Connection con, String retailer_name,String company,int id) throws SQLException{
        ResultSet rs;
        Statement stmt = con.createStatement();
        
         String queryCheck = new String(
                "SELECT * FROM `products` WHERE Retailer_name= '"+retailer_name+"' AND Company= '"+company+"' AND ID= '"+id+"';"
        );
        rs=stmt.executeQuery(queryCheck);
    
        if(rs.next()){

            return true;
        }
        else{
            
             return false;
        }
        
    }
    
    public boolean check_credit(Connection con,String client_name,int price) throws SQLException{
        ResultSet rs;
        Statement stmt = con.createStatement();
        int credit=0;
        int lim=0;
        
         String queryCredit = new String(
                "SELECT `Credit` FROM `client` WHERE NAME= '"+client_name+"';"
        );
        rs=stmt.executeQuery(queryCredit);
        
        while(rs.next()){
            credit = rs.getInt("Credit");
        }
        
        String queryLim = new String(
                "SELECT `Lim` FROM `client` WHERE NAME= '"+client_name+"';"
        );
        rs=stmt.executeQuery(queryLim);
        
        while(rs.next()){
            lim = rs.getInt("Lim");
        }
        
        if( ((credit-price)>=0) && (lim>=price)){
            
             return true;
        }else{
             return false;
        }
        
    }
   
    public void update_client(Connection con,String client_name,int price) throws SQLException{
        ResultSet rs;
        int credit=0;
        int debt=0;
        int lim=0;
               String queryCredit = new String(
                "SELECT `Credit` FROM `client` WHERE Name= '"+client_name+"';"
        );
               
        
        Statement stmt = con.createStatement();
        
        rs = stmt.executeQuery(queryCredit);
        while(rs.next()){
            credit = rs.getInt("Credit");
        }
        
        //update credit 
        
        
         //UPDATE Orders SET Quantity = Quantity + 1 WHERE
          String Credit_update = new String(
                " UPDATE `client` Set Credit ='"+(credit-price)+"' WHERE Name= '"+client_name+"';"
        );
         
          stmt.executeUpdate(Credit_update);
         
        
        //update debt
              String queryDebt = new String(
                "SELECT `Debt` FROM `client` WHERE Name= '"+client_name+"';"
        );
       
        
        rs = stmt.executeQuery(queryDebt);
        while(rs.next()){
            debt = rs.getInt("Debt");
        }
        
          String Debt_update = new String(
                " UPDATE `client` Set Debt ='"+(debt+price)+"' WHERE Name= '"+client_name+"';"
        );
          
          stmt.executeUpdate(Debt_update);
          
        //update lim
        
           String queryLim = new String(
                "SELECT `Lim` FROM `client` WHERE NAME= '"+client_name+"';"
        );
        rs=stmt.executeQuery(queryLim);
        
        while(rs.next()){
            lim = rs.getInt("Lim");
        }
        
         String Lim_update = new String(
                " UPDATE `client` Set Lim ='"+(lim-price)+"' WHERE Name= '"+client_name+"';"
        );
          
          stmt.executeUpdate(Lim_update);
    
    }
    
    public void update_retailer(Connection con,String retailer_name,int price) throws SQLException{
        ResultSet rs;
        Statement stmt = con.createStatement();
        
        double earnings=0;
        double commission=0;
        double old_earnings=0;
        double debt=0;
        
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
                " UPDATE `retailer` Set Earnings = '"+(earnings+old_earnings)+"' WHERE Name= '"+retailer_name+"';"
        );
         
          stmt.executeUpdate(Earn_update);
        
        
        //update debt 
           String queryDebt = new String(
                "SELECT `Debt` FROM `retailer` WHERE Name= '"+retailer_name+"';"
        );
        
        
        rs = stmt.executeQuery(queryDebt);
        while(rs.next()){
            debt = rs.getInt("Debt");
        }
        
        commission=(earnings*0.05);
        
           String Debt_update = new String(
                " UPDATE `retailer` Set Debt = '"+(debt+commission)+"' WHERE Name= '"+retailer_name+"';"
        );
         
          stmt.executeUpdate(Debt_update);
            
    
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
                " UPDATE `company` Set Credit = '"+(profit+credit)+"' WHERE Name= '"+company+"';"
        );
         
          stmt.executeUpdate(Profit_update);

    
    }
    
    
    public boolean buy(String client_name,String retailer_name,String company,String product_id) throws ClassNotFoundException, SQLException{
        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("hy360");
        int port = 3306;
        String username = new String("root");
        String password = new String("") ;
    
        
         Class.forName("com.mysql.jdbc.Driver");

        
         con = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName+"?characterEncoding=UTF-8" , username, password);
        

        int price=0;
        int id=0;
        
        //get product price.
        ResultSet rs;
        Statement stmt = con.createStatement();
        
         String queryPrice = new String(
                "SELECT `Price` FROM `products` WHERE ID= '"+product_id+"';"
        );
        rs=stmt.executeQuery(queryPrice);
        
        while(rs.next()){
            price = rs.getInt("Price");
        }
        
         id = Integer.parseInt(product_id);
       
        
        if( ( !check_credit(con,client_name,price) ) || ( !check_user(con,client_name) ) || ( !check_data(con, retailer_name, company, id) ) ){

           return false;
       
        }else{

            update_transactions( con,client_name,retailer_name,price,id);
           //update client credit / debt
           update_client(con,client_name,price);
         
//           //update retailer credit/debt/earnings
           update_retailer(con,retailer_name,price);
           
//           //update company credit
          update_company(con,company,price);
                 
        }
    
       return true;
    }
}
