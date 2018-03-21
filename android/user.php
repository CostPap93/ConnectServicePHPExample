<?php
include("PDOConnection.php");

define("ACTION_ADD_USER","add");
define("ACTION_LOGIN","login");
define("RESULT_SUCCESS",0);
define("RESULT_ERROR",1);
define("RESULT_USER_EXISTS",2);

$action = $_POST["action"];
$result = RESULT_ERROR;

if(isset($action)){
	$username = $_POST["username"];
	$pwd = $_POST["password"];
	
	if(ACTION_ADD_USER == $action){
		if(isExistUser($cnn,$username)){
			$result = RESULT_USER_EXISTS;
		}
		else{
			insertUser($cnn,$username,$pwd);
			$result = RESULT_SUCCESS;
		}
	}
	else{
		if(login($cnn,$username,$pwd)){
			$result = RESULT_SUCCESS;
		}
		else{
			$result = RESULT_ERROR;
		}
	}
}

echo(json_encode( array("result" => $result) ) );
			

function insertUser($cnn,$username,$pwd){
	$query = "INSERT INTO USER(USERNAME,PASSWORD) VALUES (?,?)";
	$stmt = $cnn->prepare($query);
	$stmt->bindParam(1,$username);
	$stmt->bindParam(2,$pwd);
	$stmt->execute();
}

function isExistUser($cnn,$username){
	$query = "SELECT * FROM USER WHERE USERNAME = ?";
	$stmt = $cnn->prepare($query);
	$stmt->bindParam(1,$username);
	$stmt->execute();
	$rowcount = $stmt->rowcount();
	//for debug
	//var_dump($rowcount);
	return $rowcount;
}

function login($cnn , $username,$pwd){
	$query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
	$stmt = $cnn->prepare($query);
	$stmt->bindParam(1,$username);
	$stmt->bindParam(2,$pwd);
	$stmt->execute();
	$rowcount = $stmt->rowcount();
	//for debug
	//var_dump($rowcount);
	return $rowcount;
}