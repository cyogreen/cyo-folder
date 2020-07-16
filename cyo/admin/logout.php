<?php 
session_start();
if(session_destroy()){
    header('location:https://farmocart.com/admin');
}


?>