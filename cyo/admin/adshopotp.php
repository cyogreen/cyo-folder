<?php
session_start();

$shpnme=$_POST['shpnme'];
$mobile=$_POST['smobile'];



/* Sender ID,While using route4 sender id should be 6 characters long. */
$senderId = "fmcart";
$smsmessage = urlencode('Hi'.' '.ucfirst($shpnme).' '.'Thank you for registering your shop with us.'.' '.'Team Farmocart.com');

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

?>