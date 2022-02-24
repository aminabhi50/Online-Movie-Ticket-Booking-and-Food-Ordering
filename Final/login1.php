<?php
	$un = $_POST['uname'];
	$pass = $_POST['psw'];
	if($un==("admin") && $pass==("admin"))
	{
		session_start();
		$_SESSION['name'] = $un;
		echo "<script type='text/javascript'>";
		echo "alert('You have successfully logged in.');";
		echo "window.location='movie.php';";
		echo "</script>";
	}
	else if($un!=("admin") && $pass!=("admin"))
	{
		echo "<script type='text/javascript'>";
		echo "alert('Username and Password is incorrect.');";
		echo "window.location='login.html';";
		echo "</script>";
	}
	else
	{
		if($un!=("admin"))
		{
			echo "<script type='text/javascript'>";
			echo "alert('Username is incorrect.');";
			echo "window.location='login.html';";
			echo "</script>";
		}
		else if($pass!=("admin"))
		{
			echo "<script type='text/javascript'>";
			echo "alert('Password is incorrect.');";
			echo "window.location='login.html';";
			echo "</script>";
		}
	}
?>