<?php 
include '../config.php';
?>
<?php
$data=json_decode(file_get_contents("php://input"));
$shop_id=$data->shop_id;

$jsary=array();
$qry=mysqli_query($con,"select * from mycart where shop_id='$shop_id'");
while($qryres=mysqli_fetch_assoc($qry)){
    $jsary[]=$qryres;
}
echo json_encode($jsary);
header('Content-Type: application/json');
?>