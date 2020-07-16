package com.skycode.formocart_employee.signin;

public class CustomClassImage {
    public String prd_id,prd_img,pro_name;

    public String getPrd_id() {
        return prd_id;
    }

    public String getPrd_img() {
        return prd_img;
    }
    public String getPro_name() {
        return pro_name;
    }


    public CustomClassImage(String prd_id, String prd_img, String pro_name) {
        this.prd_id=prd_id;
        this.prd_img=prd_img;
        this.pro_name=pro_name;
    }
}
