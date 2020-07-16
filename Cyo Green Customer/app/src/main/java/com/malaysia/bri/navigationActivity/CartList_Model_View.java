package com.malaysia.bri.navigationActivity;

class CartList_Model_View {
    private final String shop_id,prodId,order_qty,total_amount,priceperqty;

    public String getShop_id() {
        return shop_id;
    }

    public String getProdId() {
        return prodId;
    }

    public String getOrder_qty() {
        return order_qty;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public String getPriceperqty() {
        return priceperqty;
    }

    public CartList_Model_View(String shop_id, String prodId, String order_qty, String total_amount, String priceperqty) {
        this.shop_id=shop_id;
        this.prodId=prodId;
        this.order_qty=order_qty;
        this.total_amount=total_amount;
        this.priceperqty=priceperqty;
    }
}
