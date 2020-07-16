<?php 
include 'config.php';
include 'header.php';
?>
<div><span id="backshpli" onclick="bckshl(this)" data-dl="alldatalist">Back</span></div>
<div class="task_one">
                <h4>Notifications</h4>
            </div>
            <div class="task_tabl">
                <table class="tbl_data">
                    <th>S.No</th>
                    <th>Category Name</th>
                    <th>Product Name</th>
                    <th>Available Quantity</th>
                    <th>Inventory Shortage</th>
                    <th>Date</th>
                    <th>Notification Type</th>
                    <?php 
                    $i=0;
                    $invqry=mysqli_query($con,"select * from notifications order by id DESC");
                    while($invres=mysqli_fetch_assoc($invqry)){
                        $catt_id=$invres['cat_id'];
                        
                        //selection of category
                        $cat_sel=mysqli_query($con,"select * from categories where cat_id='$catt_id'");
                        $cat_selres=mysqli_fetch_assoc($cat_sel);
                        $catt_name=$cat_selres['cat_name'];
                        
                        $i++;
                        ?>
                        <tr>
                            <td><?php echo $i;?></td>
                            <td><?php echo ucwords($catt_name);?></td>
                            <td><?php echo ucfirst($invres['prd_name']);?></td>
                            <td style="background-color:#f95454;color:white;font-weight:700"><?php echo $invres['avail_qty'];?></td>
                            <td><?php echo $invres['inv_shrtg'];?></td>
                            <td><?php echo $invres['notify_date'];?></td>
                            <td><?php echo ucfirst($invres['type']);?></td>
                        </tr>
                        <?php
                    }
                    
                    ?>
                </table>
            </div>

<!--order detailed view st-->
<script>
    function invde(intt){
        var inv_pid=$(intt).data('invprd');
        var inv_cat=$(intt).data('invcat');
    
        $.ajax({
        url: 'invdetail.php',
        type: 'post',
        data:{inv_pid:inv_pid,inv_cat:inv_cat},
        
        beforeSend: function(){
            $('.right .right_con').html('Loading Please wait...');
        },
        success: function(resp){
            $('.right .right_con').html(resp);
        }
    })
    }
</script>
<!--order detailed view ed-->

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