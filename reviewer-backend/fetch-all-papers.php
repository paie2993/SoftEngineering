<?php
    header('Access-Control-Allow-Origin: *');
    header('Access-Control-Allow-Headers: *');

    require 'paper.php';

    $host = '127.0.0.1';
    $username = 'root';
    $password = '';
    $database = 'cms';

    $connection = new mysqli($host, $username, $password, $database);
    if (!$connection) {
        die ('Could not connect to the database server ' . mysqli_error());
    }

    $stmt = $connection->prepare(
        "SELECT id, title, abstract
        FROM `papers`"
    );
    
    try {
        $stmt->execute();
    } catch (Exception) {
        $connection->close();
        echo "<h1>Error fetching papers from the database!</h1>";
        exit(1);
    }

    $result = $stmt->get_result();
    $arr = array();
    while ($row = $result->fetch_assoc()) {
        $id = $row['id'];
        $title = $row['title'];
        $abstract = $row['abstract'];
        $paper = new Paper($id, $title, $abstract);
        array_push($arr, $paper);
    }

    $connection->close();
    echo json_encode($arr);
?>