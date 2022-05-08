<?php
    header('Access-Control-Allow-Origin: *');
    header('Access-Control-Allow-Headers: *');

    require 'comment.php';

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
        "SELECT paperId, reviewerId, content
        FROM `comments` INNER JOIN `papers` ON id = paperId
        WHERE id = ?
        "
    );
    $stmt->bind_param("i", $paper_id);

    try {
        $stmt->execute();
    } catch (Exception) {
        $connection->close();
        echo json_encode("Error fetching comments of the paper");
        exit(1);
    }

    $result = $stmt->get_result();
    $arr = array();
    while ($row = $result->fetch_assoc()) {
        $paperId = $row['paperId'];
        $reviewerId = $row['reviewerId'];
        $content = $row['content'];
        $comment = new Comment($paperId, $reviewerId, $content);
        array_push($arr, $comment);    
    }

    $connection->close();
    echo json_encode($arr);
?>