<?php 
include '../config.php';

$cat_id='4';
$status='Active';
$qry=mysqli_query($con,"select prd_id,prd_img,prd_name from products_lists");
$json_array=array();
while($res=mysqli_fetch_assoc($qry)){
    $json_array[]=$res;

}
echo json_encode($json_array);
header('Content-Type: application/json');
?>