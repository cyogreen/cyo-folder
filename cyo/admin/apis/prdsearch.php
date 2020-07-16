<?php 
include '../config.php';
?>

<?php 
$data = json_decode(file_get_contents("php://input"));

$ser_key=$data->keywrd;

$json_array=array("msg"=>"success");
$json_result=array();
$qry=mysqli_query($con,"select * from products where prd_name like '%$ser_key%'");
while($qryres=mysqli_fetch_assoc($qry)){
    $json_result[]=$qryres;
}
//json print of the result
echo json_encode($json_result);
header('Content-Type: application/json');
?>