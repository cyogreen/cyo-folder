<?php 
include 'config.php';
include 'header.php';
?>

<?php 
//time st//
$timezone = "Asia/Calcutta";
if(function_exists('date_default_timezone_set')) date_default_timezone_set($timezone);
$curDate = date('Y-m-d H:i:s');
$curDateoff=date('m');
$month_num=$curDateoff;
$month_name=date('M', mktime(0, 0, 0, $month_num, 10));
$dap=date('d').'-'.date('m').'-'.date('Y');
$daptim=date('H:i:s');
//time ed//

extract($_POST);

//variables declaration
$status=$ordstu;
$order_id=$orddii;
$prd_cnt=$rfdpc;
$refd_qty=$_POST['cv'];

/*$k=0;
for($k=0; $k < $prd_cnt; $k++){
    $index1="cv".$k;
	$first[$k]=$_POST[$index1];
    
    $tpp;
    $tpp=$first[$k].','.$tpp;
}*/

//selection of orders
$selrep=mysqli_query($con,"select * from sales_rept where order_id='$order_id'");
$selres=mysqli_fetch_assoc($selrep);
$prev_amnt=$selres['amount'];
$shop_id=$selres['shop_id'];
$emp_id=$selres['assign_empid'];

$prd_ids=$selres['prd_id'];
$prd_idsex=explode(',',$prd_ids);
$no_ret=count($prd_idsex)-1;

$ord_ids=$selres['order_qty'];
$ord_idsex=explode(',',$ord_ids);

$ord_amnts=$selres['orders_amnt'];
$ord_amntsex=explode(',',$ord_amnts);

//without return stock employee calculation st
      $k=0;
        for($k=0; $k < $no_ret; $k++){
            $prd_gid=$prd_idsex[$k];
            $qty_gid=$ord_idsex[$k];
            $prc_gid=$ord_amntsex[$k];
            //selection of commision
            $gid=mysqli_query($con,"select * from products_lists where prd_id='$prd_gid'");
            $gidres=mysqli_fetch_assoc($gid);
            $gcmi=$gidres['emp_commission'];
            
            //calu order comi
            $orcal=$qty_gid * $prc_gid;
            $orcalf=($orcal/100)*$gcmi;
            $orcalff;
            $orcalff=$orcalff+$orcalf;
            /*$empy_per;
            $empy_per=$empy_per.'/'.$gidres['emp_commission'];*/
            
        }
//without return stock employee calculation ed
$upstqry=mysqli_query($con,"update sales_rept set status='$status' where order_id='$order_id'");
if($upstqry){
    $i=0;
    for($i=0; $i < $prd_cnt; $i++){
        
        
        //refund stock qty st
            $index1="cv".$i;
	        $first[$i]=$_POST[$index1];
	        
	        if($first[$i] != '0'){
	        $tpp;
            $tpp=$first[$i].','.$tpp;
            
        //refund stock qty ed
        
        $pra;
        $pra=$prd_idsex[$i].','.$pra;
        
        $amna;
        $amna=$ord_amntsex[$i].','.$amna;
        
        //comission calcu
        $prd_wit=$prd_idsex[$i];
        
          $comisel=mysqli_query($con,"select * from products_lists where prd_id='$prd_wit'");
	      $comiselres=mysqli_fetch_assoc($comisel);
	      $co_pers=$comiselres['emp_commission'];
	      
	      $wit_cal=$first[$i] * $ord_amntsex[$i];
	      $wit_calff=(($wit_cal/100) * $co_pers);
	      $wit_calf;
	      $wit_calf=$wit_calf+$wit_calff;
	      $wit_clf=($orcalff - $wit_calf);
	      
        //total amount calculation
        $totbil;
        $totbil=$totbil+(($first[$i]) * ($ord_amntsex[$i]));
        
        //adding inventory to stocks
        $prd_ret=$prd_idsex[$i];
        $qty_ret=$first[$i];
        $plo=mysqli_query($con,"select * from invt_mang where prd_id='$prd_ret'");
        $plores=mysqli_fetch_assoc($plo);
        $pcnt=mysqli_num_rows($plo);
        $prev_qty[$i]=$plores['avail_qty'];
        
        $new_qty=$prev_qty[$i]+$qty_ret;
        
        //update qty
        $ploup=mysqli_query($con,"update invt_mang set avail_qty='$new_qty' where prd_id='$prd_ret'");
        
	  }
	  else{
	      
	  }
	  
    }
    //store refund stock
    $tpexp=explode(',',$tpp);
    $tpcnt=count($tpexp)-1;
    if($tpcnt > 0){
	  $refins=mysqli_query($con,"insert into return_stock(shop_id,prd_id,order_id,ref_amount,amount,order_qty,orders_amnt,assign_empid,date,time,status) values('$shop_id','$pra','$order_id','$totbil','$prev_amnt','$tpp','$amna','$emp_id','$dap','$daptim','$status')");
	  if($refins){
	      //selection of commission
	      $tp_prd=$pra;
	      $tp_prdex=explode(',',$tp_prd);
	      $tp_fprd=$tp_prdex[$i];

	      
	      $comi_totor=(($prev_amnt) - ($totbil));
	      /* $comi_pers=((($comi_totor)/100)*1);*/
	      
	      $comi_sta='pending';
	      $emp_comi=mysqli_query($con,"insert into employee_payouts(emp_id,order_id,order_amount,commission_amnt,date,time,status) values('$emp_id','$order_id','$comi_totor','$wit_clf','$dap','$daptim','$comi_sta')");
	      if($emp_comi){
	          header('location:https://farmocart.com/admin?suBD=alldatalistSUB');
	      }
	      else{
	          echo 'Something went wrong';
	      }
	      
	  }
    }
    else{
  
        $comi_totor=$prev_amnt;
	    $comi_sta='pending';
        $emp_comi=mysqli_query($con,"insert into employee_payouts(emp_id,order_id,order_amount,commission_amnt,date,time,status) values('$emp_id','$order_id','$comi_totor','$orcalff','$dap','$daptim','$comi_sta')");
        if($emp_comi){
            header('location:https://farmocart.com/admin?suBD=alldatalistSUB');
        }
        else{
            echo 'Something went wrong';
        }
    }

/*echo $tpp;
echo '<br>';
echo $pra;
echo '<br>';
echo $amna;
echo '<br>';
echo $totbil;*/
}
?>