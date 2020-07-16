package com.skycode.formocart_employee.navigation_menu.shop_management;

class CustomClass_Confirmed_Shop {
    private final String date_,shop_id,shop_name,contact_mobile,shop_area,status;

    public String getDate_() {
        return date_;
    }

    public String getShop_id() {
        return shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getContact_mobile() {
        return contact_mobile;
    }

    public String getShop_area() {
        return shop_area;
    }
    public String getStatus() {
        return status;
    }

    public CustomClass_Confirmed_Shop(String date_, String shop_id, String shop_name, String contact_mobile, String shop_area
    ,String status) {
        this.date_=date_;
        this.shop_id=shop_id;
        this.shop_name=shop_name;
        this.contact_mobile=contact_mobile;
        this.shop_area=shop_area;
        this.status=status;
    }
}
