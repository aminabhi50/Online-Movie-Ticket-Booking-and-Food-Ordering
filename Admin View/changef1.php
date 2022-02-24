<?php
include 'conn.php';
$f_name =$_POST["fname"];
$newp = $_POST['NPrice'];
$res=$conn->prepare("UPDATE `foodinfo` set F_Price= ? WHERE f_Name = ?;");
$res->bind_param('is',$newp,$f_name);
$res->execute();

echo "<script type='text/javascript'>";
echo "alert('Data updated successfully.');";
echo "window.location='changef1.php';";
echo "</script>";

mysqli_close($conn);

?>