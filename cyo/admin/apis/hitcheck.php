<?php 
include '../config.php';
?>

<?php 
$data = json_decode(file_get_contents("php://input"));
$otp_check=$data->otp_check;
$mob_cut=$data->mob_cust;

//checking the otp

$chk=mysqli_query($con,"select * from cust_lgn where mobile='$mob_cut' && otp='$otp_check'");
$chkres=mysqli_fetch_assoc($chk);
$chkcnt=mysqli_num_rows($chk);

$shop_id=array();
if($chkcnt == 1){
    $shop_id=$chkres['shop_id'];
    echo json_encode(array("msg" => "Login Success","shp_id" => "$shop_id"));
    
}
else{
     echo json_encode(array("msg" => "Login Failed"));
}
header('Content-Type: application/json');
?>