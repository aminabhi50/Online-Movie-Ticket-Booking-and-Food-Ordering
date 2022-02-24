<?php
include 'conn.php';
$m_name =$_POST['mname'];
$s_no = $_POST['screenno'];
$res=$conn->prepare("UPDATE `movieinfo` set Screen_No= ? WHERE M_Name = ?;");
$res->bind_param('is',$s_no,$m_name);
$res->execute();

$g = $_POST['gold'];
$d = $_POST['diamond'];
$p = $_POST['platinum'];
$res=$conn->prepare("UPDATE `price` set Gold=?, Diamond=?, Platinum=? WHERE M_Name = ?;");
$res->bind_param('iiis',$g,$d,$p,$m_name);
$res->execute();

echo "<script type='text/javascript'>";
echo "alert('Data updated successfully.');";
echo "window.location='updatem.php';";
echo "</script>";

mysqli_close($conn);

?>