<?php 
include 'config.php';
include 'header.php';
?>

<!--back button-->
<div><span id="backshpli" onclick="bckshl(this)">Back</span></div>
<div class="task_one">
                <h4>Product Details</h4>
            </div>
<?php 
$prd_mnid=$_POST['prd_mnid'];

//select of product
$prmnqry=mysqli_query($con,"select * from products where prd_id='$prd_mnid'");
$prmnres=mysqli_fetch_assoc($prmnqry);
$prmn_id=$prmnres['prd_id'];


//selection of product id
$prd_lisqr=mysqli_query($con,"select * from products_lists where prd_id='$prmn_id'");
$prd_lisres=mysqli_fetch_assoc($prd_lisqr);
$prds_cat=$prd_lisres['cat_id'];

//selection of category list
$prli_cat=mysqli_query($con,"select * from categories where cat_id='$prds_cat'");
$prli_catres=mysqli_fetch_assoc($prli_cat);
?>
<!--shop complete view-->
<div class="shop_bx">
    <div class="shp_tile"><?php echo strtoupper($invprdres['prd_name']);?></div>
    <div class="shp_imgs">
        <img src="<?php echo $prd_lisres['prd_img'];?>">
        <div class="shp_imgti">Product Photo</div>
        </div>
        
    <div class="shp_tblbx">
    <table class="shp_tbl">
        <tr class="shp_tr">
            <td class="shp_tdt">Category Name</td>
            <td class="shp_tda"><?php echo ucfirst($prli_catres['cat_name']);?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Product Name</td>
            <td class="shp_tda"><?php echo ucfirst($prmnres['prd_name']);?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Minimum Quantity</td>
            <td class="shp_tda"><?php echo $prmnres['min_qty'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Maximum Quantity</td>
            <td class="shp_tda"><?php echo $prmnres['max_qty'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Sale Price</td>
            <td class="shp_tda"><?php echo $prmnres['sale_price'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Offer Price</td>
            <td class="shp_tda"><?php echo $prmnres['offer_price'];?></td>
        </tr>
            <tr class="shp_tr">
            <td class="shp_tdt">Product Status</td>
            <td class="shp_tda"><?php echo $prmnres['prd_status'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Registered Date</td>
            <td class="shp_tda"><?php echo $prmnres['date'];?></td>
        </tr>
        
    </table>
    </div>
</div>


<!--back to shop list st-->
<script>
   function bckshl(){
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
   } 
</script>
<!--back to shop list ed-->