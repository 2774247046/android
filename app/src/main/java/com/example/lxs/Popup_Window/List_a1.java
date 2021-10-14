package com.example.lxs.Popup_Window;

import android.widget.ImageView;

public class List_a1 {
    private String text;
    private int image;

    public List_a1(String text, int image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
