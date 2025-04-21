/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eMart.DbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author ashis
 */
public class DbConnection {
    private static Connection conn;
    static {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
         conn=DriverManager.getConnection("jdbc:oracle:thin:@//desktop-gnkro4b:1521/xe","grocery","grocery");
         JOptionPane.showMessageDialog(null, "Connection openend Successfully", "Successfull",JOptionPane.INFORMATION_MESSAGE);
            
        } catch ( ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error in Loading the Driver","Driver Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error in Establishing Connection","Database Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
        
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection(){
        try{
            conn.close();
            JOptionPane.showMessageDialog(null,"Connection Closed Successfully","Successfull",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error in Closing the Database Connection!!","Database Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
}
