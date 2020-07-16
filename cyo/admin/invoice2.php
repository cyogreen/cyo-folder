<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<?php 
include 'config.php';
include 'header.php';
$kl='pratap';
$html='';
$html .='<div>'.$kl.'</div>';
?>
<div id="kll">ghy</div>
<button onclick="genpdf()">click</button>
<script>
    function genpdf(){
        var element = document.getElementById('kll');
        var worker=html2pdf(element);
        var doc=new jsPDF();
        doc.text(20, 20, worker);
        doc.save('tb.pdf');
    }
</script>