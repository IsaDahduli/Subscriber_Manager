<?php
header('Content-Type: application/json');

// Clear any previous output
ob_clean();

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "subscribers";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    echo json_encode(array("status" => "error", "message" => "Connection failed: " . $conn->connect_error));
    exit;
}

// Get the input data from POST parameters
$msisdn = isset($_POST['msisdn']) ? $_POST['msisdn'] : null;
$iccid = isset($_POST['iccid']) ? $_POST['iccid'] : null;
$chargingType = isset($_POST['charging_type']) ? $_POST['charging_type'] : null;
$simProfileId = isset($_POST['sim_profile_id']) ? $_POST['sim_profile_id'] : null;
$serviceType = isset($_POST['service_type']) ? $_POST['service_type'] : null;
$mvnoName = isset($_POST['mvno_name']) ? $_POST['mvno_name'] : null;
$tariffCode = isset($_POST['tariff_code']) ? $_POST['tariff_code'] : null;
$tac = isset($_POST['tac']) ? $_POST['tac'] : null;
$brand = isset($_POST['brand']) ? $_POST['brand'] : null;
$model = isset($_POST['model']) ? $_POST['model'] : null;

// Validate input
if (!$msisdn || !$iccid || !$chargingType || !$simProfileId || !$serviceType || !$mvnoName || !$tariffCode || !$tac || !$brand || !$model) {
    echo json_encode(array("status" => "error", "message" => "Invalid input: Missing required fields"));
    exit;
}

// Prepare and bind
$stmt = $conn->prepare("UPDATE subscribers_table SET iccid=?, charging_type=?, sim_profile_id=?, service_type=?, mvno_name=?, tariff_code=?, tac=?, brand=?, model=? WHERE msisdn=?");

if ($stmt === false) {
    echo json_encode(array("status" => "error", "message" => "Prepare failed: " . $conn->error));
    exit;
}

$stmt->bind_param("ssssssssss", $iccid, $chargingType, $simProfileId, $serviceType, $mvnoName, $tariffCode, $tac, $brand, $model, $msisdn);

if ($stmt->execute()) {
    // Check if any rows were affected
    if ($stmt->affected_rows > 0) {
        echo json_encode(array("status" => "success", "message" => "Subscriber updated successfully"));
    } else {
        echo json_encode(array("status" => "error", "message" => "Subscriber not found"));
    }
} else {
    echo json_encode(array("status" => "error", "message" => "Error updating subscriber: " . $stmt->error));
}

$stmt->close();
$conn->close();
