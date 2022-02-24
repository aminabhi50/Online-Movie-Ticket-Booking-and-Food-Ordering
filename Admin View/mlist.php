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
Movie List
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
<h1 style="margin-top: 130px;"> MOVIE LIST </h1>
</center>
<table>
<tr>
<th>M_ID</th>
<th>M_Name</th>
<th>Director</th>
<th>Producer</th>
<th>Cast</th>
<th>Description</th>
<th>Screen No</th>
<th>M_Image</th>
</tr>
<?php
$conn = mysqli_connect('localhost','root','');
$db = mysqli_select_db($conn,'adminview');
$q = "select * from movieinfo";
$r = mysqli_query($conn,$q);
if(mysqli_num_rows($r)>0)
{
	while($row = mysqli_fetch_assoc($r))
	{
		echo "<tr>";
		echo "<td>". $row["M_ID"] ."</td>";
		echo "<td>". $row["M_Name"] ."</td>";
		echo "<td>". $row["Director"] ."</td>";
		echo "<td>". $row["Producer"] ."</td>";
		echo "<td>". $row["Cast"] ."</td>";
		echo "<td>". $row["Description"] ."</td>";
		echo "<td>". $row["Screen_No"] ."</td>";
		echo "<td><img src='". $row["M_Image"] ."'></td>";
		echo "</tr>";
	}
}
?>
</table>
</body>
</html>