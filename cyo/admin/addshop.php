<?php 
include 'config.php';
include 'header.php';
?>

<?php 
//time st//
$timezone = "Asia/Calcutta";
if(function_exists('date_default_timezone_set')) date_default_timezone_set($timezone);
$curDate = date('Y-m-d H:i:s');
$curDateoff=date('m');
$month_num=$curDateoff;
$month_name=date('M', mktime(0, 0, 0, $month_num, 10));
$dap=date('d').'-'.$month_name.'-'.date('Y').' '.date('H:i');
//time ed//

//checking shop is existed or not
$shpchek=mysqli_query($con,"select * from shops where contact_mobile='$smobile'");
$shpcheknum=mysqli_num_rows($shpchek);
if($shpcheknum > 0){
    ?>
    <div>Shop Already Exists.<a href="https://getmlmsoftware.com/demo/cyo/admin/?suBD=addshopsuc">Please Go Back</a></div>
    <?php
}
else{

extract($_POST);


// selection of shop id st
$shid_qr=mysqli_query($con,"select * from shops order by id DESC limit 1");
$shid_res=mysqli_fetch_assoc($shid_qr);
$shp_id=$shid_res['shop_id'];
$shp_id2=explode('SHP',$shp_id);
$shp_id3=$shp_id2[1]+1;
$shp_id='SHP'.$shp_id3;

// selection of shop id ed
/*$shp_array=array($shpim1,$shpim2,$shpim3);
$ar_cnt=count($shp_array);*/

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
             $coverImgdir = '/home/farmocart/public_html/admin/assets/shops/'.$shp_id.'/'.'imgs/';
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
 
//shop inside img st
$img_name2 = $_FILES['shpinimg']['name'];
$source2=$_FILES["shpinimg"]["tmp_name"];

$extension2=array("jpeg","jpg","png","gif","tif");
$imagename2 = $_FILES['shpinimg']['name'];
$ext2 = pathinfo($imagename2, PATHINFO_EXTENSION);
 if(in_array($ext2,$extension2)){
     $imgRANDMR2=rand(99999,10000);
               $imgRANDM2='frmcartsi'.$curDateoff.$imgRANDMR2;
              /*generating random image name ed*/
             $coverImgdir2 = '/home/farmocart/public_html/admin/assets/shops/'.$shp_id.'/'.'imgs/';
             if(!is_dir($coverImgdir2)){
                mkdir($coverImgdir2,0777,true);
            }
            
            $pngg2='png';
              $imgRANDFS2=$imgRANDM2.'.'.$ext2;
              $target2 = "$coverImgdir2".$imgRANDFS2;
              move_uploaded_file($source2, $target2);
              

              $imagepath2 = $imgRANDFS2;
              $imgN2='assets/shops/'.$shp_id.'/imgs/'.$imagepath2;
              $save2 = "$coverImgdir2" . $imagepath2; //This is the new file you saving
              $file2 = "$coverImgdir2" . $imagepath2; //This is the original file

              list($width2, $height2) = getimagesize($file2) ;

              $modwidth2 = 500;

              $diff2 = $width2 / $modwidth2;

              $modheight2 = $height2 / $diff2;
              $tn2 = imagecreatetruecolor($modwidth2, $modheight2) ;
              if($ext2 !== $pngg2){
              $image2 = imagecreatefromjpeg($file2) ;
              imagecopyresampled($tn2, $image2, 0, 0, 0, 0, $modwidth2, $modheight2, $width2, $height2) ;
              imagejpeg($tn2, $save2, 100) ;
              }
              
              
              /*header('location:https://farmocart.com/addcategory.php');*/
              
              
 }
   
 else{
     echo 'please upload image file';
     
 }
//shop inside img ed


//shop owner img st
$img_name3 = $_FILES['shpownimg']['name'];
$source3=$_FILES["shpownimg"]["tmp_name"];

$extension3=array("jpeg","jpg","png","gif","tif");
$imagename3 = $_FILES['shpownimg']['name'];
$ext3 = pathinfo($imagename3, PATHINFO_EXTENSION);
 if(in_array($ext3,$extension3)){
     $imgRANDMR3=rand(99999,10000);
               $imgRANDM3='frmcartow'.$curDateoff.$imgRANDMR3;
              /*generating random image name ed*/
             $coverImgdir3 = '/home/farmocart/public_html/admin/assets/shops/'.$shp_id.'/'.'imgs/';
             if(!is_dir($coverImgdir3)){
                mkdir($coverImgdir3,0777,true);
            }
            
            $pngg3='png';
              $imgRANDFS3=$imgRANDM3.'.'.$ext3;
              $target3 = "$coverImgdir3".$imgRANDFS3;
              move_uploaded_file($source3, $target3);
              

              $imagepath3 = $imgRANDFS3;
              $imgN3='assets/shops/'.$shp_id.'/imgs/'.$imagepath3;
              $save3 = "$coverImgdir3" . $imagepath3; //This is the new file you saving
              $file3 = "$coverImgdir3" . $imagepath3; //This is the original file

              list($width3, $height3) = getimagesize($file3) ;

              $modwidth3 = 500;

              $diff3 = $width3 / $modwidth3;

              $modheight3 = $height3 / $diff3;
              $tn3 = imagecreatetruecolor($modwidth3, $modheight3) ;
              if($ext3 !== $pngg3){
              $image3 = imagecreatefromjpeg($file3) ;
              imagecopyresampled($tn3, $image3, 0, 0, 0, 0, $modwidth3, $modheight3, $width3, $height3) ;
              imagejpeg($tn3, $save3, 100) ;
              }
              
              
              /*header('location:https://farmocart.com/addcategory.php');*/
              
              
 }
   
 else{
     echo 'please upload image file';
     
 }
//shop owner img ed
 
 //insert data for shop
 $creadted_by='Admin';
 $shp_status='approved';
   $qry=mysqli_query($con,"insert into shops(shop_id,shop_name,contact_mobile,alter_mobile,shop_email,address,shop_area,shop_pincode,shop_gst,shop_frntimg,shop_insimg,shop_ownimg,createdby_emp,status,date) 
              values('$shp_id','$shpnme','$smobile','$amobile','$semail','$saddrs','$sarea','$spincde','$sgst','$imgN','$imgN2','$imgN3','$creadted_by','$shp_status','$dap')");
              if($qry){
                 $rnd=rand(9999,1000);
                 $shp_psd=$shpnme[0].$shpnme[1].$shpnme[2].$rnd;
                 $sucrd=mysqli_query($con,"insert into cust_lgn(shop_id,mobile,reg_date) values('$shp_id','$smobile','$dap')");
                 if($sucrd){
                     ?>
                     <script>
                         var shpnme='<?php echo $shpnme?>';
                         var smobile='<?php echo $smobile;?>';
                         
                         if($.post('adshopotp.php',{shpnme:shpnme,smobile:smobile},function(data){
                             ver=data;
                         })){
                             window.location.assign('https://getmlmsoftware.com/demo/cyo/admin?suBD=addshopsuc');
                         }
                         
                     </script>
                     <?php
                 }
              }
}
?>