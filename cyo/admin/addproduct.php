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
//selection of if product alreadt exits or not
$exi_prid=mysqli_query($con,"select * from products where prd_id='$hprdid'");
$exi_pridres=mysqli_fetch_assoc($exi_prid);
$exi_cnt=mysqli_num_rows($exi_prid);
if($exi_cnt >= 1){
    ?>
    <div>Product Already Exists. &nbsp;<a href="https://getmlmsoftware.com/demo/cyo/admin">Please Go Back</a></div>
    <?php
}
else{

//selection of prev product id
$prdidqr=mysqli_query($con,"select * from products_lists where prd_id='$hprdid'");
$prdidres=mysqli_fetch_assoc($prdidqr);
$prdd_id=$prdidres['prd_id'];
$cat_id=$prdidres['cat_id'];
$prd_img=$prdidres['prd_img'];
$prd_nme=$prdidres['prd_name'];

              $qry=mysqli_query($con,"insert into products(cat_id,prd_id,prd_name,min_qty,max_qty,sale_price,offer_price,prd_status,date) values('$cat_id','$prdd_id','$prd_nme','$minqty','$maxqty','$slepric','$offrpric','$status','$dap')");
              if($qry){
                  header('location:https://getmlmsoftware.com/demo/cyo/admin?suBD=addprdsuc');
              }
}
        
?>