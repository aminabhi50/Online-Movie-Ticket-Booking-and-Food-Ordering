<?php
session_start();
echo "<script type='text/javascript'>";
session_destroy();
echo "alert('You have successfully logged out.');";
echo "window.location='login.html';";
echo "</script>";
exit;
?>