/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eMart.DAO;
import eMart.DbUtil.DbConnection;
import eMart.pojo.EmployeePojo;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ashis
 */
public class EmployeesDAO {
    public static String getNextEmpId()throws SQLException{
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(empid) from employees");
        rs.next();
        String empId=rs.getString(1);
        int entno=Integer.parseInt(empId.substring(1));
        entno=entno+1;
        return "E"+entno;
    }
    
    public static boolean addEmployee(EmployeePojo emp)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into employees values(?,?,?,?)");
        ps.setString(1, emp.getEmpId());
        ps.setString(2, emp.getEmpName());
        ps.setString(3, emp.getJob());
        ps.setDouble(4, emp.getSalary());
        int res=ps.executeUpdate();
        return res==1;
    }
    public static List<EmployeePojo> getAllEmployees()throws SQLException{
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select * from employees order by empid");
        List<EmployeePojo>empl=new ArrayList<>();
        while(rs.next())
        {
            EmployeePojo emp=new EmployeePojo();
            emp.setEmpId(rs.getString(1));
            emp.setEmpName(rs.getString(2));
            emp.setJob(rs.getString(3));
            emp.setSalary(rs.getDouble(4));
            empl.add(emp);
        }
        return empl;
    }
    public static boolean UpdateEmployee(EmployeePojo emp)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update employees set empname=?,job=?,salary=? where empid=?");
        ps.setString(1,emp.getEmpName());
        ps.setString(2, emp.getJob());
        ps.setDouble(3, emp.getSalary());
        ps.setString(4, emp.getEmpId());
        int res=ps.executeUpdate();
        if(res==0)
            return false;
        else{
            boolean result=userDAO.isUserPresent(emp.getEmpId());
            if(result==false)
            {
                return true;
            }
        }
        ps=conn.prepareStatement("update users set username=?,usertype=? where empid=?");
        ps.setString(1,emp.getEmpName());
        ps.setString(2, emp.getJob());
        ps.setString(3, emp.getEmpId());
        int y=ps.executeUpdate();
        return y==1;
    }
    public static List<String> getIds()throws SQLException{
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select empid from employees order by empid");
        List<String>ids=new ArrayList<>();
        while(rs.next())
        {
            ids.add(rs.getString(1));
        }
        return ids;
    }
    public static EmployeePojo getDetails(String empId)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select empname,job,salary from employees where empid=?");
        ps.setString(1,empId);
        ResultSet rs=ps.executeQuery();
        EmployeePojo e=null;
        if(rs.next())
        {
            e=new EmployeePojo();
            e.setEmpName(rs.getString(1));
            e.setJob(rs.getString(2));
            e.setSalary(rs.getDouble(3));
        }
        return e;
    }
    public static boolean DeleteEmployee(String empId)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("delete from employees where empid=?");
        ps.setString(1, empId);
        int res=ps.executeUpdate();
        return res==1;
    }
}
