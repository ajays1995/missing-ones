<?php

/*
 * Following code will list all the ibetos
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// get all ibetos from ibetos table
$result = mysql_query("SELECT *FROM ibeto") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // ibeto node
    $response["ibeto"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $ibeto = array();
        $ibeto["id"] = $row["id"];
        $ibeto["name"] = $row["name"];
        $ibeto["age"] = $row["age"];
        $ibeto["gender"] = $row["gender"];
        $ibeto["mdate"] = $row["mdate"];
        $ibeto["guardian"] = $row["guardian"];
        $ibeto["image_path"] = $row["image_path"];
        $ibeto["maddress"] = $row["maddress"];
        $ibeto["mstate"] = $row["mstate"];
        $ibeto["mcity"] = $row["mcity"];
        $ibeto["htype"] = $row["htype"];
        $ibeto["hcolor"] = $row["hcolor"];
        $ibeto["complexion"] = $row["complexion"];
        $ibeto["body"] = $row["body"];
        $ibeto["mark"] = $row["mark"];
        $ibeto["rname"] = $row["rname"];
        $ibeto["relationship"] = $row["relationship"];
        $ibeto["contact"] = $row["contact"];
        $ibeto["raddress"] = $row["raddress"];
        $ibeto["rstate"] = $row["rstate"];
        $ibeto["rcity"] = $row["rcity"];


        // push single ibeto into final response array
        array_push($response["ibeto"], $ibeto);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no ibetos found
    $response["success"] = 0;
    $response["message"] = "No ibeto found";

    // echo no users JSON
    echo json_encode($response);
}
?>
