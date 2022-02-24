<?php
include 'conn.php';
$m_name =$_POST["mname"];

$res=$conn->prepare("delete from  `movieinfo` WHERE M_Name = ?;");
$res->bind_param('s',$m_name);
$res->execute();

$res=$conn->prepare("delete from  `movietime` WHERE M_Name = ?;");
$res->bind_param('s',$m_name);
$res->execute();

$res=$conn->prepare("delete from  `price` WHERE M_Name = ?;");
$res->bind_param('s',$m_name);
$res->execute();

echo "<script type='text/javascript'>";
echo "alert('Data deleted successfully.');";
echo "window.location='deletem.php';";
echo "</script>";

mysqli_close($conn);

?>