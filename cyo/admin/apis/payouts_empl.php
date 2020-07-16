<?php 
include '../config.php';

$data = json_decode(file_get_contents("php://input"));
$emp_id=$data->emp_id;

$apr='approved';
$qry=mysqli_query($con,"select * from employee_payouts where emp_id='$emp_id' && status='$apr' order by id DESC");
$json_array=array();
while($res=mysqli_fetch_assoc($qry)){
    $json_array[]=$res;

}
echo json_encode($json_array);
header('Content-Type: application/json');
?>