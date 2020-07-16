<?php 
include 'config.php';
include 'header.php';
?>

<?php
//time st//
$timezone = "Asia/Calcutta";
if(function_exists('date_default_timezone_set')) date_default_timezone_set($timezone);
$curDate = date('Y-m-d H:i:s');
$curDateoff=date('m');
$month_num=$curDateoff;
$month_name=date('M', mktime(0, 0, 0, $month_num, 10));
$dap=date('d').'-'.$month_name.'-'.date('Y').' '.date('H:i');
//time ed//

extract($_POST);
//checking coupon is available or not for particular shop
$cpn_sel=mysqli_query($con,"select * from shop_coupons where shop_id='$shprid'");
$cpn_res=mysqli_fetch_assoc($cpn_sel);
$cpn_cnt=mysqli_num_rows($cpn_sel);
if($cpn_cnt >= 1){
    $cpnqry=mysqli_query($con,"update shop_coupons set sale_value='$saleval',discount='$discamnt',coupon_code='$cpncde',date='$dap',status='$cstatus' where shop_id='$shprid'");
    if($cpnqry){
        header('location:https://farmocart.com/admin');
    }
}
else{
    //selection of shop id
    $shp_sel=mysqli_query($con,"select * from shops where shop_id='$shprid'");
    $shp_selres=mysqli_fetch_assoc($shp_sel);
    $shopp_id=$shp_selres['shop_id'];
    $shopp_name=$shp_selres['shop_name'];
     $cpnqry=mysqli_query($con,"insert into shop_coupons(shop_id,shop_name,sale_value,discount,coupon_code,status,date) values('$shopp_id','$shopp_name','$saleval','$discamnt','$cpncde','$cstatus','$dap')");
        if($cpnqry){
            header('location:https://farmocart.com/admin?suBD=couponsSUB');
        }
}

    
?>