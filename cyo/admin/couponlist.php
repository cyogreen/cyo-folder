<?php 
include 'config.php';
include 'header.php';
?>
<div style="margin-bottom:20px;"><span id="backshpli" onclick="bckshl(this)" data-dl="alldatalist">Back</span></div>
<div><span id="crtcpn">Create Coupon</span></div>
<div class="task_one">
                <h4>Coupon Management System</h4>
            </div>
            <div class="task_tabl">
                <table class="tbl_data">
                    <th>SL.No</th>
                    <th>Shop Name</th>
                    <th>Purchase Amount</th>
                    <th>Discount Amount</th>
                    <th>Coupon Code</th>
                    <th>Created Date</th>
                    <th>Action</th>
                    <?php 
                    $cpnmng=mysqli_query($con,"select * from shop_coupons order by id DESC");
                    $i=0;
                    while($cpnmngres=mysqli_fetch_assoc($cpnmng)){
                        $i++;
                        ?>
                        <tr>
                            <td><?php echo $i;?></td>
                            <td><?php echo ucwords($cpnmngres['shop_name']);?></td>
                            <td><?php echo $cpnmngres['sale_value'];?></td>
                            <td><?php echo $cpnmngres['discount'];?></td>
                            <td><?php echo $cpnmngres['coupon_code'];?></td>
                            <td><?php echo $cpnmngres['date'];?></td>
                            <td><button id="delete" onclick="cpndel(this)" data-cpnshp="<?php echo $cpnmngres['shop_id'];?>" class="btn btn-danger">Delete</button></td>
                        </tr>
                        <?php
                    }
                    
                    ?>
                </table>
            </div>
            

<!--delete shop details st-->
<script>
function cpndel(cpndell){
   var cpn_dele=$(cpndell).data('cpnshp');
    
    $.ajax({
        url: 'coupondel.php',
        type: 'post',
        data:{cpn_dele:cpn_dele},
        
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

<!--trigger to create coupon st-->
<script>
    $(document).ready(function(){
        $('#crtcpn').click(function(){
            $('#adcpn').trigger('click');
        })
    })
</script>
<!--trigger to create coupon ed-->

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