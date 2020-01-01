<?php
	require_once('connection.php');
	// $userr = $_POST['username'];
	$idnya = $_POST['id_akun'];
	$result = array();
	$query = mysqli_query($CON,"SELECT * FROM dat_usr WHERE id_akun = '$idnya'");
	while($row = mysqli_fetch_assoc($query)){
	  $result[] = $row;
	}
	echo json_encode(array('result'=>$result));
?>