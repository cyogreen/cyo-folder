<?php 
include 'config.php';
include 'header.php';
?>
<div class="task_tabl">
                <table class="tbl_data">
                    <th>Detailed View</th>
                    <th>Shop ID</th>
                    <th>Order_ID</th>
                    <th>Shop Name</th>
                    <th>Amount</th>
                    <th>Order Date</th>
                    <th>Order Status</th>
                    <th>Actions</th>
                    <?php 
                    $ordqry=mysqli_query($con,"select * from sales_rept order by id DESC");
                    while($ordres=mysqli_fetch_assoc($ordqry)){
                        ?>
                        <tr>
                            <td class="detview" onclick="salviw(this)" data-orshid="<?php echo $ordres['shop_id'];?>" data-orrid="<?php echo $ordres['order_id'];?>" data-prd_iid="<?php echo $ordres['prd_id'];?>">View</td>
                            <td><?php echo $ordres['shop_id'];?></td>
                            <td><?php echo $ordres['order_id'];?></td>
                            <td><?php echo ucwords($ordres['shop_name']);?></td>
                            <td><?php echo $ordres['amount'];?></td>
                            <td><?php echo $ordres['date'];?></td>
                            <td><?php echo $ordres['status'];?></td>
                            <td><button id="update" onclick="ordupd(this)" data-orupid="<?php echo $ordres['order_id'];?>" class="btn btn-warning">Update Status</button></td>
                        </tr>
                        <?php
                    }
                    
                    ?>
                </table>
            </div>

<!--order detailed view st-->
<script>
    function salviw(salv){
        var ord_iid=$(salv).data('prd_iid');
        var ord_shid=$(salv).data('orshid');
        var ord_orid=$(salv).data('orrid');
        
        $.ajax({
        url: 'orderlist.php',
        type: 'post',
        data:{ord_iid:ord_iid,ord_shid:ord_shid,ord_orid:ord_orid},
        
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

<!--order status update st-->
<script>
    function ordupd(orsup){
        var ord_upid=$(orsup).data('orupid');
        
        $.ajax({
        url: 'orderstup.php',
        type: 'post',
        data:{ord_upid:ord_upid},
        
        beforeSend: function(){
            $('.right .right_con').html('Loading Please wait...');
        },
        success: function(resp){
            $('.right .right_con').html(resp);
        }
    })
    }
</script>
<!--order status update ed-->
