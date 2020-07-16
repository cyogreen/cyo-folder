<?php 
include 'config.php';
include 'header.php';
?>
<div><span id="backshpli" onclick="bckshl(this)" data-dl="alldatalist">Back</span></div>
<div class="task_one">
                <h4>Shop Management System</h4>
            </div>
            <div class="task_tabl">
                <table class="tbl_data">
                    <th>Detail View</th>
                    <th>Shop ID</th>
                    <th>Shop Name</th>
                    <th>Contact Mobile</th>
                    <th>Action</th>
                    <?php 
                    $shpqry=mysqli_query($con,"select * from shops order by id DESC");
                    while($shpres=mysqli_fetch_assoc($shpqry)){
                        ?>
                        <tr>
                            <td class="detview" onclick="shpview(this)" data-shp_iid="<?php echo $shpres['shop_id'];?>">View</td>
                            <td><?php echo $shpres['shop_id'];?></td>
                            <td><?php echo ucwords($shpres['shop_name']);?></td>
                            <td><?php echo $shpres['contact_mobile'];?></td>
                            <td><button id="update" onclick="shpupd(this)" data-shupi="<?php echo $shpres['shop_id'];?>" class="btn btn-warning">Update</button>
                            <button id="delete" onclick="shpdel(this)" data-shdeli="<?php echo $shpres['shop_id'];?>" class="btn btn-danger">Delete</button></td>
                        </tr>
                        <?php
                    }
                    
                    ?>
                </table>
            </div>
            
<!--detailed shop view st-->
<script>
function shpview(spi){
    var shp_id=$(spi).data('shp_iid');
    
    $.ajax({
        url: 'shopdetview.php',
        type: 'post',
        data:{shp_id:shp_id},
        
        beforeSend: function(){
            $('.right .right_con').html('Loading Please wait...');
        },
        success: function(resp){
            $('.right .right_con').html(resp);
        }
    })
}
</script>
<!--detailed shop view ed-->

<!--update shop details st-->
<script>
function shpupd(sud){
    var shp_upid=$(sud).data('shupi');
    $.ajax({
        url: 'updshp.php',
        type: 'post',
        data:{shp_upid:shp_upid},
        
        beforeSend: function(){
            $('.right .right_con').html('Loading Please wait...');
        },
        success: function(resp){
            $('.right .right_con').html(resp);
        }
    })
}
</script>
<!--update shop details ed-->

<!--delete shop details st-->
<script>
function shpdel(sdl){
   var shp_deid =$(sdl).data('shdeli');
    
    $.ajax({
        url: 'delshp.php',
        type: 'post',
        data:{shp_deid:shp_deid},
        
        beforeSend: function(){
            $('.right .right_con').html('Loading Please wait...');
        },
        success: function(resp){
            $('.right .right_con').html(resp);
        }
    })
   
}
</script>
<!--delete shop details ed-->

<!--back to shop list st-->
<script>
   function bckshl(dl){
      var funv1=$(dl).data('dl');
                
                $.ajax({
                    url: 'formsdat.php',
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