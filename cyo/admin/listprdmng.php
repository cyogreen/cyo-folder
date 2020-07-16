<?php 
include 'config.php';
include 'header.php';
?>
<div><span id="backshpli" onclick="bckshl(this)" data-dl="alldatalist">Back</span></div>
<div class="task_one">
                <h4>Listed Product Management System</h4>
            </div>
            <div class="task_tabl">
                <table class="tbl_data">
                    <th>Detail View</th>
                    <th>Product Name</th>
                    <th>Listed Date</th>
                    <th>Action</th>
                    <?php 
                    $prdmng=mysqli_query($con,"select * from products_lists order by id DESC");
                    while($prdresmg=mysqli_fetch_assoc($prdmng)){
                        ?>
                        <tr>
                            <td class="detview" onclick="prdmngg(this)" data-prmsyi="<?php echo $prdresmg['prd_id'];?>">View</td>
                            <td><?php echo ucwords($prdresmg['prd_name']);?></td>
                            <td><?php echo $prdresmg['date'];?></td>
                            <td><button id="update" onclick="prdmgup(this)" data-prdmup="<?php echo $prdresmg['prd_id'];?>" class="btn btn-warning">Update</button>
                            <button id="delete" onclick="prdmgde(this)" data-prdmde="<?php echo $prdresmg['prd_id'];?>" class="btn btn-danger">Delete</button></td>
                        </tr>
                        <?php
                    }
                    
                    ?>
                </table>
            </div>
            
<!--detailed shop view st-->
<script>
function prdmngg(prgde){
    var prd_mnid=$(prgde).data('prmsyi');
    
    $.ajax({
        url: 'listprdmnglist.php',
        type: 'post',
        data:{prd_mnid:prd_mnid},
        
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
function prdmgup(prgup){
    var prd_upid=$(prgup).data('prdmup');
    $.ajax({
        url: 'listprdup.php',
        type: 'post',
        data:{prd_upid:prd_upid},
        
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
function prdmgde(prgdel){
   var prd_delid =$(prgdel).data('prdmde');
    
    $.ajax({
        url: 'listprdmngdel.php',
        type: 'post',
        data:{prd_delid:prd_delid},
        
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