<?php
include '../config.php';
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

// selection of shop id st
$shid_qr=mysqli_query($con,"select * from shops order by id DESC limit 1");
$shid_res=mysqli_fetch_assoc($shid_qr);
$shp_prid=$shid_res['shop_id'];

$shp_id=explode('SHP',$shp_prid);
$shp_id2=$shp_id[1];
$shp_id3=$shp_id2+1;
$shp_id='SHP'.$shp_id3;

//image processing st
$data = json_decode(file_get_contents("php://input"));
$img1=$data->img1;

$coverImgdir = '/home/farmocart/public_html/admin/assets/shops/'.$shp_id.'/'.'imgs/';

define('UPLOAD_DIR', $coverImgdir);

//get extension type st
	$bin = base64_decode($img1);
    $size = getImageSizeFromString($bin);
    
    $ext = substr($size['mime'], 6);
	//get extension type ed
	$img1 = $img1;
	$img1 = str_replace('data:image/."'.$ext.'".;base64,', '', $img1);
	$img1 = str_replace(' ', '+', $img1);
	$data = base64_decode($img1);
	$file = UPLOAD_DIR . uniqid().'.'.$ext;
	$success = file_put_contents($file, $data);
	print $success ? $file : 'Unable to save the file.';
	
?>






