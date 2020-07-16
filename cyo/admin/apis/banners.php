<?php 
include '../config.php';

$qry=mysqli_query($con,"select * from banners");
$json_array=array();
while($res=mysqli_fetch_assoc($qry)){
    $json_array[]=$res;

}
echo json_encode($json_array);
header('Content-Type: application/json');
?>