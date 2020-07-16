<?php 
include 'config.php';
?>

<?php
session_start();
extract($_POST);
$usr_email=$email;
$usr_psd=$pass;
//echo $sq="select * from employee_table where email='$usr_email' && pas_wrd='$usr_psd'";
$usel=mysqli_query($con,"select * from employee_table where email='$usr_email' && pas_wrd='$usr_psd'");
$uselres=mysqli_fetch_assoc($usel);
$uselcnt=mysqli_num_rows($usel);
if($uselcnt == 1){
    $_SESSION['empid']=$uselres['emp_id'];
    header('location:https://getmlmsoftware.com/demo/cyo/admin');
}
else{
    ?>
    <span>Login Failed&nbsp;<a href="https://getmlmsoftware.com/demo/cyo/admin/login">Go Back</a></span>
    <?php
}
?>