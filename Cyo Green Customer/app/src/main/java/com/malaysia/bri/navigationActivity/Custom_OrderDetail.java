package com.malaysia.bri.navigationActivity;

class Custom_OrderDetail {
    private final String prdarr,perarr,qtyarr;

    public Custom_OrderDetail(String prdarr, String perarr, String qtyarr) {
        this.prdarr=prdarr;
        this.perarr=perarr;
        this.qtyarr=qtyarr;
    }

    public String getPrdarr() {
        return prdarr;
    }

    public String getPerarr() {
        return perarr;
    }

    public String getQtyarr() {
        return qtyarr;
    }
}
