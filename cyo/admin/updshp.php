<?php 
include 'config.php';
include 'header.php';
?>

<?php
$shp_upid=$_POST['shp_upid'];

// selection of shop id for updation of data of shop
$shupqr=mysqli_query($con,"select * from shops where shop_id='$shp_upid'");
$shupres=mysqli_fetch_assoc($shupqr);

?>
<div><span id="backshpli" onclick="bckshl(this)">Back</span></div>
<div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/addshop.png" alt="IMG">
			</div>
			<form action="updshpsub.php" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					Update Shop
				</span>

				<div class="wrap-input1 validate-input" data-validate = "shop name is required">
					<input class="input1" type="text" name="shpnme" id="shpnme" placeholder="Shop name" value="<?php echo $shupres['shop_name'];?>" required>
					<span class="shadow-input1"></span>
				</div>

				<div class="wrap-input1 validate-input" data-validate = "contact number is required">
					<input class="input1" type="text" name="smobile" id="smobile" placeholder="Contact mobile" value="<?php echo $shupres['contact_mobile'];?>" required>
					<span class="shadow-input1"></span>
				</div>

				<div class="wrap-input1 validate-input" data-validate = "Altername mobileis required">
					<input class="input1" type="text" name="amobile" id="amobile" placeholder="Alternate mobile" value="<?php echo $shupres['alter_mobile'];?>" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "email is required">
					<input class="input1" type="text" name="semail" id="semail" placeholder="Shop email" value="<?php echo $shupres['shop_email'];?>" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "address is required">
					<input class="input1" type="text" name="saddrs" id="saddrs" placeholder="Address" value="<?php echo $shupres['address'];?>" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "Area is required">
					<input class="input1" type="text" name="sarea" id="sarea" placeholder="Shop area" value="<?php echo $shupres['shop_area'];?>" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "pincode is required">
					<input class="input1" type="text" name="spincde" id="spincde" placeholder="Shop pincode" value="<?php echo $shupres['shop_pincode'];?>" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "gst is required">
					<input class="input1" type="text" name="sgst" id="sgst" placeholder="Shop GST" value="<?php echo $shupres['shop_gst'];?>" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "prev acceptis required">
				    <lable>Current Status</lable>
					<input class="input1" type="text" name="prevacpt" id="prevacpt" placeholder="" value="<?php echo ucfirst($shupres['status']);?>" disabled>
					<span class="shadow-input1"></span>
				</div>
				
				<?php 
				$acpt=$shupres['status'];
				if($acpt != 'approved'){
				    ?>
				    <div class="wrap-input1 validate-input" data-validate = "status is required">
					<select class="form-control" id="shp_sta" name="shp_sta" required>
					    <option>--Select the Acceptance--</option>
					    <option value="approved">Approved</option>
					    <option value="rejected">Rejected</option>
					    </select>
					<span class="shadow-input1"></span>
				</div>
				    <?php
				    
				}
				?>
			
				
				<!--shop photos st-->
				<!--<div class="wrap-input1 validate-input" data-validate = "shop frnt img is required">
					<input class="input1" type="file" name="shpfrimg" id="shpfrimg" capture style="display:none"><img id="shpfrcl" src="images/addshop.png" style="width:50px">&nbsp;
					<span>Select Shop Front Photo</span>
					<div id="pstPHfri" style="width:100%"></div> 
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "shop insd img is required">
					<input class="input1" type="file" name="shpinimg" id="shpinimg" capture style="display:none"><img id="shpincl" src="images/addshop.png" style="width:50px">&nbsp;
					<span>Select Shop Inside Photo</span>
					<div id="pstPHini" style="width:100%"></div>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "shop owner img is required">
					<input class="input1" type="file" name="shpownimg" id="shpownimg" capture style="display:none"><img id="shpowncl" src="images/registration.png" style="width:50px">&nbsp;
					<span>Select Owner Photo</span>
					<div id="pstPHowni" style="width:100%"></div>
					<span class="shadow-input1"></span>
				</div>-->
				<!--shop photos ed-->
				<input type="hidden" name="shopi_id" id="shopi_id" value="<?php echo $shupres['shop_id'];?>">
				
		       	<div id="adsp_sub" class="container-contact1-form-btn">
					<button name="adshpp" id="adshpp" class="contact1-form-btn">
						<span>
							Update Shop
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
        url: 'shoplist.php',
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


<!--add shop front img st-->
<script> 
$("#shpfrimg").on('change', function () {

        if (typeof (FileReader) != "undefined") {

            var image_holder = $("#pstPHfri");
            image_holder.empty();

            var reader = new FileReader();
            reader.onload = function (e) {
                $("<img />", {
                    "src": e.target.result,
                    "class": "thumb-image"
                }).appendTo(image_holder);

            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[0]);
        } else {
            alert("This browser does not support FileReader.");
        }
    });
</script>

<script>
    $(document).ready(function(){
        $('#shpfrcl').click(function(){
            $('#shpfrimg').trigger('click');
        })
    
})
</script>
<!--add shop front img ed-->

<!--add shop inside img st-->
<script>  
$("#shpinimg").on('change', function () {

        if (typeof (FileReader) != "undefined") {

            var image_holder = $("#pstPHini");
            image_holder.empty();

            var reader = new FileReader();
            reader.onload = function (e) {
                $("<img />", {
                    "src": e.target.result,
                    "class": "thumb-image"
                }).appendTo(image_holder);

            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[0]);
        } else {
            alert("This browser does not support FileReader.");
        }
    });
</script>

<script> 
    $(document).ready(function(){
        $('#shpincl').click(function(){
            $('#shpinimg').trigger('click');
        })
    
})
</script>
<!--add shop inside img ed-->


<!--add shop owner img st--> 
<script> 
$("#shpownimg").on('change', function () {

        if (typeof (FileReader) != "undefined") {

            var image_holder = $("#pstPHowni");
            image_holder.empty();

            var reader = new FileReader();
            reader.onload = function (e) {
                $("<img />", {
                    "src": e.target.result,
                    "class": "thumb-image"
                }).appendTo(image_holder);

            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[0]);
        } else {
            alert("This browser does not support FileReader.");
        }
    });
</script>

<script>
    $(document).ready(function(){
        $('#shpowncl').click(function(){
            $('#shpownimg').trigger('click');
        })
    
})
</script>
<!--add shop owner img ed-->