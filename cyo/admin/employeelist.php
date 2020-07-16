<?php 
include 'config.php';
?>

<!--back button-->
<div><span id="backshpli" onclick="bckshl(this)" data-dl="alldatalist">Back</span></div>
            <div class="task_one">
                <h4>Employee Management System</h4>
            </div>
            <div class="task_tabl">
                <table class="tbl_data">
                    <th>Employee ID</th>
                    <th>Name</th>
                    <th>Mobile</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Bank Name</th>
                    <th>Account Number</th>
                    <th>IFSC Code</th>
                    <th>Action</th>
                    <?php 
                    $qry=mysqli_query($con,"select * from employee_table order by id DESC");
                    while($qrres=mysqli_fetch_assoc($qry)){
                        ?>
                        <tr>
                            <td><?php echo $qrres['emp_id'];?></td>
                            <td><?php echo $qrres['name'];?></td>
                            <td><?php echo $qrres['mobile'];?></td>
                            <td><?php echo $qrres['email'];?></td>
                            <td><?php echo $qrres['role'];?></td>
                            <td><?php echo $qrres['bank_name'];?></td>
                            <td><?php echo $qrres['acnt_no'];?></td>
                            <td><?php echo $qrres['ifsc_code'];?></td>
                            <td><button id="update" class="btn btn-warning">Update</button><button id="delete" class="btn btn-danger">Delete</button></td>
                        </tr>
                        <?php
                    }
                    
                    ?>
                </table>
            </div>
            
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