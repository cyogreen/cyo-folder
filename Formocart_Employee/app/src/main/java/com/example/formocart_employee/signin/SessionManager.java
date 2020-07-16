package com.skycode.formocart_employee.signin;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by BNV on 8/13/2017.
 */

public class SessionManager {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    SharedPreferences preferences1;
    SharedPreferences.Editor editor1;


    SharedPreferences preferences2;
    SharedPreferences.Editor editor2;


    SharedPreferences preferences3;
    SharedPreferences.Editor editor3;
    Context context;

    private static final String PREF_NAME = "loginPreference";
    private static final String PREF_NAME1 = "currLoc";
    private static final String PREF_NAME2 = "addAdress";
    private static final String PREF_NAME3 = "addcart";
    private static final String ID = "userId";
    private static final String CURRLOC = "curr";
    private static final String CARTNUMBER = "cartno";
    private static final String USER = "user";
    private static final String PASS = "pass";
    private static final String WALLET_AMOUNT = "wallet";
    private static final String FULLNAME = "fullname";
    private static final String MOBILE = "mobile";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";
    private static final String HOUSENO = "houseno";
    private static final String CITY_ = "city";
    private static final String PINCODE = "pincode";

    public SessionManager(Context context) {
        this.context=context;
        preferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = preferences.edit();

        preferences1 = context.getSharedPreferences(PREF_NAME1, MODE_PRIVATE);
        editor1 = preferences1.edit();


        preferences2 = context.getSharedPreferences(PREF_NAME2, MODE_PRIVATE);
        editor2 = preferences2.edit();

        preferences3 = context.getSharedPreferences(PREF_NAME3, MODE_PRIVATE);
        editor3 = preferences3.edit();

    }

    public void setLoginInfo(String userId) {
        editor.putString(ID, userId);
        editor.commit();
        editor.apply();
    }
    public String getID() {
        return preferences.getString(ID, null);
    }
    public String getCurrloc() {
        return preferences1.getString(CURRLOC, null);
    }


    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }

//    public void getWallet_details(String uid_wallet,String wallet_amount,String user, String pass) {
//        editor1.putString(ID_WALLET, uid_wallet);
//        editor1.putString(WALLET_AMOUNT,wallet_amount);
//        editor1.putString(USER,user);
//        editor1.putString(PASS,pass);
//        editor1.commit();
//        editor1.apply();
//    }

    public String getWalletAmount() {
        return preferences1.getString(WALLET_AMOUNT, null);
    }
    public String getUser() {
        return preferences1.getString(USER, null);
    }
    public String get() {
        return preferences1.getString(PASS, null);
    }
    public String getFullname() {
        return preferences2.getString(FULLNAME, null);
    }
    public String getMobile() {
        return preferences2.getString(MOBILE, null);
    }
    public String getAddress() {
        return preferences2.getString(ADDRESS, null);
    }
    public String getEmail() {
        return preferences2.getString(EMAIL, null);
    }
    public String getCartnumber() {
        return preferences3.getString(CARTNUMBER,null);
    }
    public String getHouseNo() {
        return preferences2.getString(HOUSENO,null);
    }
    public String getCity_() {
        return preferences2.getString(CITY_,null);
    }
    public String getPincode() {
        return preferences2.getString(PINCODE,null);
    }


    public void cleardetail() {
        editor2.clear();
        editor2.commit();
    }

    public void getCurLoc(String location) {
        editor1.putString(CURRLOC, location);
        editor1.commit();
        editor1.apply();
    }

    public void clearlocation() {
        editor1.clear();
        editor1.commit();
    }

    public void addDetail(String id, String fullname, String mobile, String email,String houseno,String city_,String pincode) {
        editor2.putString(ID, id);
        editor2.putString(FULLNAME,fullname);
        editor2.putString(MOBILE,mobile);
        editor2.putString(EMAIL,email);
        editor2.putString(HOUSENO,houseno);
        editor2.putString(CITY_,city_);
        editor2.putString(PINCODE,pincode);
        editor2.commit();
        editor2.apply();
    }

    public void setCartItem(String number) {
        editor3.putString(CARTNUMBER, number);
        editor3.apply();
        editor3.commit();
    }

    public void clearCart() {
        editor3.clear();
        editor3.commit();
    }

    public void setLoginInfoName(String emp_name) {
        editor1.putString(USER, emp_name);
        editor1.commit();
        editor1.apply();
    }

//    public void saveInvMessage(String invNo, String message,String custName) {
//        editor1.putString(INVNO, invNo);
//        editor1.putString(MESSAGE, message);
//        editor1.putString(CUSTOMER,custName);
//        editor1.apply();
//        editor1.commit();
//    }
//    public String getINVNO() {
//        return preferences1.getString(INVNO, null);
//    }
//    public String getMESSAGE() {
//        return preferences1.getString(MESSAGE, null);
//    }
//    public String getCustomer(){return preferences1.getString(CUSTOMER,null);}
//
//    public void clearDataInv() {
//        editor1.clear();
//        editor1.commit();
//    }
//
//    public void savePurMessage(String puNo, String message, String suppName) {
//        editor2.putString(INVNO, puNo);
//        editor2.putString(MESSAGE, message);
//        editor2.putString(CUSTOMER,suppName);
//        editor2.apply();
//        editor2.commit();
//    }
//    public String getPurno() {
//        return preferences1.getString(PURNO, null);
//    }
//    public String getMessagepur() {
//        return preferences1.getString(MESSAGEPUR, null);
//    }
//    public String getSupplier(){return preferences1.getString(SUPPLIER,null);}
//
//    public void clearDataPur() {
//        editor2.clear();
//        editor2.commit();
//    }
}