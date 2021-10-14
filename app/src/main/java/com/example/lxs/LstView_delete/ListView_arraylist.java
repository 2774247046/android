package com.example.lxs.LstView_delete;

import android.graphics.Bitmap;

public class ListView_arraylist {
    private String text;
    private int image;

    public ListView_arraylist(String text, int image) {
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
