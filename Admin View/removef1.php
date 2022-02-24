<?php
include 'conn.php';
$f_name =$_POST["fname"];

$res=$conn->prepare("delete from  `foodinfo` WHERE F_Name = ?;");
$res->bind_param('s',$f_name);
$res->execute();

echo "<script type='text/javascript'>";
echo "alert('Data deleted successfully.');";
echo "window.location='removef.php';";
echo "</script>";

mysqli_close($conn);

?>