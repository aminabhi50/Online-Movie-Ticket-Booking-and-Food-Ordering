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
Food
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
<font size="70px">
<center>
<a href="addf.php"><button class="hp_btns" style="margin-top: 150px;">ADD FOOD ITEM</button></a><br>
<a href="removef.php"><button class="hp_btns">REMOVE FOOD ITEM</button></a><br>
<a href="changef.php"><button class="hp_btns">CHANGE PRICE</button></a><br>
<a href="flist.php"><button class="hp_btns">FOOD LIST</button></a><br>
</center>
</body>
</html>