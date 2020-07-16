<?php 
include 'config.php';
include 'header.php';
?>
<!--back button-->
<div><span id="backshpli" onclick="bckshl(this)">Back</span></div>

<!--selection of particular shop-->
<?php 
$shp_id=$_POST['shp_id'];

$shpvqr=mysqli_query($con,"select * from shops where shop_id='$shp_id'");
$shpvres=mysqli_fetch_assoc($shpvqr);
?>
<!--shop complete view-->
<div class="shop_bx">
    <div class="shp_tile"><?php echo strtoupper($shpvres['shop_name']);?></div>
    <div class="shp_imgs">
        <img src="<?php echo $shpvres['shop_frntimg'];?>">
        <div class="shp_imgti">Shop Front Photo</div>
        </div>
    <div class="shp_imgs">
        <img src="<?php echo $shpvres['shop_insimg'];?>">
        <div class="shp_imgti">Shop Inside Photo</div>
        </div>
    <div class="shp_imgs">
        <img src="<?php echo $shpvres['shop_ownimg'];?>">
        <div class="shp_imgti">Shop Owner Photo</div>
        </div>
    <div class="shp_tblbx">
    <table class="shp_tbl">
        <tr class="shp_tr">
            <td class="shp_tdt">Shop ID</td>
            <td class="shp_tda"><?php echo $shpvres['shop_id'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Contact Mobile</td>
            <td class="shp_tda"><?php echo $shpvres['contact_mobile'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Alternative Mobile</td>
            <td class="shp_tda"><?php echo $shpvres['alter_mobile'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Email</td>
            <td class="shp_tda"><?php echo $shpvres['shop_email'];?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Address</td>
            <td class="shp_tda"><?php echo ucwords($shpvres['address']);?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Area</td>
            <td class="shp_tda"><?php echo ucfirst($shpvres['shop_area']);?></td>
        </tr>
        <tr class="shp_tr">
            <td class="shp_tdt">Pin Code</td>
            <td class="shp_tda"><?php echo $shpvres['shop_pincode'];?></td>
        </tr>
         <tr class="shp_tr">
            <td class="shp_tdt">GST No</td>
            <td class="shp_tda"><?php echo $shpvres['shop_gst'];?></td>
        </tr>
         <tr class="shp_tr">
            <td class="shp_tdt">Status</td>
            <td class="shp_tda"><?php echo ucfirst($shpvres['status']);?></td>
        </tr>
         <tr class="shp_tr">
            <td class="shp_tdt">Registration Date</td>
            <td class="shp_tda"><?php echo $shpvres['date'];?></td>
        </tr>
         <tr class="shp_tr">
            <td class="shp_tdt">Created By Employee</td>
            <td class="shp_tda"><?php echo $shpvres['createdby_emp'];?></td>
        </tr>
    </table>
    </div>
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