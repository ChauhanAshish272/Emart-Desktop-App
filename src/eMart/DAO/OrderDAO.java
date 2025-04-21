/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eMart.DAO;

import eMart.DbUtil.DbConnection;
import eMart.pojo.OrderPojo;
import eMart.pojo.ProductPojo;
import eMart.pojo.Userprofile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO {
        public static String getNextOrderId()throws SQLException{
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(order_id) from Orders");
        rs.next();
        String oId=rs.getString(1);
        if(oId==null)
            return "O-101";
        int ono=Integer.parseInt(oId.substring(2));
        ono=ono+1;
        return "O-"+ono;
    }
        
    public static boolean addOrder(List<ProductPojo> pl,String ordId)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into orders values(?,?,?,?,?)");
        int cnt=0;
        for(ProductPojo p:pl)
        {
            ps.setString(1, ordId);
            ps.setString(2, p.getPId());
            ps.setInt(3, p.getQuantity());
            ps.setString(4, Userprofile.getUserId());
            ps.setDouble(5, p.getTotal());
            cnt=(int) (cnt+ps.executeUpdate());
        }
        return cnt==pl.size();
    }
    
    public static List<ProductPojo> viewAllOrders(String oId)throws SQLException{
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select p.*,o.total,o.quantity  from products p join orders o on p.p_id=o.p_id where o.order_id=?");
        ps.setString(1, oId);
        ResultSet rs = ps.executeQuery();
        List<ProductPojo> al = new ArrayList<>();
        ProductPojo order;
        while(rs.next()){
            order=new ProductPojo();
            order.setPId(rs.getString(1));
            order.setpName(rs.getString(2));
            order.setpCompany(rs.getString(3));
            order.setpPrice(rs.getInt(4));
            order.setOurPrice(rs.getInt(5));
            order.setTax(rs.getInt(6));
            order.setQuantity(rs.getInt(10));
            order.setTotal(rs.getDouble(9));
            al.add(order);
        }
        return al;
    }
    public static List<ProductPojo> viewUserOrders(String userId)throws SQLException{
        Connection conn = DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select p.*,o.total,o.quantity  from products p join orders o on p.p_id=o.p_id where o.order_id=(select max(order_id) from orders)");
        List<ProductPojo> al = new ArrayList<>();
        ProductPojo order;
        while(rs.next()){
            order=new ProductPojo();
            order.setPId(rs.getString(1));
            order.setpName(rs.getString(2));
            order.setpCompany(rs.getString(3));
            order.setpPrice(rs.getInt(4));
            order.setOurPrice(rs.getInt(5));
            order.setTax(rs.getInt(6));
            order.setQuantity(rs.getInt(10));
            order.setTotal(rs.getDouble(9));
            al.add(order);
        }
        return al;  
    }
    public static List<String> getOrdersId()throws SQLException{
        Connection conn  = DbConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select order_id from orders");
        List<String> ls=new ArrayList<>();
        while(rs.next())
        {
            if(!ls.contains(rs.getString(1)))
                ls.add(rs.getString(1));
        }
        return ls;
    }
}
