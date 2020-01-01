<?php
	require_once('connection.php');

	$id_akun = $_POST['id_akun'];
	// $nama = $_POST['nama'];
	// $email = $_POST['email'];
	// $jmlPohon = $_POST['jml_pohon'];
	// $jmlDonasi = $_POST['jml_donasi'];
	$result = array();
	$query = mysqli_query($CON,"SELECT dat_donasi.metode, dat_donasi.id_donasi, dat_donasi.jml_pohon, dat_donasi.jml_donasi FROM dat_donasi INNER JOIN dat_usr ON dat_donasi.id_akun = dat_usr.id_akun
		WHERE dat_donasi.id_akun='$id_akun'");
	// SELECT namakrt, asal, tujuan, TIME_FORMAT(jam_brk, '%h:%m') AS jam_brk FROM jadwal WHERE asal = '$asal' AND tujuan = '$tujuan' AND jam_brk > NOW() 
	while($row = mysqli_fetch_assoc($query)){
	  $result[] = $row;
	}
	echo json_encode(array('result'=>$result));
?>