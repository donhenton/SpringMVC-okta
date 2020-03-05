 

package com.dhenton9000.spring.mvc.controllers;

import java.io.Serializable;

 
public class LoginForm implements Serializable {
    private String userEmail;

    @Override
    public String toString() {
        return "LoginForm{" + "userEmail=" + userEmail + ", password=*************}";
    }
    private String password;

    

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
