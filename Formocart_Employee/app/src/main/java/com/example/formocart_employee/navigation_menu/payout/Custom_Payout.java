package com.skycode.formocart_employee.navigation_menu.payout;

class Custom_Payout {
    private final String date,order_id,commission_amnt,status,order_amount;

    public String getDate() {
        return date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getCommission_amnt() {
        return commission_amnt;
    }

    public String getStatus() {
        return status;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public Custom_Payout(String date, String order_id, String commission_amnt, String status, String order_amount) {
        this.date = date;
        this.order_id = order_id;
        this.commission_amnt = commission_amnt;
        this.status = status;
        this.order_amount = order_amount;
    }
}
