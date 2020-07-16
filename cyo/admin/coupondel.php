<?php 
include 'config.php';
include 'header.php';
?>

<?php 
$cpn_dele=$_POST['cpn_dele'];

$cpn_del=mysqli_query($con,"delete from shop_coupons where shop_id='$cpn_dele'");
if($cpn_del){
    header('location:https://farmocart.com/admin');
}

?>