<?php
/**
 * HTML2PDF Librairy - example
 *
 * HTML => PDF convertor
 * distributed under the LGPL License
 *
 * @author      Laurent MINGUET <webmaster@html2pdf.fr>
 *
 * isset($_GET['vuehtml']) is not mandatory
 * it allow to display the result in the HTML format
 */
// get the HTML
ob_start();
// database connection here
require_once ( 'database-connection.php' );
// get the id
$id = $_GET['id'];
// Retrieve record from database
// query code here
?>
<page backleft="0mm" backtop="0mm" backright="0mm" backbottom="0mm">    
    ID: <?php echo 'Johy';?>
    <!--other html and php codes here.-->
</page>
<?php
$content = ob_get_clean();
// convert to PDF
include('pdf/tcpdf.php');

    $html2pdf = new HTML2PDF('P', array(200,300), 'en', true, 'UTF-8', array(0, 0, 0, 0));
    //$html2pdf = new HTML2PDF('P', 'A4', 'fr', true, 'UTF-8', 0);
    $html2pdf->pdf->SetDisplayMode('fullpage');
    $html2pdf->writeHTML($content);
    //$html2pdf->Output('test.pdf','D'); // force download pdf
    $html2pdf->Output('test.pdf'); // display only

    echo $e;
    exit;

?>