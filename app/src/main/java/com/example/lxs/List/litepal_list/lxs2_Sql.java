package com.example.lxs.List.litepal_list;

import org.litepal.crud.LitePalSupport;

public class lxs2_Sql extends LitePalSupport {
    private  String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
