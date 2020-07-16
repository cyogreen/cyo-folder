<?php 
include 'config.php';
?>

<?php 
$shp_deid=$_POST['shp_deid'];
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

$shpudqry=mysqli_query($con,"insert into del_shops(shop_id,shop_name,contact_mobile,alter_mobile,shop_email,address,shop_area,shop_pincode,shop_gst,shop_frntimg,shop_insimg,shop_ownimg,createdby_emp,date)
 select shop_id,shop_name,contact_mobile,alter_mobile,shop_email,address,shop_area,shop_pincode,shop_gst,shop_frntimg,shop_insimg,shop_ownimg,createdby_emp,date from shops where shop_id='$shp_deid'");
if($shpudqry){
    $shpdltqry=mysqli_query($con,"delete from shops where shop_id='$shp_deid'");
    if($shpdltqry){
    header('location:https://farmocart.com/admin?suBD=addshopsuc');    
    }
    
}

?>