package com.malaysia.bri.modelClass;

public class CustomClass_CategoryName {
    private final String cat_id,cat_name,cat_img;

    public String getCat_id() {
        return cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public String getCat_img() {
        return cat_img;
    }

    public CustomClass_CategoryName(String cat_id, String cat_name, String cat_img) {
        this.cat_id=cat_id;
        this.cat_name=cat_name;
        this.cat_img=cat_img;
    }
}
