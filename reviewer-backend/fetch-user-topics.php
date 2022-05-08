<?php
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: *");

    require 'topic.php';

    $username = 'root';
    $hostname = '127.0.0.1';
    $password = '';
    $database = 'cms';

    $connection = new mysqli($hostname, $username, $password, $database);
    if (!$connection) {
        die ("Could not access the database server " . mysqli_error());
    }

    $user_id = $_GET['id'];
    
    $stmt = $connection->prepare(
        "SELECT id, name, description 
        FROM `topics` 
        WHERE id IN (
            SELECT topicId
            FROM `userstopics`
            WHERE userId = ?
        )"
    );
    $stmt->bind_param("i", $user_id);

    try {
        $stmt->execute();
    }  catch (Exception) {
        $connection->close();
        echo 'Could not fetch data from database';
        exit(1);
    }

    $result = $stmt->get_result();
    $arr = array();
    while ($row = $result->fetch_assoc()) {
        $id = $row['id'];
        $name = $row['name'];
        $description = $row['description'];
        $topic = new Topic($id, $name, $description);
        array_push($arr, $topic);
    }

    $connection->close();
    echo json_encode($arr);
?>