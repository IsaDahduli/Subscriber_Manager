<?php
header('Content-Type: application/json');

$servername = "localhost";
$username = "root";
$password = ""; 
$dbname = "subscribers";


// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT msisdn, iccid FROM subscribers_table";
$result = $conn->query($sql);

$subscribers = array();

if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $subscribers[] = $row;
    }
}

echo json_encode($subscribers);

$conn->close();

error_reporting(0);
?>
