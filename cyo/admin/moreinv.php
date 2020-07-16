<?php 
include 'config.php';
?>
<?php 
$minvt=$_POST['minvt'];
$sel_prd=mysqli_query($con,"select * from invt_mang where prd_id='$minvt'");
$sel_res=mysqli_fetch_assoc($sel_prd);
?>
<!--back button-->
<div><span id="backshpli" onclick="bckshl(this)">Back</span></div>
<div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/inventory.png" alt="IMG">
			</div>
			<form action="moreinvup.php" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					Update Inventory
				</span>
				
				<div class="wrap-input1 validate-input" data-validate = "search is required">
				<lable>Product Name:</lable>
					<input class="input1" type="text" name="sprn" id="sprn" value="<?php echo $sel_res['prd_name'];?>" autocomplete="nope" disabled>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "Prev qty required">
				    <lable>Current Available Quantity:</lable>
					<input class="input1" type="text" name="prevqty" id="prevqty" value="<?php echo $sel_res['avail_qty'];?>" autocomplete="nope" disabled>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "new qty required">
				    <lable>Add New Quantity:</lable>
					<input class="input1" type="text" name="newqty" id="newqty" placeholder="New Quantity" value="" autocomplete="nope" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "shortage qty required">
				    <lable>Shortage Quantity: (gives an alert)</lable>
					<input class="input1" type="text" name="shrgqty" id="shrgqty" placeholder="Shortage Quantity" value="<?php echo $sel_res['inv_shrtg'];?>" autocomplete="nope" required>
					<span class="shadow-input1"></span>
				</div>
				
				<input type="hidden" name="hprdid"id="hprdid" value="<?php echo $sel_res['prd_id'];?>">
			<div id="invup_sub" class="container-contact1-form-btn">
					<button name="invupsub" id="invupsub" class="contact1-form-btn">
						<span>
							Update Inventory
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
        url: 'inventlist.php',
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
