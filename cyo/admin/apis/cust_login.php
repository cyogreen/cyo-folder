<?php 
include '../config.php';

$data = json_decode(file_get_contents("php://input"));
$email_id=$data->email_id;
$ps_wrd=$data->ps_wrd;

$qry=mysqli_query($con,"select * from cust_lgn where email='$email_id' && psd='$ps_wrd'");

//assign array
$json_array=array();

//fetch details
$res=mysqli_fetch_assoc($qry);
$json_array=$res['shop_id'];

//get and check login
$res_cnt=mysqli_num_rows($qry);

//condition
if($res_cnt == 1){
    
    // set response code - 201 created
    http_response_code(201);
        
    /*echo json_encode($json_array); */
    echo json_encode(array("Shop_ID" => "$json_array","message" => "Login Success"));
}
else{
        // set response code - 503 service unavailable
        http_response_code(503);
        
        echo json_encode(array("message" => "Login Failed"));
}
header('Content-Type: application/json');
?>