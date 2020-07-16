<?php 
include 'config.php';
include 'header.php';
?>
<div><span id="backshpli" onclick="bckshl(this)" data-dl="alldatalist">Back</span></div>
<div class="filters">
<div>
    <!--<div class="curng"><span>Date wise filter</span></div>-->
<lable>From</lable>
<input type="date" name="frdte" id="frdte">
<lable>to</lable>
<input type="date" name="todte" id="todte">&nbsp;
</div>
<div class="bx_filsta">
    <!--<div class="curng"><span>Status wise Filter</span></div>-->
    <select id="fil_sta" name="fil_sta">
        <option>-Select Status-</option>
        <option>pending</option>
        <option>confirmed</option>
        <option>on the way</option>
        <option>delivered</option>
    </select><button style="margin:5px" id="rst_sta" class="btn btn-warning">Reset</button>
</div>
</div>
<div class="task_one">
                <h4>Sales Management System</h4>
            </div>
            <div class="task_tabl">
                <table class="tbl_data">
                    <th>Detailed View</th>
                    <th>Shop ID</th>
                    <th>Order ID</th>
                    <th>Shop Name</th>
                    <th>Amount</th>
                    <th>Order Date</th>
                    <th>Order Status</th>
                    <th>Create Invoice</th>
                    <th>Actions</th>
                    <?php 
                    $ordqry=mysqli_query($con,"select * from sales_rept order by id DESC");
                    while($ordres=mysqli_fetch_assoc($ordqry)){
                        $shp_id=$ordres['shop_id'];
                        
                        //selection of shop
                        $shpsel=mysqli_query($con,"select * from shops where shop_id='$shp_id'");
                        $shpselres=mysqli_fetch_assoc($shpsel);
                        ?>
                        <tr>
                            <td class="detview" onclick="salviw(this)" data-orshid="<?php echo $ordres['shop_id'];?>" data-orrid="<?php echo $ordres['order_id'];?>" data-prd_iid="<?php echo $ordres['prd_id'];?>">View</td>
                            <td><?php echo $ordres['shop_id'];?></td>
                            <td><?php echo $ordres['order_id'];?></td>
                            <td><?php echo ucwords($shpselres['shop_name']);?></td>
                            <td><?php echo $ordres['amount'];?></td>
                            <td><?php echo $ordres['date'];?></td>
                            <td><?php echo $ordres['status'];?></td>
                            <td><button class="btn btn-success" onclick="crinv(this)" data-invord="<?php echo $ordres['order_id'];?>">Invoice</button></td>
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

<!--select date range st-->
<script>
    $(document).ready(function(){
        $('#todte').change(function(){
        var frdate=$('#frdte').val();
        var todate=$('#todte').val();
        
        $.ajax({
            url: 'saleslistd-filt.php',
            type: 'post',
            data:{frdate:frdate,todate:todate},
            
            beforeSend: function(){
                $('.task_tabl').html('Loading Please Wait');
            },
            success: function(resp){
                $('.task_tabl').html(resp);
            }
        })
            
        })
    })
</script>
<!--select date range ed-->

<!--select the status filter st-->
<script>
    $(document).ready(function(){
        $('#fil_sta').change(function(){
            var status=$(this).val();
            
            $.ajax({
            url: 'saleslistd-filt.php',
            type: 'post',
            data:{status:status},
            
            beforeSend: function(){
                $('.task_tabl').html('Loading Please Wait');
            },
            success: function(resp){
                $('.task_tabl').html(resp);
            }
        })
        })
    })
</script>
<!--select the status filter ed-->

<!--reset the status of the order st-->
<script>
    $(document).ready(function(){
        $('#rst_sta').click(function(){
            $('#fil_sta').val('heloo');
            
            $.ajax({
            url: 'saleslistfil.php',
            type: 'post',
            data:{},
            
            beforeSend: function(){
                $('.task_tabl').html('Loading Please Wait');
            },
            success: function(resp){
                $('.task_tabl').html(resp);
            }
        })
        })
    })
</script>
<!--reset the status of the order ed-->
<!--create invoice st-->
<script>
    function crinv(invor){
        var inv_ord=$(invor).data('invord');
       
        $.ajax({
        url: 'invoice.php',
        type: 'post',
        data:{inv_ord:inv_ord},
        
        beforeSend: function(){
            $('.right .right_con').html('Loading Please wait...');
        },
        success: function(resp){
            $('.right .right_con').html(resp);
        }
    })
   }
</script>
<!--create invoice ed-->