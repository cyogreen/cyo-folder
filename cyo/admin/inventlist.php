<?php 
include 'config.php';
include 'header.php';
?>
<div><span id="backshpli" onclick="bckshl(this)" data-dl="alldatalist">Back</span></div>
<div class="task_one">
                <h4>Inventoty Management System</h4>
            </div>
            <div class="task_tabl">
                <table class="tbl_data">
                    <th>Detailed View</th>
                    <th>Product Name</th>
                    <th>Available Quantity</th>
                    <th>Recently updated Quantity</th>
                    <th>Date</th>
                    <?php 
                    $invqry=mysqli_query($con,"select * from invt_mang order by id DESC");
                    while($invres=mysqli_fetch_assoc($invqry)){
                        ?>
                        <tr>
                            <td class="detview" onclick="invde(this)" data-invcat="<?php echo $invres['cat_id'];?>" data-invprd="<?php echo $invres['prd_id'];?>">View</td>
                            <td><?php echo ucfirst($invres['prd_name']);?></td>
                            <td><?php echo $invres['avail_qty'];?></td>
                            <td><?php echo $invres['recup_qty'];?></td>
                            <td><?php echo $invres['rect_up'];?></td>
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