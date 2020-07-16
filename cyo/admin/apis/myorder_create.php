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
$dap=date('d').'-'.date('m').'-'.date('Y');
$daptim=date('H:i:s');
//time ed//

$ordr_tim=date('d').date('m').date('Y').date('H').date('i').date('s');

$data = json_decode(file_get_contents("php://input"));
$shp_id=$data->shp_id;
$prd_ids=$data->prd_ids;
$order_qtys=$data->order_qtys;
$orders_amnt=$data->orders_amnt;
$total_amount=$data->total_amount;
$ordr_tim=date('d').date('m').date('Y').date('H').date('i').date('s');
$stas='pending';

//selection of employee id
$empsel=mysqli_query($con,"select * from shops where shop_id='$shp_id'");
$empselres=mysqli_fetch_assoc($empsel);
$emp_id=$empselres['createdby_emp'];
$sh_mobile=$empselres['contact_mobile'];
$sh_name=$empselres['shop_name'];

$order_qry=mysqli_query($con,"insert into sales_rept(shop_id,prd_id,order_id,amount,date,time,status,order_qty,orders_amnt,assign_empid)
values('$shp_id','$prd_ids','$ordr_tim','$total_amount','$dap','$daptim','$stas','$order_qtys','$orders_amnt','$emp_id')");

if($order_qry){
    $prd_idsexp=explode(',',$prd_ids);
    $prd_idsexpcnt=count($prd_idsexp)-1;
    
    $qty_exp=explode(',',$order_qtys);
    $amnt_exp=explode(',',$orders_amnt);
    
    $i=0;
    for($i=0; $i < $prd_idsexpcnt; $i++){
        $prd_inv=$prd_idsexp[$i];
        $qty_inv=$qty_exp[$i];
        $amt_inv=$amnt_exp[$i];
        
        /*$jkl;
        $jkl=$jkl.'/'.$prd_inv;*/
        //selection of prev qty
        $plo=mysqli_query($con,"select * from invt_mang where prd_id='$prd_inv'");
        $plores=mysqli_fetch_assoc($plo);
        $pcnt=mysqli_num_rows($plo);
        $prev_qty[$i]=$plores['avail_qty'];
        
        $new_qty=$prev_qty[$i]-$qty_inv;
        //update qty
        $ploup=mysqli_query($con,"update invt_mang set avail_qty='$new_qty' where prd_id='$prd_inv'");
        
    }
    
    /*$opn=mysqli_query($con,"update invt_mang set avail_qty='$pcnt' where prd_id='PRD18'");*/
    
    if($ploup){
        
    $delcart=mysqli_query($con,"delete from mycart where shop_id='$shp_id'");
    if($delcart){
        include 'ordermsg.php';
        echo json_encode(array("message" => "Your order has been placed successfully"));    
    }
    else{
     echo json_encode(array("message" => "Something went wrong!"));   
    }
    
    }
    
}
else{
    echo json_encode(array("message" => "Something went wrong"));
}
header('Content-Type: application/json');
?>