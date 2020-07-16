<?php 
include 'config.php';
include 'header.php';
?>
<?php 
$frdate=$_POST['frdate'];
$todate=$_POST['todate'];
$status=$_POST['status'];
/*if($status == '-Select Status-'){
    $sqry="select * from sales_rept order by id DESC";
}
else{
    $sqry="select * from sales_rept where date BETWEEN '$frdate' AND '$todate' || status='$status'";
}*/
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
                    $ordqry=mysqli_query($con,"select * from sales_rept where date BETWEEN '$frdate' AND '$todate' || status='$status'");
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