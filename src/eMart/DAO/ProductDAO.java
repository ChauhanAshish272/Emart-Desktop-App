/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eMart.DAO;

import eMart.DbUtil.DbConnection;
import eMart.pojo.ProductPojo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ashis
 */
public class ProductDAO {
    public static String getNextPId()throws SQLException{
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(p_id) from products");
        rs.next();
        String pId=rs.getString(1);
        if(pId==null)
            return "P101";
        int pno=Integer.parseInt(pId.substring(1));
        pno=pno+1;
        return "P"+pno;
    }  
    
    public static boolean addProduct(ProductPojo p)throws SQLException{
     Connection conn=DbConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("insert into products values(?,?,?,?,?,?,?,'Y')");
     ps.setString(1, p.getPId());
     ps.setString(2, p.getpName());
     ps.setString(3, p.getpCompany());
     ps.setDouble(4, p.getpPrice());
     ps.setDouble(5, p.getOurPrice());
     ps.setInt(6, p.getTax());
     ps.setInt(7, p.getQuantity());
     return ps.executeUpdate()==1;
    }
    
    public static List<ProductPojo> getProductDetails()throws SQLException{
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from Products where status='Y' order by p_id");
        List<ProductPojo> productList=new ArrayList<>();
        while(rs.next())
        {
            ProductPojo p=new ProductPojo();
            p.setPId(rs.getString(1));
            p.setpName(rs.getString(2));
            p.setpCompany(rs.getString(3));
            p.setpPrice(rs.getDouble(4));
            p.setOurPrice(rs.getDouble(5));
            p.setTax(rs.getInt(6));
            p.setQuantity(rs.getInt(7));
            productList.add(p);
        }
        return productList;
    }
    
    public static boolean deleteProduct(String p_id)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update products set status='N' where p_id=?");
        ps.setString(1, p_id);
        return ps.executeUpdate()==1;
    }
    
    public static boolean updateProduct(ProductPojo p)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update products set p_name=?,p_companyname=?,p_price=?,our_price=?,p_tax=?,quantity=? where p_id=?");
        ps.setString(1, p.getpName());
        ps.setString(2, p.getpCompany());
        ps.setDouble(3, p.getpPrice());
        ps.setDouble(4, p.getOurPrice());
        ps.setInt(5, p.getTax());
        ps.setInt(6, p.getQuantity());
        ps.setString(7, p.getPId());
        return ps.executeUpdate()==1;
    }
    public static ProductPojo getProductDetails(String pId)throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from products where p_id=? and status='Y'");
        ps.setString(1, pId);
        ResultSet rs=ps.executeQuery();
        ProductPojo p=new ProductPojo();
        if(rs.next())
        {
            p.setPId(rs.getString(1));
            p.setpName(rs.getString(2));
            p.setpCompany(rs.getString(3));
            p.setpPrice(rs.getDouble(4));
            p.setOurPrice(rs.getDouble(5));
            p.setTax(rs.getInt(6));
            p.setQuantity(rs.getInt(7));
        }
        return p;
    }
    public static boolean updateStocks(List<ProductPojo> pl)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update products set quantity=quantity-? where p_id=?");
        int x=0;
        for(ProductPojo p:pl)
        {
            ps.setInt(1, p.getQuantity());
            ps.setString(2, p.getPId());
            ps.executeUpdate();
            int rows=ps.executeUpdate();
            if(rows!=0)
                x++;
        }
        return x==pl.size();
    }
}
