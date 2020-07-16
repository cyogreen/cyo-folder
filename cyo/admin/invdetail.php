<?php 
include 'config.php';
include 'header.php';
?>

<!--back button-->
<div><span id="backshpli" onclick="bckshl(this)">Back</span></div>
<div class="task_one">
                <h4>Inventory Details</h4>
            </div>
<?php 
$inv_pid=$_POST['inv_pid'];
$inv_cat=$_POST['inv_cat'];

//select of product
$invprdsel=mysqli_query($con,"select * from invt_mang where prd_id='$inv_pid'");
$invprdres=mysqli_fetch_assoc($invprdsel);
$inv_catid=$invprdres['cat_id'];

//selection of category id
$invcatsel=mysqli_query($con,"select * from categories where cat_id='$inv_catid'");
$invcatres=mysqli_fetch_assoc($invcatsel);
$invcatnme=$invcatres['cat_name'];
?>
<!--shop complete view-->
<div class="shop_bx">
    <div class="shp_tile"><?php echo strtoupper($invprdres['prd_name']);?></div>
    <div class="shp_imgs">
        <img src="<?php echo $invprdres['prd_img'];?>">
        <div class="shp_imgti">Product Photo</div>
        </div>
        
    <div class="shp_tblbx">
    <table class="shp_tbl">
        <tr class="shp_tr">
            <td class="shp_tdt">Category Name</td>
            <td class="shp_tda"><?php echo ucwords($invcatnme);?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Product Name</td>
            <td class="shp_tda"><?php echo ucwords($invprdres['prd_name']);?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Available Quantity</td>
            <td class="shp_tda"><?php echo $invprdres['avail_qty'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Recently Updated Quantity</td>
            <td class="shp_tda"><?php echo $invprdres['recup_qty'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Shortage Quantity</td>
            <td class="shp_tda"><?php echo $invprdres['inv_shrtg'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Updation Date</td>
            <td class="shp_tda"><?php echo $invprdres['rect_up'];?></td>
        </tr>
    </table>
    </div>
    <div class="ad-invm"><button  onclick="mreinv(this)" data-mrin="<?php echo $invprdres['prd_id'];?>" class="bn btn-success">Add Inventory</button></div>
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

<!--add more inventory st-->
<script>
   function mreinv(rinv){
       var minvt=$(rinv).data('mrin');
        $.ajax({
        url: 'moreinv.php',
        type: 'post',
        data:{minvt:minvt},
        
        beforeSend: function(){
            $('.right .right_con').html('Loading Please wait...');
        },
        success: function(resp){
            $('.right .right_con').html(resp);
        }
    })
   } 
</script>
<!--add more inventory ed-->