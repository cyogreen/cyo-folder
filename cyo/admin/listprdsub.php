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
//selection of prev product id
$prdidqr=mysqli_query($con,"select * from products_lists order by id DESC limit 1");
$prdidres=mysqli_fetch_assoc($prdidqr);
$prd_id=$prdidres['prd_id'];
$prd_id2=explode('PRD',$prd_id);
$prd_id3=$prd_id2[1]+1;
$prd_id='PRD'.$prd_id3;

$img_name = $_FILES['prdimg']['name'];
$source=$_FILES["prdimg"]["tmp_name"];

$extension=array("jpeg","jpg","png","gif","tif");
$imagename = $_FILES['prdimg']['name'];
$ext = pathinfo($imagename, PATHINFO_EXTENSION);
 if(in_array($ext,$extension)){
     $imgRANDMR=rand(99999,10000);
               $imgRANDM='frmcart'.$curDateoff.$imgRANDMR;
              /*generating random image name ed*/
              
             $coverImgdir = '/home/getmlmsoftware/public_html/demo/cyo/admin/assets/imgs/prdimgs/';
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
              
              $qry=mysqli_query($con,"insert into products_lists(cat_id,prd_id,prd_img,prd_name,qty_type,emp_commission,date) values('$prdcat','$prd_id','$imgN','$prdname','$qtytype','$prdcmsn','$dap')");
              if($qry){
                  $inv_ins=mysqli_query($con,"insert into invt_mang(prd_id,cat_id,prd_name,prd_img,avail_qty,recup_qty) values('$prd_id','$prdcat','$prdname','$imgN','0','0')");
                  if($inv_ins){
                      header('location:https://getmlmsoftware.com/demo/cyo/admin?suBD=listproductSUB');
                  }
                  
              }
              
              
              
 }
 else{
     echo 'please upload image file';
     
 }
?>