/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eMart.pojo;

/**
 *
 * @author ashis
 */
public class ProductPojo {
    private String PId;

    public ProductPojo(){
        
    }
    public ProductPojo(String PId, String pName, String pCompany, double pPrice, double ourPrice, int tax, int quantity, double total) {
        this.PId = PId;
        this.pName = pName;
        this.pCompany = pCompany;
        this.pPrice = pPrice;
        this.ourPrice = ourPrice;
        this.tax = tax;
        this.quantity = quantity;
        this.total=total;
    }
    private String pName;
    private String pCompany;
    private double pPrice;
    private double ourPrice;
    private int tax;
    private int quantity;
    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpCompany() {
        return pCompany;
    }

    public void setpCompany(String pCompany) {
        this.pCompany = pCompany;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public double getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
