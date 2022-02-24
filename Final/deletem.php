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
Delete movie
</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<ul>
<center class="head_r" style="background-color:#333"><font size="100px" style="color:#ffffff;">GOLD CINEMA</font></center>
  <li><a  href="movie.php" class="active">MOVIE</a></li>
  <li><a href="booking.php">BOOKING</a></li>
  <li><a  href="food.php">FOOD</a></li>
  <li><a href="orders.php">ORDERS</a></li>
  <li style="float:right"><a href="logout.php">LOGOUT</a></li>
</ul>
<center class="l"><label><b>DELETE MOVIE DETAILS</b></label></center>
<center>
<div class="container">
<form name="md" action="deletem1.php" method="POST">
	<input id="fn" type="text" placeholder="Movie Name" name="mname" required><br>
   <input id="del" class="btn" type="submit" value="Delete">
  </div>
  </center>
</form>
</body>
</html>