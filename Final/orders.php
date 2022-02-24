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
Orders
</title>
<link rel="stylesheet" href="style.css">
</head>
<body background="download.jpg">
<ul>
<center class="head_r" style="background-color:#333"><font size="100px" style="color:#ffffff;" >GOLD CINEMA</font></center>
  <li><a href="movie.php">MOVIE</a></li>
  <li><a href="booking.php">BOOKING</a></li>
  <li><a href="food.php">FOOD</a></li>
  <li><a class="active" href="oreders.php">ORDERS</a></li>
  <li style="float:right"><a href="logout.php">LOGOUT</a></li>
</ul>
</body>
</html>