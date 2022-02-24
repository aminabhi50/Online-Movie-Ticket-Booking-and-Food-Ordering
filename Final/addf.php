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
Add Food 
</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<ul>
<center class="head_r" style="background-color:#333"><font size="100px" style="color:#ffffff;">GOLD CINEMA</font></center>
  <li><a href="movie.php">MOVIE</a></li>
  <li><a href="booking.php">BOOKING</a></li>
  <li><a class="active" href="food.php">FOOD</a></li>
  <li><a href="orders.php">ORDERS</a></li>
  <li style="float:right"><a href="logout.php">LOGOUT</a></li>
</ul>
<center class="l"><label><b>ADD FOOD DETAILS</b></label></center>
<center>
<div class="container">
<form name="md" action="addf1.php" method="POST" enctype="multipart/form-data">
	<input id="fn" type="text" placeholder="Food Name" name="fname" required><br>
    <input id="p" type="text" placeholder="Price" name="Price" required><br>
	<label class="ps"> Upload Image: </label><input type="file" name="img" required><br>
    <input id="add" class="btn" type="submit" value="Add">
  </div>
  </center>
</form>
</body>
</html>