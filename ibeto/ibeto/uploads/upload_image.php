<?php
    // Get image string posted from Android App
    $base=$_REQUEST['image'];

	// Get file name posted from Android App
	$filename = $_REQUEST['name'];

	// Decode Image
    $binary=base64_decode($base);

    header('Content-Type: bitmap; charset=utf-8');
	// Images will be saved under 'www/imgupload/uplodedimages' folder

    $file = fopen($filename.'-'.date("Y-m-d").'-'.rand().'.jpg', 'wb');

    $file_path='../uploads/'.$file;

	// Create File
    fwrite($file, $binary);
    fclose($file);


// Connect to server and select database.
mysql_connect("localhost", "root", " ")or die("cannot connect");
mysql_select_db("ibeto")or die("cannot select DB");

// update data in mysql database
$sql="UPDATE ibeto SET image_path='$file_path' WHERE name='$name'";
$result=mysql_query($sql);
    
echo '<img src=test.jpg>';

?>