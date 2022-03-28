<?php
$filename=md5(uniqid()).'.'.$_GET['filetype'];
$fileData=file_get_contents('php://input');
if (!file_exists('uploads')) {
    mkdir('uploads', 0777, true);
}
$fhandle=fopen("uploads/image/".$filename, 'wb');
fwrite($fhandle, $fileData);
fclose($fhandle);
echo($filename);
?>