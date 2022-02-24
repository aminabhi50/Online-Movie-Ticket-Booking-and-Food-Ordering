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
Add Movie
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
<center class="l"><label><b>MOVIE DETAILS</b></label></center>
<center>
<div class="container">
<form name="md" action="add1.php" method="POST" enctype="multipart/form-data">
	<input id="mn" type="text" placeholder="Movie Name" name="mname" required><br>
    <input id="d" type="text" placeholder="Director" name="director" required><br>
	<input id="p" type="text" placeholder="Producer" name="producer" required><br>
	<textarea placeholder="Cast" name="cast" rows="2" cols="50" required></textarea><br>
	<textarea placeholder="Description" name="desc" rows="5" cols="50" required></textarea><br>
	<input id="sno" type="text" placeholder="Screen Number" name="screenno" required><br>
	<input id="lang" type="text" placeholder="Language" name="lang" required><br>
	<input id="t" type="text" placeholder="Type" name="t" required><br>
	<label class="ps"> Upload Image: </label><input type="file" name="mimg" required><br>
	<label class="ps">Price:</label><br>
	<input id="g" type="text" placeholder="Gold" name="gold" style="width: 25%;" required>
	<input id="di" type="text" placeholder="Diamond" name="diamond" style="width: 25%;" required>
	<input id="pl" type="text" placeholder="Platinum" name="platinum" style="width: 25%;" required><br>
	<label class="ps">Showtime:</label> <br>
	<input id="time1" type="text" placeholder="time1" name="time1" style="width: 25%;">
	<input id="time2" type="text" placeholder="time2" name="time2" style="width: 25%;">
	<input id="time3" type="text" placeholder="time3" name="time3" style="width: 25%;">
	<input id="time4" type="text" placeholder="time4" name="time4" style="width: 25%;">
    <input id="add" class="btn" type="submit" value="Add">
	</div>
	</form>
	</center>
</body>
</html>