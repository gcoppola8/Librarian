package dev.coppola.librarian.services;

import dev.coppola.librarian.core.entity.User;

public class AuthenticationManager {
    private String user;
    private static AuthenticationManager instance = null;

    private AuthenticationManager(){}

    public static AuthenticationManager getInstance() {
        if(instance == null){
            instance = new AuthenticationManager();
        }
        return instance;
    }

    public void login(){
    	throw new UnsupportedOperationException("This method hasn't been implemented yet.");
    }

    public void logout(){
    	throw new UnsupportedOperationException("This method hasn't been implemented yet.");
    }

    public boolean isLoggedIn(){
        return false;
    }

    public User getLoggedUser(){
        return null;
    }

}
