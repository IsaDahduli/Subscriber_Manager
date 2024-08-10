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
    die(json_encode(array("status" => "error", "message" => "Connection failed: " . $conn->connect_error)));
}

if ($_SERVER['REQUEST_METHOD'] === 'DELETE') {
    if (isset($_GET['msisdn'])) {
        $msisdn = $_GET['msisdn'];

        // Prepare and bind
        $stmt = $conn->prepare("DELETE FROM subscribers_table WHERE msisdn = ?");
        if (!$stmt) {
            echo json_encode(array("status" => "error", "message" => "Prepare failed: (" . $conn->errno . ") " . $conn->error));
            exit();
        }

        $stmt->bind_param("s", $msisdn);

        if ($stmt->execute()) {
            if ($stmt->affected_rows > 0) {
                echo json_encode(array("status" => "success", "message" => "Subscriber deleted successfully"));
            } else {
                echo json_encode(array("status" => "error", "message" => "Invalid MSISDN"));
            }
        } else {
            echo json_encode(array("status" => "error", "message" => "Failed to delete subscriber"));
        }

        $stmt->close();
    } else {
        echo json_encode(array("status" => "error", "message" => "MSISDN not provided"));
    }
} else {
    echo json_encode(array("status" => "error", "message" => "Invalid request method"));
}

$conn->close();
?>