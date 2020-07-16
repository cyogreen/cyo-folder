<?php 
$data = json_decode(file_get_contents("php://input"));
$name=$data->name;

if($name == 'pratap'){
    ?>
    <script>
        var obj={"msg":"success"};
        JSON.stringify(obj);
    </script>
    <?php
}
?>