/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Shivangi Yadav
 */
public class ConnectionClass {
    //Creating the connection 
     private String url = "jdbc:mysql://localhost:3306/login"; 
     private String user = "root"; 
     private String pass = "pwdpwd"; 
     private Connection con=null; 
     private Statement st;
        //Entering the data 
     
     public ConnectionClass(){
         createConnection();
     }
     
    private void createConnection(){
        
        try
        { 
            DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
          
            //Reference to connection interface 
            con = (Connection) DriverManager.getConnection(url,user,pass); 
  
            st = con.createStatement(); 
            
        } 
        catch(Exception ex) 
        { 
            System.err.println(ex); 
        } 
    }
    public boolean register(String username, String email, String contact,String password) throws SQLException{
        

        String query = "INSERT INTO profile (username, email, contact) VALUES ('"+username+"', '"+email+"', '"+contact+"')";
        String query1 = "INSERT INTO login1 (username, password) VALUES ('"+username+"', '"+password+"')";
        System.out.println(query);
        System.out.println(query1);
        boolean isRegister=st.execute(query);
        boolean isRegister1=st.execute(query1);
        System.out.println(isRegister);
        System.out.println(isRegister1);
        
        if(isRegister && isRegister1){
            return true;
        }
        else{
        return false;
        }
    }
    
    public boolean auth(String username, String password) throws SQLException{
        String query = "Select * from login1 WHERE username like '"+username+"'";
        System.out.println(query);
        ResultSet r = st.executeQuery(query);
        boolean isAuth=false;
        String authPass=null;
        while(r.next()){
            authPass = r.getString("password");
            System.out.println(authPass);
        }
        System.out.println(authPass+" : "+password);
        if(authPass.equals(password)){
            isAuth=true;
        }
        //Get password
        //If password matches return true
        //else return false
        return isAuth;
    }
    
}
