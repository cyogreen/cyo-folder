<?php 
include 'config.php';
include 'header.php';
?>

<!--back button-->
<div><span id="backshpli" onclick="bckshl(this)">Back</span></div>

<?php 
$ord_upid=$_POST['ord_upid'];
$orupqry=mysqli_query($con,"select * from sales_rept where order_id='$ord_upid'");
$orupres=mysqli_fetch_assoc($orupqry);
$m_prdid=$orupres['prd_id'];
$m_prdexp=explode(',',$m_prdid);
$m_prdcnt=count($m_prdexp)-1;

$m_Qty=$orupres['order_qty'];
$m_qtexp=explode(',',$m_Qty);

$m_amnt=$orupres['orders_amnt'];
$m_amntexp=explode(',',$m_amnt);
?>          

<div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/horizontal.png" alt="IMG">
			</div>
			<form action="orderupsub.php" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					Update Order Status
				</span>
				<lable>Order Staus</lable>
				<div class="wrap-input1 validate-input" data-validate = "order status is required">
					<input class="input1" type="text" name="ordstuss" id="ordstuss" value="<?php echo $orupres['status'];?>" disabled>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "status is required">
				<select class="form-control" id="ordstu" name="ordstu" required>
					    <option value="pending">Pending</option>
					    <option value="confirmed">Confirmed</option>
					    <option value="on the Way">On the Way</option>
					    <option value="delivered">Delivered</option>
                            </select>
                            </div>
                            
                <!--Delivered requess st-->
                <div style="color:red;font-weight:700">REFUND STOCK UPDATION IF</div>
                <div class="wrap-input1 validate-input" data-validate = "refund status is required">
                <?php 
                $i=0;
                for($i=0; $i < $m_prdcnt; $i++){
                    $mcp=$m_prdexp[$i];
                    $mcp_qty=$m_qtexp[$i];
                    $mcp_perqt=$m_amntexp[$i];
                    
                    /*$totamnt=($m_qtexp)*($m_amntexp);*/
                    
                    $mpsel=mysqli_query($con,"select * from products_lists where prd_id='$mcp'");
                    $mpselres=mysqli_fetch_assoc($mpsel);
                    $mp_prnme=$mpselres['prd_name'];
                    ?>
                    Product Name:&nbsp;<span style="color:red"><?php echo ucfirst($mp_prnme);?></span>&nbsp;(Refund Stock)&nbsp;
                    <div>Order Quantity:&nbsp;<?php echo $mcp_qty;?></div>
                    <div>Quantity Per Price:&nbsp;<?php echo $mcp_perqt;?></div>
                    <div>Total Bill:&nbsp;<?php echo $mcp_perqt * $mcp_qty;?></div>
                    <input class="input1" type="text" name="cv<?php echo $i;?>" id="cv<?php echo $i;?>" value="0">
                    
                    <?php
                }
                
                ?>
                <span class="shadow-input1"></span>
                </div>
                <!--<div class="wrap-input1 validate-input" data-validate = "refund status is required">
					<input class="input1" type="text" name="runstc" id="runstc" value="" placeholder="Refund Stock">
					<span class="shadow-input1"></span>
				</div>-->
				<!--Delivered requess ed-->
				
				<input type="hidden" name="rfdpc" id="rfdpc" value="<?php echo $m_prdcnt;?>">
                <input type="hidden" name="orddii" id="orddii" value="<?php echo $orupres['order_id'];?>">
		       	<div id="ordst_sub" class="container-contact1-form-btn">
					<button name="ordsta" id="ordsta" class="contact1-form-btn">
						<span>
							Update Status
							<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
						</span>
					</button>
					<div class="out_suc"></div>
				</div>	
				
			</form>
					
		</div>


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

<!--delivered requests st-->
<script>
   /* $(document).ready(function(){
        $('#ordstu').change(function(){
            var upsta=$(this).val();
            if(upsta == 'delivered'){
                //crea
            }
            
        })
    })*/
</script>
<!--delivered requests ed-->