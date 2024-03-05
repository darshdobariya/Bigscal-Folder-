package com.example.alltyperecycleview.multiview.demo;

public class MultiView {
    public static final int LayoutOne = 0;
    public static final int LayoutTwo = 1;
    private int viewType;
    private String text;

    public MultiView(int viewType, String text) {
        this.viewType = viewType;
        this.text = text;
    }

    public int getViewType() {
        return viewType;
    }

    public String getText() {
        return text;
    }

    private int icon;
    private String text_one, text_two;

    public MultiView(int viewType, int icon, String text_one, String text_two) {
        this.viewType = viewType;
        this.icon = icon;
        this.text_one = text_one;
        this.text_two = text_two;
    }

    public int getIcon() {
        return icon;
    }

    public String getText_one() {
        return text_one;
    }

    public String getText_two() {
        return text_two;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
