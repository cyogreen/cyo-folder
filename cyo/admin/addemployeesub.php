<head>
    <style>
        .em_adsuc{
    text-align: center;
    font-family: 'Roboto', sans-serif;
    font-weight: 700;
        }
    </style>
</head>

<?php 
include 'config.php';
include 'header.php';

//time st//
$timezone = "Asia/Calcutta";
if(function_exists('date_default_timezone_set')) date_default_timezone_set($timezone);
$curDate = date('Y-m-d H:i:s');
$curDateoff=date('m');
$month_num=$curDateoff;
$month_name=date('M', mktime(0, 0, 0, $month_num, 10));
$dap=date('d').'-'.$month_name.'-'.date('Y').' '.date('H:i');
//time ed//
?>

<?php 
extract($_POST);
$ename=$_POST['emp_name'];
$emobile=$_POST['emp_mobile'];
$email=$_POST['emp_email'];
$erole=$_POST['emp_role'];
$bname=$_POST['emp_bank'];
$acno=$_POST['emp_accn'];
$ifsc=$_POST['emp_ifsc'];
$pas_wrd=$_POST['pas_wrd'];

//password creation
$rnd=rand(9999,1000);
$emp_psd=$ename[0].$ename[1].$ename[2].$rnd;
    
/*$pwsdenc=$pwsd;*/
 /*select emp id st*/
                  $empID=mysqli_query($con,"select * from employee_table order by id DESC limit 1");
                  $empIDRES=mysqli_fetch_assoc($empID);
                  $lemp_ID=$empIDRES['emp_id'];
                  
                  $lemp_ID=$lemp_ID+1;
                  
                  /*select emp id ed*/

$qry=mysqli_query($con,"insert into employee_table(emp_id,name,mobile,email,pas_wrd,role,bank_name,acnt_no,ifsc_code,date) values('$lemp_ID','$ename','$emobile','$email','$emp_psd','$erole','$bname','$acno','$ifsc','$dap')");
if($qry){
    ?>
    <script>
        var emobile='<?php echo $emobile?>';
        var empname='<?php echo $ename?>';
        var epsd='<?php echo $emp_psd;?>';
        var eemail='<?php echo $email;?>';
         
                         if($.post('newemployeeotp.php',{emobile:emobile,empname:empname,epsd:epsd,eemail:eemail},function(data){
                             ver=data;
                         })){
                             $('#em_sub .out_suc').addClass('em_adsuc');
                             $('#em_sub .out_suc').text('Employee add successfully');
                             
                         }
    </script>
    <?php
}
else{
    ?>
    <div>Something went wrong!</div>
    <?php
}
?>