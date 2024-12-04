package com.ivan.springboot.webapp.springboot_web.Controllers.dto;

public class UserDTO {
    private String title;
    private String name;
    private String lastname;

    public UserDTO() {}
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
