<?php
//getting variables
$smobile='91'.$_POST['smobile'];
$shpnme=$_POST['shpnme'];
$psd=$_POST['psd'];
$email=$_POST['email'];

function PostRequest($url, $referer, $_data) {
// convert variables array to string:
$data = array(); while(list($n,$v) =
each($_data)){
$data[] = "$n=$v";
}
$data = implode('&', $data);
// format --> test1=a&test2=b etc.
// parse the given URL
$url = parse_url($url);
if ($url['scheme'] != 'http') {
die('Only HTTP request are supported !');
}
// extract host and path:
$host = $url['host'];
$path = $url['path'];
// open a socket connection on port 80
$fp = fsockopen($host, 80);
// send the request headers:
fputs($fp, "POST $path HTTP/1.1\r\n");
fputs($fp, "Host: $host\r\n");
fputs($fp, "Referer: $referer\r\n");
fputs($fp, "Content-type: application/x-www-form-urlencoded\r\n");
fputs($fp, "Content-length: ". strlen($data) ."\r\n");
fputs($fp, "Connection: close\r\n\r\n");
fputs($fp, $data);
$result = '';
while(!feof($fp)) {
// receive the results of the request
$result .= fgets($fp, 128);
}
// close the socket connection:
fclose($fp);
// split the result header from the content
$result = explode("\r\n\r\n", $result, 2);
$header = isset($result[0]) ? $result[0] : '';
$content = isset($result[1]) ? $result[1] : '';
// return as array:
return array($header, $content);
}
/*$rt=$shpnme;
$mobile=$smobile;*/
$data = array(
'apikey'=>"cbD5OthmiOD8pD4z2%3dbyVJXz3snpqsX9apoZvizlxb8%3d",
'password'=>"Abhishek@73",
'msisdn' => "$smobile",
'sid' => "GullyP",
'msg'=> "Dear $shpnme Thanks for registering your shop with us. Your Login details are Email: $email and Password: $psd Team Farmocart.com",
'fl' =>"0",
'gwid' =>"2",
);
list($header, $content) = PostRequest( "http://apps.smslane.com/vendorsms/pushsms.aspx",
// the url to post to
"http://www.yourdomain.com/sms.php", // its your url
$data
);
echo $content;
?>