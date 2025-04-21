/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eMart.DAO;

import eMart.DbUtil.DbConnection;
import eMart.pojo.UserPojo;
import eMart.pojo.Userprofile;
import java.sql.*;
/**
 *
 * @author ashis
 */
public class userDAO {
    public static boolean validateUser(UserPojo user)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select * from users where userid=? and password=? and usertype=?");
        ps.setString(1, user.getUserId());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            String username=rs.getString(5);
            Userprofile.setUsername(username);
            return true;
        }
        return false;
    }
    public static boolean isUserPresent(String empid)throws SQLException{
        
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select 1 from users where empid=?");
        ps.setString(1, empid);
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }
}
