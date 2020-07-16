<?php
include '../config.php';
?>

<?php 
$data = json_decode(file_get_contents("php://input"));

$json_array=array("msg"=>"success");

/*$img_nameone=$data->imgone;
$img_nametwo=$data->imgtwo;
$img_namethr=$data->imgthr;*/


//time st//
$timezone = "Asia/Calcutta";
if(function_exists('date_default_timezone_set')) date_default_timezone_set($timezone);
$curDate = date('Y-m-d H:i:s');
$curDateoff=date('m');
$month_num=$curDateoff;
$month_name=date('M', mktime(0, 0, 0, $month_num, 10));
$dap=date('d').'-'.$month_name.'-'.date('Y').' '.date('H:i');
//time ed//

// selection of shop id st
$shid_qr=mysqli_query($con,"select * from shops order by id DESC limit 1");
$shid_res=mysqli_fetch_assoc($shid_qr);
$shp_prid=$shid_res['shop_id'];

$shp_id=explode('SHP',$shp_prid);
$shp_id2=$shp_id[1];
$shp_id3=$shp_id2+1;
$shp_id='SHP'.$shp_id3;

$shpnme=$data->shpnme;
$smobile=$data->smobile;
$amobile=$data->amobile;
$semail=$data->semail;
$saddrs=$data->saddrs;
$sarea=$data->sarea;
$spincde=$data->spincde;
$sgst=$data->sgst;
$dap=$data->dap;
$creby=$data->creby;

//insert data for shop
$sta='pending';
   $qry=mysqli_query($con,"insert into shops(shop_id,shop_name,contact_mobile,alter_mobile,shop_email,address,shop_area,shop_pincode,shop_gst,createdby_emp,status,date) 
              values('$shp_id','$shpnme','$smobile','$amobile','$semail','$saddrs','$sarea','$spincde','$sgst','$creby','$sta','$dap')");
              if($qry){
                  $rnd=rand(9999,1000);
                  $shp_psd=$shpnme[0].$shpnme[1].$shpnme[2].$rnd;
                  $sucrd=mysqli_query($con,"insert into cust_lgn(shop_id,email,psd,reg_date) values('$shp_id','$semail','$shp_psd','$dap')");
                  
                   if($sucrd){
                  // set response code - 503 service unavailable
                  /*http_response_code();*/
 
                  // tell the user
                  echo json_encode(array("message" => "Shop Added Successfully"));
                       
                       
                 }
              }
              else{
                  // set response code - 503 service unavailable
                  /*http_response_code(503);*/
 
                  // tell the user
                  echo json_encode(array("message" => "Something went wrong!"));
              }




?>


