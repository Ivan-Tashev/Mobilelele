package com.example.mobilelele.security;

import com.example.mobilelele.model.entity.enums.Role;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CurrentUser {
    private String name = "Anonymous";
    private boolean isAnonymous = true;
    private List<Role> userRoleList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        if (anonymous) {
            this.name = "Anonymous";
            this.userRoleList.clear(); // clear the Roles list
        }
        isAnonymous = anonymous;
    }

    public List<Role> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<Role> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public boolean isAdmin(){
        return this.userRoleList.contains(Role.ADMIN);
    }
}
