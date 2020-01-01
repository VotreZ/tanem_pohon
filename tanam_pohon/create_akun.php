<?php
	require_once('connection.php');

	// $id_ak = $_POST['id_akun']
	$nm = $_POST['nama'];
	$em = $_POST['email'];
	$us = $_POST['username'];
	$ps = $_POST['password'];
	$query = mysqli_query($CON, "INSERT INTO dat_usr (nama, email, username, password) VALUES ('$nm', '$em', '$us', '$ps')");
	// $query = mysqli_query($CON, "INSERT INTO dat_usr (id_akun, nama, email, username, password) VALUES ('$id_ak','$nm', '$em', '$us', '$ps')");

	if($query){
		echo json_encode(array('message'=>'Akun Berhasil Dibuat.'));
	}else{
		echo json_encode(array('message'=>'Akun Gagal Dibuat.'));
		}
	
?>