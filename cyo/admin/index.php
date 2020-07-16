<?php 
include 'config.php';
session_start();
$suotput=$_REQUEST['suBD'];
$seid=$_SESSION['empid'];
if($seid != NULL){
    

//selection of login details
$lusr=mysqli_query($con,"select * from employee_table where emp_id='$seid'");
$lusrres=mysqli_fetch_assoc($lusr);

?>
<html>
    <head>
        <title>CYO</title>
        <?php include 'header.php';?>
    </head>
    <body>
        <header class="head">
            <a href="http://getmlmsoftware.com/demo/cyo/admin">CYO</a>
            <span class="weadm">
                
                <!--inventory shortage notification st-->
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
                
                $inv_sht=mysqli_query($con,"select * from invt_mang");
                while($inv_shtres=mysqli_fetch_assoc($inv_sht)){
                    $shr_prdid=$inv_shtres['prd_id'];
                    $avl_qty=$inv_shtres['avail_qty'];
                    $shr_qty=$inv_shtres['inv_shrtg'];
                    $shr_cati=$inv_shtres['cat_id'];
                    $shr_prdnme=$inv_shtres['prd_name'];
                    
                    if($avl_qty > $shr_qty){
                        $prddel=mysqli_query($con,"delete from notifications where prd_id='$shr_prdid'");
                    }
                    else{
                        //selection of product id
                        $sprdid=mysqli_query($con,"select * from notifications where prd_id='$shr_prdid'");
                        $sprcnt=mysqli_num_rows($sprdid);
                        if($sprcnt == 0){
                        $not_ty='inventory';
                        $inv_shtins=mysqli_query($con,"insert into notifications(cat_id,prd_id,prd_name,avail_qty,inv_shrtg,type,notify_date) values('$shr_cati','$shr_prdid','$shr_prdnme','$avl_qty','$shr_qty','$not_ty','$dap')");    
                        }
                        else{
                            $invstcup=mysqli_query($con,"update notifications set avail_qty='$avl_qty',inv_shrtg='$shr_qty' where prd_id='$shr_prdid',notify_date='$dap'");
                        }
                        
                    }
                }
                ?>
                <!--inventory shortage notification ed-->
                
                <!--notification content st-->
                <span clas=="notifi" id="notify_icon" style="font-size:25px;cursor:pointer">
                    
                    <!--notifications count st-->
                    <?php 
                    $sprdidnot=mysqli_query($con,"select * from notifications");
                    $sprcntnot=mysqli_num_rows($sprdidnot);
                    ?>
                    <!--notifications count ed-->
                    <sup style="font-weight:700;color:#ffc23d;"><?php echo $sprcntnot;?></sup>
                    <i class="fa fa-bell"></i>
                </span>
                <!--notification content ed-->
                
                <?php echo 'Welcome'.' '.ucwords($lusrres['name']);?>
                &nbsp;&nbsp;<a href="logout.php"><i class="fa fa-sign-out"></i></a>
            </span>
            
        </header>
        <section class="sec1">
            <!--left pane st-->
            <?php 
            if($seid != NULL){
            ?>
        <div class="split left">
            <div id="aldli" onclick="dlist(this)"  data-dl="alldatalist" class="list"><img src="images/folder.png">&nbsp;&nbsp;Management System</div>
            <div onclick="addemp(this)" data-addemp="addemployee" class="list"><img src="images/add.png">&nbsp;&nbsp;Add employee</div>
            <div id="adcat" onclick="addcat(this)" data-addcat="addcategory" class="list"><img src="images/listings.png">&nbsp;&nbsp;Add Category</div>
            <div id="adshp" onclick="addshp(this)" data-addshp="addshop" class="list"><img src="images/addshop.png">&nbsp;&nbsp;Add Shop</div>
            <div id="adp" onclick="addprd(this)" data-addprd="addproduct" class="list"><img src="images/sales.png">&nbsp;&nbsp;Add Product</div>
            <div id="lisprd" onclick="liprd(this)" data-lisprd="listproduct" class="list"><img src="images/checklist.png">&nbsp;&nbsp;List Product</div>
            <div id="adinvt" onclick="invtmg(this)" data-invcon="inventory" class="list"><img src="images/inventory.png">&nbsp;&nbsp;Add Inventory</div>
            <div id="adcpn" onclick="cupnmg(this)" data-coupns="coupons" class="list"><img src="images/coupon.png">&nbsp;&nbsp;Create Coupons</div>
        </div>
        <?php
            }
            ?>
        <!--left pane ed-->
        
        <!--right pane st-->
        <div class="split right">
            <div class="right_con"><img id="logo" src="images/formocart.jpg"></div>
            
        </div>
        <!--right pane ed-->
        </section>
        
        <!--posting form st-->
        <script>
            //function all data list
            function dlist(dl){
                var funv1=$(dl).data('dl');
                $.ajax({
                    url: 'formsdat.php',
                    type: 'post',
                    data: {funv1:funv1},
                    beforeSend: function(){
                        $('.right .right_con').html('Loading Please wait...');
                    },
                    success: function(resp){
                        $('.right .right_con').html(resp);
                    }
                })
            }
            //function add employee
            function addemp(adde){
                var funv1=$(adde).data('addemp');
                
                $.ajax({
                    url: 'formsdat.php',
                    type: 'post',
                    data: {funv1:funv1},
                    beforeSend: function(){
                        $('.right .right_con').html('Loading Please wait...');
                    },
                    success: function(resp){
                        $('.right .right_con').html(resp);
                    }
                })
                
            }
            //function add category
            function addcat(addcat){
                var funv1=$(addcat).data('addcat');
                
                  $.ajax({
                    url: 'formsdat.php',
                    type: 'post',
                    data: {funv1:funv1},
                    beforeSend: function(){
                        $('.right .right_con').html('Loading Please wait...');
                    },
                    success: function(resp){
                        $('.right .right_con').html(resp);
                    }
                })
            }
            // function add product
            function addprd(adprd){
                var funv1=$(adprd).data('addprd');
                
                    $.ajax({
                    url: 'formsdat.php',
                    type: 'post',
                    data: {funv1:funv1},
                    beforeSend: function(){
                        $('.right .right_con').html('Loading Please wait...');
                    },
                    success: function(resp){
                        $('.right .right_con').html(resp);
                    }
                })
            }
            //list product
             function liprd(liprd){
                var funv1=$(liprd).data('lisprd');
                
                    $.ajax({
                    url: 'formsdat.php',
                    type: 'post',
                    data: {funv1:funv1},
                    beforeSend: function(){
                        $('.right .right_con').html('Loading Please wait...');
                    },
                    success: function(resp){
                        $('.right .right_con').html(resp);
                    }
                })
            }
            //add inventory
            function invtmg(invttg){
                var funv1=$(invttg).data('invcon');
                
                    $.ajax({
                    url: 'formsdat.php',
                    type: 'post',
                    data: {funv1:funv1},
                    beforeSend: function(){
                        $('.right .right_con').html('Loading Please wait...');
                    },
                    success: function(resp){
                        $('.right .right_con').html(resp);
                    }
                })
            }
            
            //create coupons
             function cupnmg(cpmgt){
                var funv1=$(cpmgt).data('coupns');
                
                    $.ajax({
                    url: 'formsdat.php',
                    type: 'post',
                    data: {funv1:funv1},
                    beforeSend: function(){
                        $('.right .right_con').html('Loading Please wait...');
                    },
                    success: function(resp){
                        $('.right .right_con').html(resp);
                    }
                })
            }
            //function add shop
            function addshp(addshpp){
                var funv1=$(addshpp).data('addshp');
                
                    $.ajax({
                    url: 'formsdat.php',
                    type: 'post',
                    data: {funv1:funv1},
                    beforeSend: function(){
                        $('.right .right_con').html('Loading Please wait...');
                    },
                    success: function(resp){
                        $('.right .right_con').html(resp);
                    }
                })
            }
        </script>
        <!--posting form ed-->
        
   <!--success outputs st-->
   <script>
       var suc_var='<?php echo $suotput;?>';
       if(suc_var != ''){
           if(suc_var == 'addprdsuc'){
               $('#adp').trigger('click');
           }
           if(suc_var == 'addcatsuc'){
               $('#adcat').trigger('click');
           }
           if(suc_var == 'addshopsuc'){
               $('#adshp').trigger('click');
           }
           if(suc_var == 'listproductSUB'){
               $('#lisprd').trigger('click');
           }
           if(suc_var == 'inventorySUB'){
               $('#adinvt').trigger('click');
           }
           if(suc_var == 'couponsSUB'){
               $('#adcpn').trigger('click');
           }
           if(suc_var == 'alldatalistSUB'){
               $('#aldli').trigger('click');
              
           }
           
       }
   </script>
   <!--success outputs ed-->
   
   <!--notification st-->
   <script>
       $(document).ready(function(){
           $('#notify_icon').click(function(){
                 $.ajax({
                    url: 'notification.php',
                    type: 'post',
                    data: {},
                    beforeSend: function(){
                        $('.right .right_con').html('Loading Please wait...');
                    },
                    success: function(resp){
                        $('.right .right_con').html(resp);
                    }
                })
           })
       })
   </script>
   <!--notification ed-->

        
        <!--===============================================================================================-->
	<script src="ContactFrom/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="ContactFrom/vendor/bootstrap/js/popper.js"></script>
	<script src="ContactFrom/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="ContactFrom/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="ContactFrom/vendor/tilt/tilt.jquery.min.js"></script>
    </body>
</html>
<?php 
}
else{
    header('location:https://getmlmsoftware.com/demo/cyo/admin/login');
}