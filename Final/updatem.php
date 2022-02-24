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
Update Movie
</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<ul>
<center class="head_r" style="background-color:#333"><font size="100px" style="color:#ffffff;">GOLD CINEMA</font></center>
  <li><a class="active" href="movie.php">MOVIE</a></li>
  <li><a href="booking.php">BOOKING</a></li>
  <li><a href="food.php">FOOD</a></li>
  <li><a href="orders.php">ORDERS</a></li>
  <li style="float:right"><a href="logout.php">LOGOUT</a></li>
</ul>
<center class="l"><label><b>UPDATE MOVIE DETAILS</b></label></center>
<center>
<div class="container">
<form name="md" action="updatem1.php" method="POST">
	<input id="mn" type="text" placeholder="Movie Name" name="mname" required><br>
    <input id="sno" type="text" placeholder="Screen Number" name="screenno" required><br>
	<label class="ps">Price:</label><br>
	<input id="g" type="text" placeholder="Gold" name="gold" style="width: 25%;" required>
	<input id="di" type="text" placeholder="Diamond" name="diamond" style="width: 25%;" required>
	<input id="pl" type="text" placeholder="Platinum" name="platinum" style="width: 25%;" required><br>
	<label class="ps">Showtime:</label> <br>
	<input id="time1" type="text" placeholder="time1" name="time1" style="width: 25%;">
	<input id="time2" type="text" placeholder="time2" name="time2" style="width: 25%;">
	<input id="time3" type="text" placeholder="time3" name="time3" style="width: 25%;">
	<input id="time4" type="text" placeholder="time4" name="time4" style="width: 25%;">
    <input id="upd" class="btn" type="submit" value="Update">
  </div>
</form>
</body>
</html>