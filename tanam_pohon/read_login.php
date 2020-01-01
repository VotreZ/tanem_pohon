<?php
	require_once('connection.php');

	$username = $_POST['username'];
	$password = $_POST['password'];
	
	$result = array();
	$query = mysqli_query($CON,"SELECT id_akun FROM dat_usr WHERE username = '$username' AND password = '$password'");
	
	while($row = mysqli_fetch_assoc($query)){
	  $result[] = $row;
	}
	
	echo json_encode(array('result'=>$result));
?>