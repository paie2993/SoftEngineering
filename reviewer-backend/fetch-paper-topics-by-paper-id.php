<?php
    header('Access-Control-Allow-Origin: *');
    header('Access-Control-Allow-Headers: *');

    require 'topic.php';

    $host = '127.0.0.1';
    $username = 'root';
    $password = '';
    $database = 'cms';

    $connection = new mysqli($host, $username, $password, $database);
    if (!$connection) {
        die ('Could not connect to the database server ' . mysqli_error());
    }
    $paper_id = $_GET['id'];

    $stmt = $connection->prepare(
        "SELECT id, name, description
        FROM `topics` INNER JOIN `paperstopics` ON id = topicId
        WHERE paperId = ?
        "
    );
    $stmt->bind_param("i", $paper_id);

    try {
        $stmt->execute();
    } catch (Exception) {
        $connection->close();
        echo json_encode("Error fetching topics of the paper");
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