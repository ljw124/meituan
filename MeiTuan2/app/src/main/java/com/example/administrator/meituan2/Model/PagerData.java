package com.example.administrator.meituan2.Model;

/**
 * Created by Administrator on 2016/10/16.
 */
public class PagerData {

    public String name;
    public int iconRes;

    public PagerData(String name, int iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
}
