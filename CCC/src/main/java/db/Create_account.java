/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.DriverManager;
import java.util.Arrays;


/**
 *
 * @author antoniakrasoudaki
 */
public class Create_account {
    
      List<String> Name_List = new ArrayList<>();
      List<String> Emp_List = new ArrayList<>();
      List<String> ID_List = new ArrayList<>();
      String Name = new String ();
      Connection con;
    
        public boolean create(String user_type , String user_name, int income,String emp_name , String emp_id) throws SQLException, ClassNotFoundException {
        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("hy360");
        int port = 3306;
        String username = new String("root");
        String password = new String("") ;
    
        
        Class.forName("com.mysql.jdbc.Driver");

        
         con = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName+"?characterEncoding=UTF-8" , username, password);

            
        create_name_list(con,user_type,user_name);
        
        if (Name_List.contains(user_name)) {
            //if account already exists exit.
            return false;
         }
        
        //generate expiration date 
        LocalDateTime today =  LocalDateTime.now();
        LocalDateTime expiration= today.plusYears(5); 
        //generate random account number
        Random rand = new Random();
        int ceil = 99999;
        int account = rand.nextInt(ceil);
        
        //generate limit and credit according to income.
        int credit;
        int limit;
        
        if(income <=7500){
            credit=1000;
            limit=400;
            
        }else if((income>7500) && (income<=1500) ){
            credit =1500;
            limit=500;
            
        }else{
            credit =2000;
            limit=1000;
        }
       
        Statement stmt = con.createStatement();  
 
                          
        switch (user_type){
        
            case "retailer":
                  
        String addEmp = new String(
                    "INSERT INTO `"+user_type+"`(`Name`, `Account`,`Date`, `Commission`, `Earnings`, `Debt`) VALUES ( '"+user_name+"' ,'"+account+"' ,'"+expiration+"','5','0','0')"
            );

            stmt.executeUpdate(addEmp);  
                
            case "client":
            
         
                String addClient = new String(
                    "INSERT INTO `"+user_type+"`(`Name`, `Account`, `Date`, `Lim`, `Debt`, `Credit`) VALUES ( '"+user_name+"' ,'"+account+"','"+expiration+"','"+limit+"','0','"+credit+"')"
            );

            stmt.executeUpdate(addClient);   
            
            case "company":
           insert_employees(emp_name,emp_id);
           for (int i = 0; i < Emp_List.size(); i++) {
                   String addComp = new String(
                    "INSERT INTO `"+user_type+"`(`Name`, `Account`, `Date`, `lim`, `Debt`, `Credit`,`Employee_name`,`Employee_id`) VALUES ( '"+user_name+"' ,'"+account+"','"+expiration+"','"+limit+"','0','"+credit+"','"+Emp_List.get(i)+"','"+ID_List.get(i)+"')"
            );

            stmt.executeUpdate(addComp);    
                
                
            }
                    
        }
             
        con.close();
        return true;
           }
            
     public void create_name_list(Connection con, String user_type , String user_name) throws SQLException {
                 
              String queryName = new String(
                "SELECT `Name` FROM `"+user_type+"` WHERE 1"
        );

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryName);

        while (rs.next()) {
            Name = rs.getString("Name");
            Name_List.add(Name);
        }
   
     }
     
     public void insert_employees (String emp_names, String emp_id){      
         Emp_List = Arrays.asList(emp_names.split(","));
         ID_List = Arrays.asList(emp_id.split(","));       
     }
        
}
