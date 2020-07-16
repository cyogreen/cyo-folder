<?php 
include 'config.php';
include 'header.php';
?>

<?php 
$ser_ter=$_POST['ser_ter'];

$serqry=mysqli_query($con,"select * from products_lists where prd_name like '%$ser_ter%'");
while($serres=mysqli_fetch_assoc($serqry)){
$prds_id=$serres['prd_id'];
    ?>
    <div onclick="selprd(this)" data-hprid="<?php echo $prds_id;?>" data-prnm="<?php echo $serres['prd_name']?>"><?php echo ucfirst($serres['prd_name']);?></div>
    <?php
    $selqty=mysqli_query($con,"select * from invt_mang where prd_id='$prds_id'");
    $selres=mysqli_fetch_assoc($selqty);
    $pravl_qty=$selres['avail_qty'];
    $shrgqty=$selres['inv_shrtg'];
}
?>

<script>
    function selprd(selpr){
        var pr_name=$(selpr).data('prnm');
        var pr_hid=$(selpr).data('hprid');
        var pr_aqty='<?php echo $pravl_qty;?>';
        var shrgqty='<?php echo $shrgqty;?>';
        
        $('#sprn').val(pr_name);
        $('#hprdid').val(pr_hid);
        $('#searchres').hide();
        $('#prevqty').val(pr_aqty);
        $('#shrgqty').val(shrgqty);
    }
</script>