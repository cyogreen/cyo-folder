<?php 
include 'config.php';
include 'header.php';
?>
<div><span id="backshpli" onclick="bckshl(this)" data-dl="alldatalist">Back</span></div>
<div class="task_one">
                <h4>Listed Product Commission Management System(For Employee)</h4>
            </div>
            <div class="task_tabl">
                <table class="tbl_data">
                   <th>Product Name</th>
                   <th>Commission rate (%)</th>
                   <th>Action</th>
                    <?php 
                    $prdmng=mysqli_query($con,"select * from products_lists order by id DESC");
                    while($prdresmg=mysqli_fetch_assoc($prdmng)){
                        ?>
                        <tr>
                            <td><?php echo ucwords($prdresmg['prd_name']);?></td>
                            <td><input type="text" name="vc<?php echo $prdresmg['prd_id'];?>" id="vc<?php echo $prdresmg['prd_id'];?>" value="<?php echo $prdresmg['emp_commission'];?>"></td>
                            <td class="asn<?php echo $prdresmg['prd_id'];?>"><button id="update" onclick="prdmgup(this)" data-prdmup="<?php echo $prdresmg['prd_id'];?>" class="btn btn-warning">Update</button></td>
                        </tr>
                        <?php
                    }
                    
                    ?>
                </table>
            </div>
            
            
<!--update shop details st-->
<script>
function prdmgup(prgup){
    var prd_upid=$(prgup).data('prdmup');
    var prd_cmnva=$('#vc'+prd_upid).val();
    
    $.ajax({
        url: 'prdcmnup.php',
        type: 'post',
        data:{prd_upid:prd_upid,prd_cmnva:prd_cmnva},
        
        beforeSend: function(){
            $('.tbl_data .asn'+prd_upid).html('Loading Please wait...');
        },
        success: function(resp){
            $('.tbl_data .asn'+prd_upid).html(resp);
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