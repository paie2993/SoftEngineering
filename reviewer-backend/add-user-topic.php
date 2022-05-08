<?php
    header('Access-Control-Allow-Origin: *');
    header('Access-Control-Allow-Headers: *');

    require 'topic.php';

    $username = 'root';
    $hostname = '127.0.0.1';
    $password = '';
    $database = 'cms';

    $connection = new mysqli($hostname, $username, $password, $database);
    if (!$connection) {
        die ("Could not access the database server " . mysqli_error());
    }

    $payload = json_decode(file_get_contents("php://input"), true);
    $user_id = $payload['user_id'];
    $topic_id = $payload['topic_id'];

    $stmt = $connection->prepare(
        "INSERT INTO `userstopics` VALUES (?, ?)"
    );
    $stmt->bind_param("ii", $user_id, $topic_id);
    
    try {
        $stmt->execute();
    } catch (Exception) {
        $connection->close();
        echo json_encode(false);
        exit(1);
    }

    $connection->close();
    echo json_encode(true);
?>