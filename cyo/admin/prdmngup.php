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

$prmngupqr=mysqli_query($con,"update products set min_qty='$minqty',max_qty='$maxqty',sale_price='$slepric',offer_price='$offrpric',prd_status='$status' where prd_id='$upprid'");
if($prmngupqr){
    $hom_prd=mysqli_query($con,"update home_prds set min_qty='$minqty',max_qty='$maxqty',sale_price='$slepric',offer_price='$offrpric',prd_status='$status' where prd_id='$upprid'");
    if($hom_prd){
        header('location:https://farmocart.com/admin?suBD=addprdsuc');
    }
    
}
?>