<?php
require "con.php";
$uname="imsaiful";
$upas="myammaji";
$mysql_query="select * from emp_data where uname='$uname' and upas='$upas'";
$result=mysqli_query($con,$mysql_query);
if($result)
	{
		
		echo " Successfull Yar";
	}
else
	{
		echo "Login Not Sucess";
	}

?>