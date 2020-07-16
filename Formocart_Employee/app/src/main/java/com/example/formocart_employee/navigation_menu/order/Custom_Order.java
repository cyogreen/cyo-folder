package com.skycode.formocart_employee.navigation_menu.order;

class Custom_Order {
    private final String prd_id,order_id,amount,status,order_qty,orders_amnt;

    public String getPrd_id() {
        return prd_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getOrder_qty() {
        return order_qty;
    }

    public String getOrders_amnt() {
        return orders_amnt;
    }

    public Custom_Order(String prd_id, String order_id, String amount, String status, String order_qty, String orders_amnt) {
        this.prd_id=prd_id;
        this.order_id=order_id;
        this.amount=amount;
        this.status=status;
        this.order_qty=order_qty;
        this.orders_amnt=orders_amnt;
    }
}
