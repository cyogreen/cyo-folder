<?php

// shop front img st
$img_name = $_FILES['shpfrimg']['name'];
$source=$_FILES["shpfrimg"]["tmp_name"];

$extension=array("jpeg","jpg","png","gif","tif");
$imagename = $_FILES['shpfrimg']['name'];
$ext = pathinfo($imagename, PATHINFO_EXTENSION);
 if(in_array($ext,$extension)){
     $imgRANDMR=rand(99999,10000);
               $imgRANDM='frmcartsf'.$curDateoff.$imgRANDMR;
              /*generating random image name ed*/
             $coverImgdir = '/home/farmocart/public_html/admin/cool/';
             if(!is_dir($coverImgdir)){
                mkdir($coverImgdir,0777,true);
            }
            
            $pngg='png';
              $imgRANDFS=$imgRANDM.'.'.$ext;
              $target = "$coverImgdir".$imgRANDFS;
              move_uploaded_file($source, $target);
              

              $imagepath = $imgRANDFS;
              $imgN='assets/shops/'.$shp_id.'/imgs/'.$imagepath;
              $save = "$coverImgdir" . $imagepath; //This is the new file you saving
              $file = "$coverImgdir" . $imagepath; //This is the original file

              list($width, $height) = getimagesize($file) ;

              $modwidth = 500;

              $diff = $width / $modwidth;

              $modheight = $height / $diff;
              $tn = imagecreatetruecolor($modwidth, $modheight) ;
              if($ext !== $pngg){
              $image = imagecreatefromjpeg($file) ;
              imagecopyresampled($tn, $image, 0, 0, 0, 0, $modwidth, $modheight, $width, $height) ;
              imagejpeg($tn, $save, 100) ;
              }
              
              
              /*header('location:https://farmocart.com/addcategory.php');*/
              
              
 }
   
   
   
   
   
   
   
   
   
   
   
 else{
     echo 'please upload image file';
     
 }
 // shop front img ed
?>
?>




<!DOCTYPE html>
<html>
<body>

<form action="" method="post" enctype="multipart/form-data">
    Select image to upload:
    <input type="file" name="shpfrimg" id="shpfrimg">
    <input type="submit" value="Upload Image" name="submit">
</form>

</body>
</html>