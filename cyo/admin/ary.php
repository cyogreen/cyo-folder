<?php 
$prd_id='PRD1';
$exp=explode(',',$prd_id);
$cnt=count($exp);
/*echo $cnt;*/

$ser='PRD1';
$i=0;
for($i=0; $i < $cnt; $i++){
    if($ser == $exp[$i]){
        echo $i;
    }
}
?>