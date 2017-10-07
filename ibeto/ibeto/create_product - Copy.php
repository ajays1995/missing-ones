<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields

if (isset($_POST['name']) && isset($_POST['lat']) && isset($_POST['gender']) && isset($_POST['age']) && isset($_POST['mdate']) && isset($_POST['guardian']) && isset($_POST['maddress']) && isset($_POST['mstate']) && isset($_POST['mcity']) && isset($_POST['htype']) && isset($_POST['hcolor']) && isset($_POST['complexion']) && isset($_POST['body']) && isset($_POST['mark']) && isset($_POST['rname']) && isset($_POST['relationship']) && isset($_POST['contact']) && isset($_POST['raddress']) && isset($_POST['rstate']) && isset($_POST['rcity'])) {
    
    $name = $_POST['name'];
    $lat = $_POST['lat'];
    $gender = $_POST['gender'];
    $age = $_POST['age'];
    $mdate = $_POST['mdate'];
    $guardian = $_POST['guardian'];
    $maddress = $_POST['maddress'];
    $mstate = $_POST['mstate'];
    $mcity = $_POST['mcity'];
    $htype = $_POST['htype'];
    $hcolor = $_POST['hcolor'];
    $complexion = $_POST['complexion'];
    $body = $_POST['body'];
    $mark = $_POST['mark'];
    $rname = $_POST['rname'];
    $relationship = $_POST['relationship'];
    $contact = $_POST['contact'];
    $raddress = $_POST['raddress'];
    $rstate = $_POST['rstate'];
    $rcity = $_POST['rcity'];

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO ibeto(name,lat, gender, age, mdate, guardian,maddress, mstate, mcity, htype, hcolor, complexion, body,mark, rname, relationship, contact, raddress, rstate, rcity) VALUES('$name','$lat', '$gender', '$age', '$mdate', '$guardian', '$maddress', '$mstate', '$mcity', '$htype', '$hcolor', '$complexion', '$body','$mark', '$rname', '$relationship', '$contact', '$raddress', '$state', '$rcity')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>