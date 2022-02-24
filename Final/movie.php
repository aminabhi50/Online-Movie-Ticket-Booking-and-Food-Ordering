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
Home
</title>
<link rel="stylesheet" href="style.css">
</head>
<body background="download.jpg">
<ul>
<center class="head_r" style="background-color:#333"><font size="100px" style="color:#ffffff;" >GOLD CINEMA</font></center>
  <li><a class="active" href="movie.php">MOVIE</a></li>
  <li><a href="booking.php">BOOKING</a></li>
  <li><a href="food.php">FOOD</a></li>
  <li><a href="orders.php">ORDERS</a></li>
  <li style="float:right"><a href="logout.php">LOGOUT</a></li>
</ul>
<center>
<a href="add.php"><button class="hp_btns" style="margin-top: 150px;">ADD MOVIE</button></a><br>
<a href="deletem.php"><button class="hp_btns">DELETE MOVIE</button></a><br>
<a href="updatem.php"><button class="hp_btns">UPDATE MOVIE</button></a><br>
<a href="mlist.php"><button class="hp_btns">MOVIE LIST</button></a><br>
</center>
</body>
</html>