<?php 
include 'config.php';
$sel_form=$_POST['funv1'];
if($sel_form == 'addemployee'){
    ?>
    <div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/add.png" alt="IMG">
			</div>
			<form method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					Add Employee
				</span>

				<div class="wrap-input1 validate-input" data-validate = "Name is required">
					<input class="input1" type="text" name="ename" id="ename" placeholder="Employee name">
					<span class="shadow-input1"></span>
				</div>

				<div class="wrap-input1 validate-input" data-validate = "Mobile is required">
					<input class="input1" type="text" name="emobile" id="emobile" placeholder="Employee mobile">
					<span class="shadow-input1"></span>
				</div>

				<div class="wrap-input1 validate-input" data-validate = "email is required">
					<input class="input1" type="text" name="email" id="email" placeholder="Employee email">
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "bank is required">
					<input class="input1" type="text" name="bname" id="bname" placeholder="Bank name">
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "account number is required">
					<input class="input1" type="text" name="acno" id="acno" placeholder="Account number">
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "ifsc is required">
					<input class="input1" type="text" name="ifsc" id="ifsc" placeholder="IFSC code">
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "role is required">
					<select class="form-control" id="erole" name="erole">
					         <option>--Select Role--</option>
                            <option>Admin</option>
                            <option>Sub-Admin</option>
                            <option>Sales Executive</option>
                            <option>Business Partner</option>
                            <option>Marketing Executive</option>
                            </select>
					<span class="shadow-input1"></span>
				</div>
				
			</form>
					<div id="em_sub" class="container-contact1-form-btn">
					<button name="esubmit" id="esubmit" class="contact1-form-btn">
						<span>
							Add Employee
							<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
						</span>
					</button>
					<div class="out_suc"></div>
				</div>
		</div>
    <?php
}
elseif($sel_form == 'addcategory'){
    ?>
    <div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/listings.png" alt="IMG">
			</div>
			<form action="addcategorysub.php" id="addcategorysub" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					Add Category
				</span>

				<div class="wrap-input1 validate-input" data-validate = "category name is required">
					<input class="input1" type="text" name="catname" id="catname" placeholder="Category name">
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "category name is required">
				    <label for="catimg">Category image:</label>
					<input class="input1" type="file" name="catimg" id="catimg">
					<span class="shadow-input1"></span>
				</div>
			
			<div id="cat_sub" class="container-contact1-form-btn">
					<button name="catsubmit" id="catsubmit" class="contact1-form-btn">
						<span>
							Add Category
							<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
						</span>
					</button>
					<div class="out_suc"></div>
				</div>
			</form>
					
		</div>
    <?php
}
elseif($sel_form == 'inventory'){
    ?>
    <div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/inventory.png" alt="IMG">
			</div>
			<form action="invtup.php" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					Add Inventory
				</span>
				
				<div class="wrap-input1 validate-input" data-validate = "search is required">
				<lable>Select Product:</lable>
					<input class="input1" type="search" name="sprn" id="sprn" placeholder="Search By Product Name" value="" autocomplete="nope" required>
					<span class="shadow-input1"></span>
				</div>
				<div id="searchres"></div>
				
				<div class="wrap-input1 validate-input" data-validate = "Prev qty required">
				    <lable>Current Available Quantity:</lable>
					<input class="input1" type="text" name="prevqty" id="prevqty" placeholder="Available Quantity" value="" autocomplete="nope" disabled>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "new qty required">
				    <lable>Add New Quantity:</lable>
					<input class="input1" type="text" name="newqty" id="newqty" placeholder="New Quantity" value="" autocomplete="nope">
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "shortage qty required">
				    <lable>Shortage Quantity: (gives an alert)</lable>
					<input class="input1" type="text" name="shrgqty" id="shrgqty" placeholder="Shortage Quantity" value="" autocomplete="nope">
					<span class="shadow-input1"></span>
				</div>
				
				<input type="hidden" name="hprdid"id="hprdid" value="">
			<div id="inv_sub" class="container-contact1-form-btn">
					<button name="invsub" id="invsub" class="contact1-form-btn">
						<span>
							Add Inventory
							<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
						</span>
					</button>
					<div class="out_suc"></div>
				</div>
			</form>
					
		</div>
    <?php
}
elseif($sel_form == 'coupons'){
    ?>
    <div><span id="crtcpn">Coupon Lists</span></div>
    <div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/coupon.png" alt="IMG">
			</div>
			<form action="crcoupn.php" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					Create Coupon
				</span>
				
				<div class="wrap-input1 validate-input" data-validate = "search is required">
				<lable>Select Shop:</lable>
					<input class="input1" type="search" name="shpn" id="shpn" placeholder="Search By Shop Name" value="" autocomplete="nope" required>
					<span class="shadow-input1"></span>
				</div>
				<div id="shpsearchres"></div>
				
				<div class="wrap-input1 validate-input" data-validate = "Sale Value required">
				    <lable>Sale Value Amount:</lable>
					<input class="input1" type="text" name="saleval" id="saleval" placeholder="Sale Value Amount" value="" autocomplete="nope" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "Discount Amount required">
				    <lable>Discount Amount:</lable>
					<input class="input1" type="text" name="discamnt" id="discamnt" placeholder="Discount Amount" value="" autocomplete="nope" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "Coupon Code required">
				    <lable>Coupon Code:</lable>
					<input class="input1" type="text" name="cpncde" id="cpncde" placeholder="Coupon Code" value="" autocomplete="nope" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "status is required">
				    <span style="font-weight:700">Select Status of Coupon</span><br>
					<input type="radio" name="cstatus" id="cstatus" value="Active" required>Active
					<input type="radio" name="cstatus" id="cstatus" value="In Active" required>In Active
					<span class="shadow-input1"></span>
				</div>
				
				<input type="hidden" name="shprid"id="shprid" value="">
			<div id="add_cpn" class="container-contact1-form-btn">
					<button name="crea_cpn" id="crea_cpn" class="contact1-form-btn">
						<span>
							Create Coupon
							<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
						</span>
					</button>
					<div class="out_suc"></div>
				</div>
			</form>
					
		</div>
    <?php
}
elseif($sel_form == 'alldatalist'){
    ?>
     <div class="task_list">
                <div id="emply" class="task_block">
                <img src="images/add.png">
                <div>Employee Mng</div>
                </div>
                
                <div id="shoply" class="task_block">
                <img src="images/addshop.png">
                <div>Shop Mng</div>
                </div>
                
                <div id="salsm" class="task_block">
                <img src="images/sale.png">
                <div>Sales Mng</div>
                </div>
                
                 <div id="invms" class="task_block">
                <img src="images/inventory.png">
                <div>Inventory Mng</div>
                </div>
                
                <div id="prdmnsy" class="task_block">
                <img src="images/sales.png">
                <div>Product Mng</div>
                </div>
                
                <div id="cpnmng" class="task_block">
                <img src="images/coupon.png">
                <div>Coupons Mng</div>
                </div>
                
                <div id="liprdmnsy" class="task_block">
                <img src="images/checklist.png">
                <div>List Product Mng</div>
                </div>
                
                <div id="prdcmny" class="task_block">
                <img src="images/commission.png">
                <div>Product Commission Mng</div>
                </div>
                
                <div id="erncmny" class="task_block">
                <img src="images/income.png">
                <div>Earned Commission Mng</div>
                </div>
            </div>
            

    <?php
    
}
elseif($sel_form == 'addproduct'){
    ?>
    <div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/sales.png" alt="IMG">
			</div>
			<form action="addproduct.php" id="addproduct" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					Add Product
				</span>
				
					<div class="wrap-input1 validate-input" data-validate = "category is required">
				<!--	<select class="form-control" id="prdcat" name="prdcat" required>
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
                            </select>-->
					<span class="shadow-input1"></span>
				</div>
				
				<!--<div class="wrap-input1 validate-input" data-validate = "product image is required">
					<input class="input1" type="file" name="prdimg" id="prdimg" capture style="display:none"><img id="prdpsel" src="images/sales.png" style="width:50px">&nbsp;
					<span>Select Product Photo</span>
					<div id="pstPH" style="width:100%"></div>
					<span class="shadow-input1"></span>
				</div>-->
				
				<!--<div class="wrap-input1 validate-input" data-validate = "product name is required">
					<input class="input1" type="text" name="prdname" id="prdname" placeholder="Product Name" required>
					<span class="shadow-input1"></span>
				</div>-->
				<div class="wrap-input1 validate-input" data-validate = "search is required">
					<input class="input1" type="search" name="sprn" id="sprn" placeholder="Search By Product Name" value="" required>
					<span class="shadow-input1"></span>
				</div>
				<div id="searchres"></div>
				
				<div class="wrap-input1 validate-input" data-validate = "min qty is required">
					<input class="input1" type="text" name="minqty" id="minqty" placeholder="Minimum Quantity" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "max qty is required">
					<input class="input1" type="text" name="maxqty" id="maxqty" placeholder="Maximum Quantity" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "sale price is required">
					<input class="input1" type="text" name="slepric" id="slepric" placeholder="Sale Price" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "offer price is required">
					<input class="input1" type="text" name="offrpric" id="offrpric" placeholder="Offer Price" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "status is required">
				    <span style="font-weight:700">Select Status of the Product</span><br>
					<input type="radio" name="status" id="status" value="Active" required>Active
					<input type="radio" name="status" id="status" value="In Active" required>In Active
					<span class="shadow-input1"></span>
				</div>
				<input type="hidden" name="hprdid" id="hprdid" value="">
			    <div id="prd_sub" class="container-contact1-form-btn">
					<button name="prdsubmit" id="prdsubmit" class="contact1-form-btn">
						<span>
							Add Product
							<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
						</span>
					</button>
					<div class="out_suc"></div>
				</div>
			</form>
					
		</div>
    <?php
}
elseif($sel_form == 'listproduct'){
    ?>
    <div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/checklist.png" alt="IMG">
			</div>
			<form action="listprdsub.php" id="addproduct" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
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
					<input class="input1" type="file" name="prdimg" id="prdimg" capture style="display:none"><img id="prdpsel" src="images/checklist.png" style="width:50px">&nbsp;
					<span>Select Product Photo</span>
					<div id="pstPH" style="width:100%"></div>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "product name is required">
					<input class="input1" type="text" name="prdname" id="prdname" placeholder="Product Name" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "qty type is required">
					<select class="form-control" id="qtytype" name="qtytype" required>
					    <?php 
					    $qtyt_qry=mysqli_query($con,"select * from qty_typestbla");
					    $qtyt_qrycnt=mysqli_num_rows($qtyt_qry);
					    ?>
					    <option>--Select Quantity type--</option>
					    <?php
					    while($qtyt_qryres=mysqli_fetch_assoc($qtyt_qry)){
					        ?>
					        <option value="<?php echo $qtyt_qryres['qty_type'];?>"><?php echo $qtyt_qryres['qty_type'];?></option>
					        <?php
					    }
					    
					    ?>
                            </select>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "prd commi is required">
				    <lable>Product Commission</lable>
					<input class="input1" type="text" name="prdcmsn" id="prdcmsn" placeholder="Product Commission" required>
					<span class="shadow-input1"></span>
				</div>
				
			<!--	<div class="wrap-input1 validate-input" data-validate = "status is required">
				    <span style="font-weight:700">Select Status of the Product</span><br>
					<input type="radio" name="status" id="status" value="Active" required>Active
					<input type="radio" name="status" id="status" value="In Active" required>In Active
					<span class="shadow-input1"></span>
				</div>-->
				
			    <div id="listp_sub" class="container-contact1-form-btn">
					<button name="lisprdd" id="lisprdd" class="contact1-form-btn">
						<span>
							List Product
							<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
						</span>
					</button>
					<div class="out_suc"></div>
				</div>
			</form>
					
		</div>
    <?php
}
elseif($sel_form == 'addshop'){
 ?>
  <div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="images/addshop.png" alt="IMG">
			</div>
			<form action="addshop.php" method="post" enctype="multipart/form-data" class="contact1-form validate-form">
				<span class="contact1-form-title">
					Add Shop
				</span>

				<div class="wrap-input1 validate-input" data-validate = "shop name is required">
					<input class="input1" type="text" name="shpnme" id="shpnme" placeholder="Shop name" required>
					<span class="shadow-input1"></span>
				</div>

				<div class="wrap-input1 validate-input" data-validate = "contact number is required">
					<input class="input1" type="text" name="smobile" id="smobile" placeholder="Contact mobile" required>
					<span class="shadow-input1"></span>
				</div>

				<div class="wrap-input1 validate-input" data-validate = "Altername mobileis required">
					<input class="input1" type="text" name="amobile" id="amobile" placeholder="Alternate mobile" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "email is required">
					<input class="input1" type="text" name="semail" id="semail" placeholder="Shop email" required>
					<span class="shadow-input1"></span>
				</div>
			
				<div class="wrap-input1 validate-input" data-validate = "address is required">
					<input class="input1" type="text" name="saddrs" id="saddrs" placeholder="Address" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "Area is required">
					<input class="input1" type="text" name="sarea" id="sarea" placeholder="Shop area" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "pincode is required">
					<input class="input1" type="text" name="spincde" id="spincde" placeholder="Shop pincode" required>
					<span class="shadow-input1"></span>
				</div>
				
				<div class="wrap-input1 validate-input" data-validate = "gst is required" required>
					<input class="input1" type="text" name="sgst" id="sgst" placeholder="Shop GST">
					<span class="shadow-input1"></span>
				</div>
				
				<!--shop photos st-->
				<div class="wrap-input1 validate-input" data-validate = "shop frnt img is required">
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
				</div>
				<!--shop photos ed-->
				
		       	<div id="adsp_sub" class="container-contact1-form-btn">
					<button name="adshpp" id="adshpp" class="contact1-form-btn">
						<span>
							Add Shop
							<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
						</span>
					</button>
					<div class="out_suc"></div>
				</div>	
				
			</form>
					
		</div>
 <?php
}

