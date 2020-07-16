<?php 
include '../config.php';

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

$ordr_tim=date('d').date('m').date('Y').date('H').date('i').date('s');

$data = json_decode(file_get_contents("php://input"));
$shop_id=$data->shop_id;
$prd_id=$data->prd_id;
$order_qty=$data->order_qty;
$order_amnt=$data->order_amnt;
$tot_amnt=(($order_qty)*($order_amnt));

//checking available stock
$stck=mysqli_query($con,"select * from invt_mang where prd_id='$prd_id'");
$stckres=mysqli_fetch_assoc($stck);
$availqty=$stckres['avail_qty'];
$shrtg_qty=$stckres['inv_shrtg'];
$tot_check=($availqty - $shrtg_qty);

if($order_qty <= $tot_check){
    



$qry=mysqli_query($con,"select * from mycart where shop_id='$shop_id' && prd_id='$prd_id'");
$qryres=mysqli_fetch_assoc($qry);
$qrycnt=mysqli_num_rows($qry);

if($qrycnt > 0){
    $qryupd=mysqli_query($con,"update mycart set order_qty='$order_qty',total_amount='$tot_amnt',date='$dap',time='$daptim' where shop_id='$shop_id' && prd_id='$prd_id'");
    if($qryupd){
        // set response code - 201 created
        http_response_code(201);
        echo json_encode(array("message" => "Item Updated successfully"));
    }
    else{
        // set response code - 503 service unavailable
        http_response_code(503);
        echo json_encode(array("message" => "Something went wrong"));
    }
    
}
else{
    $ordr_uid=$shop_id.$ordr_tim;
    $sel2=mysqli_query($con,"select * from mycart where shop_id='$shop_id'");
    $selres=mysqli_fetch_assoc($sel2);
    $selprd=$selres['prd_id'];
    
    $selnum=mysqli_num_rows($sel2);
    
    if($selnum > 0){
        if($selprd != $prd_id){
        $qryins=mysqli_query($con,"insert into mycart(shop_id,prd_id,order_qty,priceperqty,total_amount,date,time) values('$shop_id','$prd_id','$order_qty','$order_amnt','$tot_amnt','$dap','$daptim')");
        // set response code - 201 created
        http_response_code(201);
        echo json_encode(array("message" => "Item added successfully"));
        }
    }
    else{
        $qryins=mysqli_query($con,"insert into mycart(shop_id,prd_id,order_qty,priceperqty,total_amount,date,time) values('$shop_id','$prd_id','$order_qty','$order_amnt','$tot_amnt','$dap','$daptim')");
        if($qryins){
        // set response code - 201 created
        http_response_code(201);
        echo json_encode(array("message" => "Item added successfully"));
        }
        else{
        // set response code - 503 service unavailable
        http_response_code(503);
        echo json_encode(array("message" => "Something went wrong")); 
        }
    }
    
}
}
else{
    echo json_encode(array("message" => "Currently Available Stock is $tot_check"));
}
header('Content-Type: application/json');
?>