<?php
include '../config.php';
?>

<?php 
$data=json_decode(file_get_contents("php://input"));

$shop_id=$data->shop_id;
$prd_id=$data->prd_id;

//delete products in cart
$cartdel=mysqli_query($con,"delete from mycart where shop_id='$shop_id' && prd_id='$prd_id'");
if($cartdel){
    echo json_encode(array("message" => "Item deleted successfully"));
}
else{
    echo json_encode(array("message" => "Something went wrong!"));
}
header('Content-Type: application/json');
?>