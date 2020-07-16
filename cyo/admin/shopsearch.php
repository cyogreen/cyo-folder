<?php 
include 'config.php';
include 'header.php';
?>

<?php 
$shp_ter=$_POST['shp_ter'];

$serqry=mysqli_query($con,"select * from shops where shop_name like '%$shp_ter%'");
while($serres=mysqli_fetch_assoc($serqry)){
$shop_id=$serres['shop_id'];
    ?>
    <div onclick="shpidd(this)" data-shppr="<?php echo $shop_id;?>" data-prnm="<?php echo $serres['shop_name']?>"><?php echo ucfirst($serres['shop_name']);?></div>
    <?php
    $selqty=mysqli_query($con,"select * from shop_coupons where shop_id='$shop_id'");
    $selres=mysqli_fetch_assoc($selqty);
    $sale_val=$selres['sale_value'];
    $disc_val=$selres['discount'];
    $cpn_cde=$selres['coupon_code'];
    
    
    
}
?>

<script>
    function shpidd(selpr){
        var shp_name=$(selpr).data('prnm');
        var shp_hid=$(selpr).data('shppr');
        var sal_val='<?php echo $sale_val;?>';
        var disc_val='<?php echo $disc_val?>';
        var cpn_cde='<?php echo $cpn_cde;?>';
        
        $('#shpn').val(shp_name);
        $('#shprid').val(shp_hid);
        $('#shpsearchres').hide();
        $('#saleval').val(sal_val);
        $('#discamnt').val(disc_val);
        $('#cpncde').val(cpn_cde);
        
    }
</script>