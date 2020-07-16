<?php 
include 'config.php';
include 'header.php';
?>

<!--back button-->
<div><span id="backshpli" onclick="bckshl(this)">Back</span></div>
<div class="task_one">
                <h4>Order Details</h4>
            </div>
<?php 
$ord_iid=$_POST['ord_iid'];
$ord_shid=$_POST['ord_shid'];
$ord_orid=$_POST['ord_orid'];

//variables process
$ord_exp=explode(',',$ord_iid);
$ord_prcnt=count($ord_exp)-1;

//select of product
$prdqry=mysqli_query($con,"select * from products_lists where prd_id='$ord_iid'");
$prdres=mysqli_fetch_assoc($prdqry);

//select of shop
$shpqry=mysqli_query($con,"select * from shops where shop_id='$ord_shid'");
$shpres=mysqli_fetch_assoc($shpqry);
$emp_id=$shpres['createdby_emp'];

//select of order
$ordrqry=mysqli_query($con,"select * from sales_rept where order_id='$ord_orid'");
$ordrres=mysqli_fetch_assoc($ordrqry);

$mordr_amnt=$ordrres['orders_amnt'];
$odamntexp=explode(',',$mordr_amnt);

$mor_qty=$ordrres['order_qty'];
$odexp=explode(',',$mor_qty);

$mord_tamnt=$ordrres['amount'];
/*$ot_perprd=(($odexp)*($odamntexp));*/
?>
<!--shop complete view-->
<div class="shop_bx">
    <div class="shp_tile"><?php echo strtoupper($shpres['shop_name']);?></div>
    <div class="shp_imgs" style="width:100%">
        
        <?php 
        $i=0;
        for($i=0; $i < $ord_prcnt; $i++){
            $mcp=$ord_exp[$i];
            $mpsel=mysqli_query($con,"select * from products_lists where prd_id='$mcp'");
            $mpselres=mysqli_fetch_assoc($mpsel);
            $mp_img=$mpselres['prd_img'];
            $mp_prnme=$mpselres['prd_name'];
            $mul_prd;
            $mul_prd=$mp_prnme.','.$mul_prd;
            ?>
            <div style="display:inline-block;border:1px solid grey">
            <div style="width:200px;height:200px;border-radius:20px;"><img style="width:100%" src="<?php echo $mp_img;?>"></div>
            <div><?php echo ucfirst($mp_prnme);?></div>
            <div>Order Quantity:&nbsp;<?php echo $odexp[$i];?></div>
            <div>Per Quantity Price:&nbsp;<?php echo $odamntexp[$i];?></div>
            <div>Total Bill:&nbsp;<?php echo $ot_perprd=(($odexp[$i])*($odamntexp[$i]));?></div>
            </div>
            <?php
        }
        ?>
        </div>
        
    <div class="shp_tblbx">
    <table class="shp_tbl">
        <tr class="shp_tr">
            <td class="shp_tdt">Product Name's</td>
            <td class="shp_tda"><?php echo ucwords($mul_prd);?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Shop ID</td>
            <td class="shp_tda"><?php echo $shpres['shop_id'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Shop Name</td>
            <td class="shp_tda"><?php echo $shpres['shop_name'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Order ID</td>
            <td class="shp_tda"><?php echo $ordrres['order_id'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Amount</td>
            <td class="shp_tda"><?php echo $ordrres['amount'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Quantity</td>
            <td class="shp_tda"><?php echo $mor_qty;?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Amount Per Quantity</td>
            <td class="shp_tda"><?php echo $mordr_amnt;?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Total Bill</td>
            <td class="shp_tda"><?php echo $mord_tamnt;?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Order Date</td>
            <td class="shp_tda"><?php echo $ordrres['date'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Order Time</td>
            <td class="shp_tda"><?php echo $ordrres['time'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Order Status</td>
            <td class="shp_tda"><?php echo $ordrres['status'];?></td>
        </tr>
         <tr class="shp_tr">
            <td class="shp_tdt">Assigned Employee ID</td>
            <td class="shp_tda"><?php echo $emp_id;?></t$ordrresd>
        </tr>
    </table>
    </div>
</div>
<?php 
if($ordrres['status'] == 'delivered'){
    ?>
    <!--<div class="invc" onclick="crinv(this)" data-invord="<?php echo $ordrres['order_id'];?>"><span>Create Invoice</span></div>-->
    <?php
}
?>

<!--back to shop list st-->
<script>
   function bckshl(){
        $.ajax({
        url: 'saleslist.php',
        type: 'post',
        data:{},
        
        beforeSend: function(){
            $('.right .right_con').html('Loading Please wait...');
        },
        success: function(resp){
            $('.right .right_con').html(resp);
        }
    })
   } 
</script>
<!--back to shop list ed-->

<!--create invoice st-->
<script>
    function crinv(invor){
        var inv_ord=$(invor).data('invord');
       
        $.ajax({
        url: 'invoice.php',
        type: 'post',
        data:{inv_ord:inv_ord},
        
        beforeSend: function(){
            $('.right .right_con').html('Loading Please wait...');
        },
        success: function(resp){
            $('.right .right_con').html(resp);
        }
    })
   }
</script>
<!--create invoice ed-->