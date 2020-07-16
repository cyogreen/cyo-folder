<?php 
include 'config.php';
include 'header.php';
?>
<div><span id="backshpli" onclick="bckshl(this)" data-dl="alldatalist">Back</span></div>
<div class="task_one">
                <h4>Earned Product Commissions by employees(For Employee)</h4>
            </div>
            <div class="task_tabl">
                <table class="tbl_data">
                   <th>Employee Id</th>
                   <th>Order Id</th>
                   <th>Total Order Amount</th>
                   <th>Earned Commission</th>
                   <th>Status</th>
                   <th>Action</th>
                    <?php 
                    $prdmng=mysqli_query($con,"select * from employee_payouts order by id DESC");
                    while($prdresmg=mysqli_fetch_assoc($prdmng)){
                        ?>
                        <tr>
                            <td><?php echo $prdresmg['emp_id'];?></td>
                            <td><?php echo $prdresmg['order_id'];?></td>
                            <td><?php echo $prdresmg['order_amount'];?></td>
                            <td><input type="text" name="ec<?php echo $prdresmg['order_id'];?>" id="ec<?php echo $prdresmg['order_id'];?>" value="<?php echo $prdresmg['commission_amnt'];?>"></td>
                            <td><span class="sttupd"><?php echo $prdresmg['status'];?></span></td>
                            <td class="easn<?php echo $prdresmg['order_id'];?>"><button id="update" onclick="prdmgup(this)" data-prdmup="<?php echo $prdresmg['order_id'];?>" class="btn btn-warning">Approve</button></td>
                        </tr>
                        <?php
                    }
                    
                    ?>
                </table>
            </div>
            
            
<!--update shop details st-->
<script>
function prdmgup(prgup){
    var ord_id=$(prgup).data('prdmup');
    var ear_cmnva=$('#ec'+ord_id).val();
    var st_upp=$('#st_up').val();
    
        $.ajax({
        url: 'earncmnup.php',
        type: 'post',
        data:{ord_id:ord_id,ear_cmnva:ear_cmnva,st_upp:st_upp},
        
        beforeSend: function(){
            $('.tbl_data .easn'+ord_id).html('Loading Please wait...');
        },
        success: function(resp){
            $('.tbl_data .easn'+ord_id).html(resp);
            $('.tbl_data .sttupd').text('approved');
            
        }
    })
    
    
}
</script>
<!--update shop details ed-->



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