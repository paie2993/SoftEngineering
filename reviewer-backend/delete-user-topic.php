<?php
    header('Access-Control-Allow-Origin: *');
    header('Access-Control-Allow-Headers: *');
    header('Access-Control-Allow-Methods: *');

    $username = 'root';
    $hostname = '127.0.0.1';
    $password = '';
    $database = 'cms';

    $connection = new mysqli($hostname, $username, $password, $database);
    if (!$connection) {
        die ("Could not access the database server " . mysqli_error());
    }

    $user_id = $_GET['id'];
    $user_topic_id = $_GET['topic_id'];

    $stmt = $connection->prepare(
        "DELETE 
        FROM `userstopics`
        WHERE userId = ? AND topicId = ?"
    );
    $stmt->bind_param('ii', $user_id, $user_topic_id);

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