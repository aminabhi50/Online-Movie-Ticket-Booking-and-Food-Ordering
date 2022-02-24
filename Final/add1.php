<?php
include 'conn.php';

$m_name = $_POST['mname'];
$director = $_POST['director'];
$producer = $_POST['producer'];
$cast = $_POST['cast'];
$desc = $_POST['desc'];
$s_no = $_POST['screenno'];
$lang = $_POST['lang'];
$t = $_POST['t'];

$name = $_FILES['mimg']['name'];
$type = $_FILES['mimg']['type'];
$tempname = $_FILES['mimg']['tmp_name'];

	if (move_uploaded_file($tempname, "./images/" . $name)) {
        $name = './images/' . $name;
		$res=$conn->prepare("INSERT INTO movieinfo(M_Name,Director,Producer,Cast,Description,Screen_No,M_Image,Language,Type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
		$res->bind_param('sssssisss',$m_name,$director,$producer,$cast,$desc,$s_no,$name,$lang,$t);
		$res->execute();
    } else {
        echo "<script type='text/javascript'>";
        echo "alert('Data entered successfully.');";
        echo "window.location='add.php';";
        echo "</script>";
    }

$g = $_POST['gold'];
$d = $_POST['diamond'];
$p = $_POST['platinum'];
$res=$conn->prepare("Insert into price(M_Name,Gold,Diamond,Platinum) VALUES (?, ?, ?, ?);");
$res->bind_param('siii',$m_name,$g,$d,$p);
$res->execute();

$t1 = $_POST['time1'];
$t2 = $_POST['time2'];
$t3 = $_POST['time3'];
$t4 = $_POST['time4'];
$res=$conn->prepare("Insert into movietime(M_Name,Time1,Time2,Time3,Time4) VALUES (?, ?, ?, ?, ?);");
$res->bind_param('siiii',$m_name,$t1,$t2,$t3,$t4);
$res->execute();

echo "<script type='text/javascript'>";
echo "alert('Data entered successfully.');";
echo "window.location='add.php';";
echo "</script>";
mysqli_close($conn);

?>