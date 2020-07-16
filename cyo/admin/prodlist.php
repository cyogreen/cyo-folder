<div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/sales.png" alt="IMG">
			</div>
			<form action="addproduct.php" id="addproduct" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					List Product
				</span>
				
					<div class="wrap-input1 validate-input" data-validate = "category is required">
					<select class="form-control" id="prdcat" name="prdcat" required>
					    <?php 
					    $prdcatqr=mysqli_query($con,"select * from categories");
					    $prdcnt=mysqli_num_rows($prdcatqr);
					    ?>
					    <option>--Select Category--</option>
					    <?php
					    while($prdcarqres=mysqli_fetch_assoc($prdcatqr)){
					        ?>
					        <option value="<?php echo $prdcarqres['cat_id'];?>"><?php echo ucfirst($prdcarqres['cat_name']);?></option>
					        <?php
					    }
					    
					    ?>
                            </select>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "product image is required">
					<input class="input1" type="file" name="prdimg" id="prdimg" capture style="display:none"><img id="prdpsel" src="images/sales.png" style="width:50px">&nbsp;
					<span>Select Product Photo</span>
					<div id="pstPH" style="width:100%"></div>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "product name is required">
					<input class="input1" type="text" name="prdname" id="prdname" placeholder="Product Name" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "status is required">
				    <span style="font-weight:700">Select Status of the Product</span><br>
					<input type="radio" name="status" id="status" value="Active" required>Active
					<input type="radio" name="status" id="status" value="In Active" required>In Active
					<span class="shadow-input1"></span>
				</div>
				
			    <div id="prd_sub" class="container-contact1-form-btn">
					<button name="prdsubmit" id="prdsubmit" class="contact1-form-btn">
						<span>
							List Product
							<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
						</span>
					</button>
					<div class="out_suc"></div>
				</div>
			</form>
					
		</div>