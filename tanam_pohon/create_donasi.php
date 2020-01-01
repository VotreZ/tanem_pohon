<?php
	require_once('connection.php');

	$id_donasi = $_POST['id_donasi'];
	$id_akun = $_POST['id_akun'];
	$jml_pohon = $_POST['jml_pohon'];
	$jml_donasi = $_POST['jml_donasi'];
	$metode = $_POST['metode'];
	
	$query = mysqli_query($CON, "INSERT INTO dat_donasi (id_donasi, id_akun, jml_pohon, jml_donasi, metode) VALUES ('$id_donasi', '$id_akun', '$jml_pohon', '$jml_donasi', '$metode')");
	if($query){
    	echo json_encode(array('message'=>'Pembayaran Berhasil.'));
  	}else{
    	echo json_encode(array('message'=>'Pembayaran Gagal.'));
  	}

?>