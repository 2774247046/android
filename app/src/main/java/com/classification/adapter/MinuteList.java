package com.classification.adapter;

public class MinuteList {
    private String text;
    private String URl;

    public MinuteList(String text, String URl) {
        this.text = text;
        this.URl = URl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getURl() {
        return URl;
    }

    public void setURl(String URl) {
        this.URl = URl;
    }
}
