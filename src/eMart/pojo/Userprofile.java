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
public class Userprofile {
    private static String username;
    private static String usertype;
    private static String userId;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Userprofile.username = username;
    }

    public static String getUsertype() {
        return usertype;
    }

    public static void setUsertype(String usertype) {
        Userprofile.usertype = usertype;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        Userprofile.userId = userId;
    }
    
}
