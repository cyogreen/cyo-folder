<?php 
include 'config.php';
include 'header.php';
?>

<?php 
$prd_upid=$_POST['prd_upid'];
$prmn_upqr=mysqli_query($con,"select * from products_lists where prd_id='$prd_upid'");
$prmn_upres=mysqli_fetch_assoc($prmn_upqr);
?>
<div><span id="backshpli" onclick="bckshl(this)" data-dl="alldatalist">Back</span></div>
<div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/checklist.png" alt="IMG">
			</div>
			<form action="listprdmngup.php" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					Update Listed Product
				</span>
				
				<div class="wrap-input1 validate-input" data-validate = "product name is required">
				    <lable>Product name:</lable>
					<input class="input1" type="text" name="prdmnme" id="prdmnme" placeholder="" value="<?php echo ucfirst($prmn_upres['prd_name']);?>" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "product name is required">
				    <lable>Product Photo:</lable>
					<img style="width:100%" src="<?php echo $prmn_upres['prd_img'];?>"> 
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "product image is required">
					<input class="input1" type="file" name="prdimg" id="prdimg" capture style="display:none"><img id="prdpsel" src="images/checklist.png" style="width:50px">&nbsp;
					<span>Select New Product Photo</span>
					<div id="pstPH" style="width:100%"></div>
					<span class="shadow-input1"></span>
				</div>
				
				<input type="hidden" name="upprid" id="upprid" value="<?php echo $prmn_upres['prd_id'];?>">
			    <div id="prd_sub" class="container-contact1-form-btn">
					<button name="prdsubmit" id="prdsubmit" class="contact1-form-btn">
						<span>
							Update Listed Product
							<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
						</span>
					</button>
					<div class="out_suc"></div>
				</div>
			</form>
					
		</div>
<!--select product photo script st-->
<script> 
$("#prdimg").on('change', function () {

        if (typeof (FileReader) != "undefined") {

            var image_holder = $("#pstPH");
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
        $('#prdpsel').click(function(){
            $('#prdimg').trigger('click');
        })
    
})
</script>
<!--select product photo script ed-->

<!--back to shop list st-->
<script>
   function bckshl(dl){
      var funv1=$(dl).data('dl');
                
                $.ajax({
                    url: 'listprdmng.php',
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