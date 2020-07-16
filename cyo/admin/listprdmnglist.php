<?php 
include 'config.php';
include 'header.php';
?>

<!--back button-->
<div><span id="backshpli" onclick="bckshl(this)">Back</span></div>
<div class="task_one">
                <h4>Listed Product Details</h4>
            </div>
<?php 
$prd_mnid=$_POST['prd_mnid'];

//selection of product id
$prd_lisqr=mysqli_query($con,"select * from products_lists where prd_id='$prd_mnid'");
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
            <td class="shp_tda"><?php echo ucfirst($prd_lisres['prd_name']);?></td>
        </tr>
         <tr class="shp_tr">
            <td class="shp_tdt">Quantity Type</td>
            <td class="shp_tda"><?php echo ucfirst($prd_lisres['qty_type']);?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Registered Date</td>
            <td class="shp_tda"><?php echo $prd_lisres['date'];?></td>
        </tr>
        
    </table>
    </div>
</div>


<!--back to shop list st-->
<script>
   function bckshl(){
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
   } 
</script>
<!--back to shop list ed-->