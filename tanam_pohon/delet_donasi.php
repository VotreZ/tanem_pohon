<?php
	require_once('connection.php');
	$kode_donasi = $_POST['id_donasi'];
	$query = mysqli_query($CON, "DELETE FROm dat_donasi WHERE id_donasi = '$kode_donasi'");
	if($query){
    	echo json_encode(array('message'=>'Data berhasil dihapus'));
  	}else{
    	echo json_encode(array('message'=>''));
  	}
?>