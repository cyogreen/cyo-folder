<?php 
include 'config.php';
include 'header.php';
?>
<?php 
$prd_delid=$_POST['prd_delid'];

$pdr_del=mysqli_query($con,"insert into del_products(cat_id,prd_id,prd_name,min_qty,max_qty,sale_price,offer_price,prd_status,date) select cat_id,prd_id,prd_name,min_qty,max_qty,sale_price,offer_price,prd_status,date from products where prd_id='$prd_delid'");
if($pdr_del){
    $pdr_delfi=mysqli_query($con,"delete from products where prd_id='$prd_delid'");
    if($pdr_delfi){
        header('location:https://farmocart.com/admin?suBD=alldatalistSUB');
    }
}
?>