<?php 
include 'config.php';
include 'header.php';
?>

<?php 
$prd_upid=$_POST['prd_upid'];
$prmn_upqr=mysqli_query($con,"select * from products where prd_id='$prd_upid'");
$prmn_upres=mysqli_fetch_assoc($prmn_upqr);
?>
<div><span id="backshpli" onclick="bckshl(this)" data-dl="alldatalist">Back</span></div>
<div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/sales.png" alt="IMG">
			</div>
			<form action="prdmngup.php" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					Update Product
				</span>
				
				<div class="wrap-input1 validate-input" data-validate = "product name is required">
				    <lable>Product name:</lable>
					<input class="input1" type="text" name="prdmnme" id="prdmnme" placeholder="" value="<?php echo ucfirst($prmn_upres['prd_name']);?>" required disabled>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "min qty is required">
				    <lable>Minium Quantity:</lable>
					<input class="input1" type="text" name="minqty" id="minqty" placeholder="Minimum Quantity" value="<?php echo $prmn_upres['min_qty'];?>" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "max qty is required">
				    <lable>Maximum Quantity:</lable>
					<input class="input1" type="text" name="maxqty" id="maxqty" placeholder="Maximum Quantity" value="<?php echo $prmn_upres['max_qty'];?>" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "sale price is required">
				    <lable>Sale Price:</lable>
					<input class="input1" type="text" name="slepric" id="slepric" placeholder="Sale Price" value="<?php echo $prmn_upres['sale_price'];?>" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "offer price is required">
				    <lable>Offer Price:</lable>
					<input class="input1" type="text" name="offrpric" id="offrpric" placeholder="Offer Price" value="<?php echo $prmn_upres['offer_price'];?>" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "status is required">
				    <span style="font-weight:700">Select Status of the Product</span><br>
					<input type="radio" name="status" id="status" value="Active" required>Active
					<input type="radio" name="status" id="status" value="In Active" required>In Active
					<span class="shadow-input1"></span>
				</div>
				<input type="hidden" name="upprid" id="upprid" value="<?php echo $prmn_upres['prd_id'];?>">
			    <div id="prd_sub" class="container-contact1-form-btn">
					<button name="prdsubmit" id="prdsubmit" class="contact1-form-btn">
						<span>
							Update Product
							<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
						</span>
					</button>
					<div class="out_suc"></div>
				</div>
			</form>
					
		</div>

<!--back to shop list st-->
<script>
   function bckshl(dl){
      var funv1=$(dl).data('dl');
                
                $.ajax({
                    url: 'prdslists.php',
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
<!--back to shop list ed-->