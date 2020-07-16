<?php 
include '../config.php';
?>

<?php 
$data = json_decode(file_get_contents("php://input"));

$name=$data->name;
$mobile=$data->mobile;
$addr=$data->address;
$json_array=array("msg"=>"success");

$qry=mysqli_query($con,"insert into hju(name,mobile,address) values('$name','$mobile','$addr')");
if($qry){
      // set response code - 201 created
        http_response_code(201);
 
        // tell the user
        echo json_encode(array("message" => "Data inserted successfully"));
}
else{
    
        // set response code - 503 service unavailable
        http_response_code(503);
 
        // tell the user
        echo json_encode(array("message" => "Data not inserted successfully"));
}
?>