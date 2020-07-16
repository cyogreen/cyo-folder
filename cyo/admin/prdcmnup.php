<?php 
include 'config.php';
include 'header.php';
?>

<?php 
$prd_upid=$_POST['prd_upid'];
$prd_cmnva=$_POST['prd_cmnva'];

$qry=mysqli_query($con,"update products_lists set emp_commission='$prd_cmnva' where prd_id='$prd_upid'");
if($qry){
    ?>
    <button class="btn btn-success">Updated</button>
    <?php
}
?>