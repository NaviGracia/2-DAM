package com.ivan.springboot.webapp.springboot_web.models;

public class UserDTO {
    private String title;
    private User user;
    
    public UserDTO(String title, User user) {
        this.title = title;
        this.user = user;
    }
}
