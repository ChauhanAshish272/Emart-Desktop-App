/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eMart.DAO;

import eMart.DbUtil.DbConnection;
import eMart.pojo.ReceptionistPojo;
import eMart.pojo.UserPojo;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ashish
 */
public class ReceptionistDAO {
    public static Map<String,String> getNotRegisteredRecep()throws SQLException{
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select empid,empname from employees where job='Receptionist' and empid not in(select empid from users where usertype='Receptionist')");
        HashMap<String,String> recep=new HashMap<>();
        while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            recep.put(id, name);
        }
        return recep;
    }
    public static boolean addReceptionist(UserPojo user)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?,?,?)");
        ps.setString(1, user.getUserId());
        ps.setString(2, user.getEmpId());
        ps.setString(3, user.getPassword());
        ps.setString(4,user.getUserType());
        ps.setString(5,user.getUsername());
        int res=ps.executeUpdate();
        return res==1;
    }
    public static List<ReceptionistPojo> getAllRecep()throws SQLException{
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select users.empid,empname,userid,job,salary from users, employees where usertype='Receptionist'and users.empid=employees.empid");
        List<ReceptionistPojo> list=new ArrayList<>();
        while(rs.next()){
            ReceptionistPojo rp=new ReceptionistPojo();
            rp.setEmpId(rs.getString(1));
            rp.setEMpName(rs.getString(2));
            rp.setUserId(rs.getString(3));
            rp.setJob(rs.getString(4));
            rp.setSal(rs.getDouble(5));
            list.add(rp);
        }
        
        return list;
    }
    
    
    public static Map<String,String> getAllRecepId()throws SQLException{
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select userid,username from users where usertype='Receptionist'");
        HashMap<String,String> recep=new HashMap<>();
        while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            recep.put(id, name);
        }
        return recep;
    }
    
    public static boolean updatePassword(String userid,String pwd)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update users set password=? where userid=?");
        ps.setString(1, pwd);
        ps.setString(2, userid);
        int res=ps.executeUpdate();
        return res==1;
    }
    
    public static List<String> getRecepId()throws SQLException{
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select userid from users where usertype='Receptionist'");
        List<String> ls=new ArrayList<>();
        while(rs.next())
        {
            String id=rs.getString(1);
            ls.add(id);
        }
        return ls;
    }
    
    public static boolean DeleteRecep(String userId)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("delete from users where userid=?");
        ps.setString(1, userId);
        int res=ps.executeUpdate();
        return res==1;
    }
}
