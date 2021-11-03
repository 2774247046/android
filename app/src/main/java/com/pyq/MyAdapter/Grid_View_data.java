package com.pyq.MyAdapter;

import java.io.Serializable;

public class Grid_View_data implements Serializable {
    private String ima;

    public Grid_View_data(String ima) {
        this.ima = ima;
    }

    public String getIma() {
        return ima;
    }

    public void setIma(String ima) {
        this.ima = ima;
    }
}
