<?php 
include '../config.php';

$cat_id='3';
$status='Active';
$qry=mysqli_query($con,"select * from products where cat_id='$cat_id' && prd_status='$status' Order by RAND()");
$json_array=array();
while($res=mysqli_fetch_assoc($qry)){
    $prd_id=$res['prd_id'];
    
    $inv=mysqli_query($con,"select * from invt_mang where prd_id='$prd_id'");
    $invres=mysqli_fetch_assoc($inv);
    $avai_qty=$invres['avail_qty'];
    $shrtg=$invres['inv_shrtg'];
    if($avai_qty > $shrtg){
        $json_array[]=$res;
    }
    
}
echo json_encode($json_array);
header('Content-Type: application/json');
?>