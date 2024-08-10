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

// Debugging: output the received parameters
error_log("Received parameters: msisdn=$msisdn, iccid=$iccid, charging_type=$chargingType, sim_profile_id=$simProfileId, service_type=$serviceType, mvno_name=$mvnoName, tariff_code=$tariffCode, tac=$tac, brand=$brand, model=$model");

if ($msisdn && $iccid && $chargingType && $simProfileId && $serviceType && $mvnoName && $tariffCode && $tac && $brand && $model) {
    // Prepare and bind
    $stmt = $conn->prepare("INSERT INTO subscribers_table (msisdn, iccid, charging_type, sim_profile_id, service_type, mvno_name, tariff_code, tac, brand, model) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

    if ($stmt === false) {
        echo json_encode(array("status" => "error", "message" => "Prepare failed: " . $conn->error));
        exit;
    }

    $stmt->bind_param("ssssssssss", $msisdn, $iccid, $chargingType, $simProfileId, $serviceType, $mvnoName, $tariffCode, $tac, $brand, $model);

    if ($stmt->execute()) {
        echo json_encode(array("status" => "success", "message" => "Subscriber added successfully"));
    } else {
        echo json_encode(array("status" => "error", "message" => "Error adding subscriber: " . $stmt->error));
    }

    $stmt->close();
} else {
    echo json_encode(array("status" => "error", "message" => "Invalid input"));
}