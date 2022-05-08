<?php
    header('Access-Control-Allow-Origin: *');
    header('Access-Control-Allow-Headers: *');

    require 'author.php';

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
        "SELECT id, name
        FROM `authors` INNER JOIN `authorspapers` ON id = authorId
        WHERE paperId = ?
        "
    );
    $stmt->bind_param("i", $paper_id);

    try {
        $stmt->execute();
    } catch (Exception) {
        $connection->close();
        echo json_encode("Error fetching authors of the paper");
        exit(1);
    }

    $result = $stmt->get_result();
    $arr = array();
    while ($row = $result->fetch_assoc()) {
        $id = $row['id'];
        $name = $row['name'];
        $author = new Author($id, $name);
        array_push($arr, $author);    
    }

    $connection->close();
    echo json_encode($arr);
?>