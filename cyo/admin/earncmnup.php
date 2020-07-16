<?php 
include 'config.php';
include 'header.php';
?>

<?php 
$ord_id=$_POST['ord_id'];
$ear_cmnva=$_POST['ear_cmnva'];
$st_upp='approved';

$qry=mysqli_query($con,"update employee_payouts set commission_amnt='$ear_cmnva',status='$st_upp' where order_id='$ord_id'");
if($qry){
    ?>
    <button class="btn btn-success">Approved</button>
    <?php
}
?>