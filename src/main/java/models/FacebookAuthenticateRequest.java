/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author john.ralston
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookAuthenticateRequest {
    
    private String email;
    private String token;

    public FacebookAuthenticateRequest() {
    }

    public FacebookAuthenticateRequest(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
