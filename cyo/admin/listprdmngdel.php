<?php 
include 'config.php';
include 'header.php';
?>
<?php 
$prd_delid=$_POST['prd_delid'];

$pdr_del=mysqli_query($con,"delete from products_lists where prd_id='$prd_delid'");
if($pdr_del){
    $pdr_delfi=mysqli_query($con,"delete from invt_mang where prd_id='$prd_delid'");
    if($pdr_delfi){
        header('location:https://farmocart.com/admin?suBD=alldatalistSUB');
    }
}
?>