?>


<!--add employee st-->
        <script>
            $(document).ready(function(){
                $('#em_sub').click(function(){
                    var emp_name=$('#ename').val();
                    var emp_mobile=$('#emobile').val();
                    var emp_email=$('#email').val();
                    var emp_bank=$('#bname').val();
                    var emp_accn=$('#acno').val();
                    var emp_ifsc=$('#ifsc').val();
                    var emp_role=$('#erole').val();
                    
                    if(emp_name != '' && emp_mobile != '' && emp_email != '' && emp_bank != '' && emp_accn !='' && emp_ifsc != '' && emp_role != ''){
                        
                        function su_clo(){
                            setTimeout(function(){
                                $('#em_sub .out_suc').css('visibility','hidden');
                            },3000);
                        }
                        
                        $.ajax({
                            url: 'addemployeesub.php',
                            type: 'post',
                            data:{emp_name:emp_name,emp_mobile:emp_mobile,emp_email:emp_email,emp_bank:emp_bank,emp_accn:emp_accn,emp_ifsc:emp_ifsc,emp_role:emp_role},
                        
                            beforeSend: function(){
                                $('#em_sub .out_suc').html('Please wait...');
                            },
                            success: function(resp){
                                $('#em_sub .out_suc').html(resp);
                                $('.input1').val('');
                                su_clo();
                                $('#em_sub .out_suc').css('visibility','visible');
                                
                            }
                        })
                    }
                    else{
                        alert('Please fill all required fields');
                    }
                })
            })
        </script>
