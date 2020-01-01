<?php 
	require_once('connection.php');

	$idA = $_POST['id_akun'];
	$nama = $_POST['nama'];
	$email = $_POST['email'];
	$user = $_POST['username'];
	$pass = $_POST['password'];


	$query = mysqli_query($CON, "UPDATE dat_usr SET nama = '$nama', email = '$email', username = '$user', password = '$pass' WHERE id_akun = '$idA'");
	// $query = mysqli_query($CON, "UPDATE dat_usr SET password = '$pass', nama = '$nama',
	// 	email = '$email' WHERE username = '$user'");
	if($query){
    	echo json_encode(array('message'=>'Update Berhasil'));
  	}else{
    	echo json_encode(array('message'=>'Gagal Update data anda.'));
  	}
?>