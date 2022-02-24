<?php
session_start();
if(!isset($_SESSION['name']))
{
	header("Location: login.html");
}
?>

<!DOCTYPE html>
<html>
<head>
<title>
Food List
</title>
<link rel="stylesheet" href="style.css">
</head>
<body background="download.jpg">
<ul>
<center class="head_r" style="background-color:#333"><font size="100px" style="color:#ffffff;" >GOLD CINEMA</font></center>
  <li><a href="movie.php">MOVIE</a></li>
  <li><a href="booking.php">BOOKING</a></li>
  <li><a class="active" href="food.php">FOOD</a></li>
  <li><a href="orders.php">ORDERS</a></li>
  <li style="float:right"><a href="logout.php">LOGOUT</a></li>
</ul>
<center>
<h1 style="margin-top: 130px;"> FOOD LIST </h1>
</center>
<table>
<tr>
<th>F_ID</th>
<th>F_Name</th>
<th>F_Price</th>
<th>F_Image</th>
</tr>
<?php
$conn = mysqli_connect('localhost','root','');
$db = mysqli_select_db($conn,'adminview');
$q = "select * from foodinfo";
$r = mysqli_query($conn,$q);
if(mysqli_num_rows($r)>0)
{
	while($row = mysqli_fetch_assoc($r))
	{
		echo "<tr>";
		echo "<td>". $row["F_ID"] ."</td>";
		echo "<td>". $row["F_Name"] ."</td>";
		echo "<td>". $row["F_Price"] ."</td>";
		echo "<td><img src='". $row["F_Image"] ."'></td>";
		echo "</tr>";
	}
}
?>
</table>
</body>
</html>