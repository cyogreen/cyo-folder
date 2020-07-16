<head>
    <style>
        table{
            width: 100%;
        }
        tr,th,td{
            border: 1px solid grey;
        }
        table th{
            background-color: green;
            color: white;
            text-align: center;
        }
        #btnExport{
    float: right;
    /*margin: 15px;*/
    /*padding: 10px;*/
    background-color: yellow;
    color: black;
        }
    </style>
</head>
<div><span id="backshpli" onclick="bckshl()" data-dl="alldatalist">Back</span></div>
<?php 
include 'config.php';
include 'header.php';
?>
<?php 
$inv_ord=$_POST['inv_ord'];


$qry=mysqli_query($con,"select * from sales_rept where order_id='$inv_ord'");
$qryres=mysqli_fetch_assoc($qry);
$shp_id=$qryres['shop_id'];


$prd_id=$qryres['prd_id'];
$prd_idexp=explode(',',$prd_id);
$prdcont=count($prd_idexp)-1;

$ord_qty=$qryres['order_qty'];
$ord_qtyexp=explode(',',$ord_qty);

$orders_price=$qryres['orders_amnt'];
$orders_exp=explode(',',$orders_price);

$amnt=$qryres['amount'];
$ord_date=$qryres['date'];
$ord_time=$qryres['time'];


//selection of shop 
$qry2=mysqli_query($con,"select * from shops where shop_id='$shp_id'");
$qryres2=mysqli_fetch_assoc($qry2);
$shp_nme=$qryres2['shop_name'];
$shp_mobi=$qryres2['contact_mobile'];
$addr=$qryres2['address'];
$gst=$qryres2['shop_gst'];
$shp_pin=$qryres2['shop_pincode'];

?>
<input type="button" id="btnExport" value="Print" onclick="Export()" />
<div class="inv_bx" id="tblCustomers">
    <div class="inv_img"><img src="images/formocart.jpg">
    <span>Order ID:&nbsp;<?php echo $inv_ord;?></span>
    <span>Order Date:&nbsp;<?php echo $ord_date;?>&nbsp;<?php echo $ord_time;?></span>
    </div>
    <br>
    <div class="addr_bx">
        <div class="fradr">
            From<br>
            Farmocart Pvt Ltd.</br>
            No. 27-27/A,Magadi main road,Vrishabhavathi Nagar<br>
            Kamakshipalya,Bangalore,Karnataka-560079.<br>
            GST NO:&nbsp;29AAECF0391G1ZN
        </div>
        <div class="fradr">
            To<br>
            Shop Name:&nbsp;<?php echo $shp_nme;?><br>
            Shop Mobile:&nbsp;<?php echo $shp_mobi;?><br>
            Address:&nbsp;<?php echo $addr;?><br>
            Pincode:&nbsp;<?php echo $shp_pin;?><br>
            GST NO:&nbsp;<?php echo $gst;?><br>
        </div>
    </div>
    <br>
    <div class="prd_tbl">
        <table>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price Per KG</th>
            <th>Total Price</th>
            <?php
            $i=0;
            for($i=0; $i < $prdcont; $i++){
                $prdid_inv=$prd_idexp[$i];
                
                //selection of prd name
                $invsel=mysqli_query($con,"select * from products_lists where prd_id='$prdid_inv'");
                $invselres=mysqli_fetch_assoc($invsel);
                $prd_nme[$i]=$invselres['prd_name'];
                ?>
                <tr>
                    <td><?php echo $prd_nme[$i];?></td>
                    <td><?php echo $ord_qtyexp[$i];?></td>
                    <td><?php echo $orders_exp[$i];?></td>
                    <td><?php echo ($ord_qtyexp[$i] * $orders_exp[$i]);?></td>
                </tr>
                <?php
            }
            ?>
        </table>
        <div class="totbil">Total Bill:&nbsp;<?php echo $amnt;?></div>
    </div>
    <div class="thnk">Thank You.</div>
</div>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
    <script type="text/javascript">
        function Export() {
            html2canvas(document.getElementById('tblCustomers'), {
                onrendered: function (canvas) {
                    var data = canvas.toDataURL();
                    var docDefinition = {
                        content: [{
                            image: data,
                            width: 530
                        }]
                    };
                    pdfMake.createPdf(docDefinition).download("<?php echo $inv_ord;?>");
                }
            });
        }
    </script>
    
    <!--back to shop list st-->
<script>
   function bckshl(){
        $.ajax({
        url: 'saleslist.php',
        type: 'post',
        data:{},
        
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