<?php 
include 'config.php';
?>

<?php 
//section of category id st//
$qry=mysqli_query($con,"select * from categories order by id DESC limit 1");
$qrres=mysqli_fetch_assoc($qry);
$cat_id=$qrres['cat_id'];
$cat_id=$cat_id+1;
//section of category id ed//

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

$img_name = $_FILES['catimg']['name'];
$source=$_FILES["catimg"]["tmp_name"];

$extension=array("jpeg","jpg","png","gif","tif");
$imagename = $_FILES['catimg']['name'];
$ext = pathinfo($imagename, PATHINFO_EXTENSION);
 if(in_array($ext,$extension)){
     $imgRANDMR=rand(99999,10000);
               $imgRANDM='frmcart'.$curDateoff.$imgRANDMR;
              /*generating random image name ed*/
              
             $coverImgdir = '/home/farmocart/public_html/admin/assets/imgs/catimgs/';
             if(!is_dir($coverImgdir)){
                mkdir($coverImgdir,0777,true);
            }
            
            $pngg='png';
              $imgRANDFS=$imgRANDM.'.'.$ext;
              $target = "$coverImgdir".$imgRANDFS;
              move_uploaded_file($source, $target);
              

              $imagepath = $imgRANDFS;
              $imgN='assets/imgs/catimgs/'.$imagepath;
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
              
              $qry=mysqli_query($con,"insert into categories(cat_id,cat_name,cat_img,add_date) values('$cat_id','$catname','$imgN','$dap')");
              if($qry){
                 header('location:https://getmlmsoftware.com/demo/cyo/admin?suBD=addcatsuc');
              }
              /*header('location:https://farmocart.com/addcategory.php');*/
              
              
 }
 else{
     echo 'please upload image file';
     
 }
 
?>