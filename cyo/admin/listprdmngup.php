<?php 
include 'config.php';
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

extract($_POST);

$img_name = $_FILES['prdimg']['name'];
$source=$_FILES["prdimg"]["tmp_name"];

$extension=array("jpeg","jpg","png","gif","tif");
$imagename = $_FILES['prdimg']['name'];
$ext = pathinfo($imagename, PATHINFO_EXTENSION);
 if(in_array($ext,$extension)){
     $imgRANDMR=rand(99999,10000);
               $imgRANDM='frmcart'.$curDateoff.$imgRANDMR;
              /*generating random image name ed*/
              
             $coverImgdir = '/home/farmocart/public_html/admin/assets/imgs/prdimgs/';
             if(!is_dir($coverImgdir)){
                mkdir($coverImgdir,0777,true);
            }
            
            $pngg='png';
              $imgRANDFS=$imgRANDM.'.'.$ext;
              $target = "$coverImgdir".$imgRANDFS;
              move_uploaded_file($source, $target);
              

              $imagepath = $imgRANDFS;
              $imgN='assets/imgs/prdimgs/'.$imagepath;
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
              
              $qry=mysqli_query($con,"update products_lists set prd_name='$prdmnme',prd_img='$imgN' where prd_id='$upprid'");
              if($qry){
                  $inv_ins=mysqli_query($con,"update invt_mang set prd_name='$prdmnme',prd_img='$imgN' where prd_id='$upprid'");
                  if($inv_ins){
                      header('location:https://farmocart.com/admin?suBD=alldatalistSUB');
                  }
                  
              }
              
              
              
 }
 else{
     echo 'please upload image file';
     
 }
?>