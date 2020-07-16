package com.malaysia.bri.dashboard;

class CustomClass_HomeProduct {
    private final String prd_id,prd_name,min_qty,max_qty,sale_price;

    public String getPrd_id() {
        return prd_id;
    }

    public String getPrd_name() {
        return prd_name;
    }

    public String getMin_qty() {
        return min_qty;
    }

    public String getMax_qty() {
        return max_qty;
    }

    public String getSale_price() {
        return sale_price;
    }

    public CustomClass_HomeProduct(String prd_id, String prd_name, String min_qty, String max_qty, String sale_price) {
        this.prd_id=prd_id;
        this.prd_name=prd_name;
        this.min_qty=min_qty;
        this.max_qty=max_qty;
        this.sale_price=sale_price;
    }
}
