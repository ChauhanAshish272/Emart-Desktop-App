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
public class UserPojo {
    private String userId;
    private String empId;
    private String password;
    private String userType;
    private String username;
    public UserPojo(){
        
    }

    public UserPojo(String userId, String empId, String password, String userType, String username) {
        this.userId = userId;
        this.empId = empId;
        this.password = password;
        this.userType = userType;
        this.username = username;
    }
    
    

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
