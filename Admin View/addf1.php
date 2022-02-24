<?php
include 'conn.php';

$f_name = $_POST['fname'];
$f_price = $_POST['Price'];

$name = $_FILES['img']['name'];
$type = $_FILES['img']['type'];
$tempname = $_FILES['img']['tmp_name'];

	if (move_uploaded_file($tempname, "./images/" . $name)) {
        $name = './images/' . $name;
		$res=$conn->prepare("INSERT INTO foodinfo(F_Name,F_Price,F_Image) VALUES (?, ?, ?);");
		$res->bind_param('sis',$f_name,$f_price,$name);
		$res->execute();
		echo "<script type='text/javascript'>";
        echo "alert('Data entered successfully.');";
        echo "window.location='addf.php';";
        echo "</script>";
	} else {
        echo "<script type='text/javascript'>";
        echo "alert('Data not entered successfully.');";
        echo "window.location='addf.php';";
        echo "</script>";
    }

mysqli_close($conn);

?>