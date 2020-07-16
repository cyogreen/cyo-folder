<?php 
include '../config.php';
?>

<?php 
//time st//
$timezone = "Asia/Calcutta";
if(function_exists('date_default_timezone_set')) date_default_timezone_set($timezone);
$curDate = date('Y-m-d H:i:s');
$curDateoff=date('m');
$month_num=$curDateoff;
$month_name=date('M', mktime(0, 0, 0, $month_num, 10));
$dap=date('d').'-'.$month_name.'-'.date('Y');
$daptim=date('H:i:s');
//time ed//

$data = json_decode(file_get_contents("php://input"));
$shop_id=$data->shop_id;
$shpnme=$data->shpnme;
$smobile=$data->smobile;
$amobile=$data->amobile;
$semail=$data->semail;
$saddrs=$data->saddrs;
$sarea=$data->sarea;
$spincde=$data->spincde;
$sgst=$data->sgst;
$creby=$data->creby;
$bnknme=$data->bnknme;
$achnme=$data->achnme;
$acno=$data->acno;
$ifsccde=$data->ifsccde;
$brncnme=$data->brncnme;

//update shops
$shpup=mysqli_query($con,"update shops set shop_name='$shpnme',contact_mobile='$smobile',alter_mobile='$amobile',shop_email='$semail',address='$saddrs',shop_area='$sarea',shop_pincode='$spincde',shop_gst='$sgst',bank_name='$bnknme',acchol_name='$achnme',accont_no='$acno',ifsc_code='$ifsccde',branch_name='$brncnme' where shop_id='$shop_id'");

if($shpup){
    http_response_code(201);
    echo json_encode(array("message" => "Your shop updated successfully"));
}
else{
    echo json_encode(array("message" => "Something went wrong"));
}
header('Content-Type: application/json');
?>