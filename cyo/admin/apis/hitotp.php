<?php 
include '../config.php';
?>

<?php 
$data = json_decode(file_get_contents("php://input"));
$mob_cust=$data->mob_cust;

 $mobile=$mob_cust;
                       $senderId = "fmcart";
                       $rand=rand(9999,1000);
                       $smsmessage = urlencode('Hi Your verification code is:'.' '.$rand.' '.'Team Farmocart.com');
                       if($smsmessage){
                           //store otp
                           $otpp=mysqli_query($con,"update cust_lgn set otp='$rand' where mobile='$mobile'");
                           if($otpp){
                               echo json_encode(array("message" => "Verification code has been sent to your registered mobile number","OTP IS" => "$rand"));
                           }
                           
                           
                       }
                       /* Define route */
                       $route = "route=4";
                       /* Prepare you post parameters */
                       $postData = array(
                           'authkey' => $authKey,
                           'mobiles' => $mobile,
                           'message' => $smsmessage,
                           'sender' => $senderId,
                           'route' => $route
                           );
                           /* API URL*/ 
                           $url="http://sms.a2zsms.in/api.php?username=kibitz73&password=914661&to=$mobile&from=fmcart&message=test";
                           //echo "$url";
                           /* init the resource */
                           $ch = curl_init();
                           curl_setopt_array($ch, array(
                               CURLOPT_URL => $url,
                               CURLOPT_RETURNTRANSFER => true,
                               CURLOPT_POST => true,
                               CURLOPT_POSTFIELDS => $postData
                               /*,CURLOPT_FOLLOWLOCATION => true */
                               ));
                               /* Ignore SSL certificate verification */
                               curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
                               curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);
                               /* get response */
                               $output = curl_exec($ch);
                               /* Print error if any */
                               if(curl_errno($ch))
                               {
                                   echo 'error:' . curl_error($ch);
                                   
                               }
                               curl_close($ch);
                               /*echo $_SESSION['main'];*/
                               header('Content-Type: application/json');
?>