<!--add employee ed-->

<script>
$(document).ready(function(){
    //employee management sys
    $('#emply').click(function(){
        
           $.ajax({
                            url: 'employeelist.php',
                            type: 'post',
                            data:{},
                        
                            beforeSend: function(){
                                $('.right .right_con').html('Loading Please wait...');
                            },
                            success: function(resp){
                                $('.right .right_con').html(resp);
                                
                                
                            }
                        })
        
    })
    
    //shop management sys
    $('#shoply').click(function(){
        
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
    })
    //sales management system
       $('#salsm').click(function(){
        
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
    })
    
    //inventory management system
      $('#invms').click(function(){
        
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
    })
    //product management system
     $('#prdmnsy').click(function(){
        
         $.ajax({
                            url: 'prdslists.php',
                            type: 'post',
                            data:{},
                        
                            beforeSend: function(){
                                $('.right .right_con').html('Loading Please wait...');
                            },
                            success: function(resp){
                                $('.right .right_con').html(resp);
                                
                                
                            }
                        })
    })
    //coupon management system
    $('#cpnmng').click(function(){
                 
                 $.ajax({
                            url: 'couponlist.php',
                            type: 'post',
                            data:{},
                        
                            beforeSend: function(){
                                $('.right .right_con').html('Loading Please wait...');
                            },
                            success: function(resp){
                                $('.right .right_con').html(resp);
                                
                                
                            }
                        })
    })
    //Listed product management
     $('#liprdmnsy').click(function(){
                 
                 $.ajax({
                            url: 'listprdmng.php',
                            type: 'post',
                            data:{},
                        
                            beforeSend: function(){
                                $('.right .right_con').html('Loading Please wait...');
                            },
                            success: function(resp){
                                $('.right .right_con').html(resp);
                                
                                
                            }
                        })
    })
    //Product Commission Management System
    $('#prdcmny').click(function(){
                 
                 $.ajax({
                            url: 'prdcommin.php',
                            type: 'post',
                            data:{},
                        
                            beforeSend: function(){
                                $('.right .right_con').html('Loading Please wait...');
                            },
                            success: function(resp){
                                $('.right .right_con').html(resp);
                                
                                
                            }
                        })
    })
    //Earned commissions management
    $('#erncmny').click(function(){
                 
                 $.ajax({
                            url: 'erncommin.php',
                            type: 'post',
                            data:{},
                        
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

<!--add product img st-->
<script>
var input = document.querySelector('#prdimg');
            input.onchange = function () {
            var files = $(input)[0].files;
            ver=files.length;
            /*alert(ver);*/
            var i = 0;
            for (i = 0; i < ver; i++) {
            /*$('#ph_tagline').attr('name','mk');*/
            var file = input.files[i];
            drawOnCanvas(file);
            displayAsImage(file);
            }
            };
            function drawOnCanvas(file) {
  var reader = new FileReader();

  reader.onload = function (e) {
    var dataURL = e.target.result,
        c = document.querySelector('canvas'),
        ctx = c.getContext('2d'),
        img = new Image();

    img.onload = function() {
      c.width = img.width;
      c.height = img.height;
      ctx.drawImage(img, 0, 0);
    };

    img.src = dataURL;
  };

  reader.readAsDataURL(file);
}

function displayAsImage(file) {
    
  var imgURL = URL.createObjectURL(file),
      img = document.createElement('img');
      main= document.getElementById('pstPH');
      
      img.setAttribute('style','border-radius:10px;width:100%');
      
      diV=document.createElement('div');
      diV.classList.add('ph-ilb');
      diV.setAttribute('id','ph-clos');
      diV.setAttribute('style','width:100%');
      
    /*  btnclo=document.createElement('button');
      btnclot=document.createTextNode('X');
      btnclo.appendChild(btnclot);*/
      /*btnclo.addEventListener("click", phclse);*/
      /*btnclo.setAttribute('onclick','bCLO(this);');*/
      /*btnclo.classList.add('close');*/
      /*btnclo.setAttribute('data-dismiss','ph-ilb');*/
      /*diV.appendChild(btnclo);*/
      diV.appendChild(img);
      
      /*closing photo onclick st*/

      /*closing photo onclick ed*/
     
      
  img.onload = function() {
    URL.revokeObjectURL(imgURL);
  };

  img.src = imgURL;
  /*main.appendChild(statuS);*/
  main.appendChild(diV);
/*  main.appendChild(img);*/
  

}

$(document).ready(function(){
        $('#prdpsel').click(function(){
            $('#prdimg').trigger('click');
        })
    
})
    
</script>
<!--add product img ed-->

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

<!--add category st-->
<script>
    /*$(document).ready(function(){*/
/*        $('#cat_sub').click(function(){
            var cat_nme=$('#catname').val();
            var cat_img=$('#catimg').file();
            */
            /*$('#addcategorysub').submit();*/
            /*$.ajax({
                url: 'addcategorysub.php',
                type: 'post',
                data:{cat_nme:cat_nme,cat_img:cat_img},
                beforeSend: function(){
                    $('#cat_sub .out_suc').html('Please wait...');
                },
                success: function(resp){
                    $('#cat_sub .out_suc').html(resp);
                }
            })*/
      /*  })
    })*/
</script>
<!--add category ed-->

<!--search by product name st-->
<script>
$(document).ready(function(){
$('#searchres').hide();
 $('#sprn').keyup(function(){
      var ser_ter=$(this).val();
      
      if(ser_ter != ''){
      $('#searchres').show();
      $.post('search.php',{ser_ter:ser_ter},function(data){
          $('#searchres').html(data);
      })
      }
      else{
          $('#searchres').hide();
          $('#searchres').html('');
      }
      
 })
})
</script>
<!--search by product name ed-->

<!--search by shop name to create coupons ed-->
<script>
$(document).ready(function(){
$('#shpsearchres').hide();
 $('#shpn').keyup(function(){
      var shp_ter=$(this).val();
      
      if(shp_ter != ''){
      $('#shpsearchres').show();
      $.post('shopsearch.php',{shp_ter:shp_ter},function(data){
          $('#shpsearchres').html(data);
      })
      }
      else{
          $('#shpsearchres').hide();
          $('#shpsearchres').html('');
      }
      
 })
})
</script>
<!--search by shop name to create coupons ed-->

<!--trigger to create coupon st-->
<script>
    $(document).ready(function(){
        $('#crtcpn').click(function(){
              $.ajax({
                            url: 'couponlist.php',
                            type: 'post',
                            data:{},
                        
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
<!--trigger to create coupon ed-->