<?php 
include 'config.php';
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

//selection of product
$qtysel=mysqli_query($con,"select * from invt_mang where prd_id='$hprdid'");
$qtyress=mysqli_fetch_assoc($qtysel);
$qty_prdid=$qtyress['prd_id'];
$prv_qqty=$qtyress['avail_qty'];
$up_qty=$prv_qqty+$newqty;

$qtyupqr=mysqli_query($con,"update invt_mang set avail_qty='$up_qty',recup_qty='$newqty',inv_shrtg='$shrgqty',rect_up='$dap' where prd_id='$qty_prdid'");
if($qtyupqr){
     header('location:https://farmocart.com/admin?suBD=inventorySUB');
}
?>