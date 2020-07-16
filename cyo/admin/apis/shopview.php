<?php 
include '../config.php';

$data = json_decode(file_get_contents("php://input"));
$shp_id=$data->shp_id;

$qry=mysqli_query($con,"select * from shops where shop_id='$shp_id'");
$json_array=array();
while($res=mysqli_fetch_assoc($qry)){
    $json_array[]=$res;

}
echo json_encode($json_array);
header('Content-Type: application/json');
?>