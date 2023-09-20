<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Symfony\Component\Process\Process;

class TestController extends Controller
{
    public function tst()
    {
        // $executeCommand = "java -cp /C:/Users/jamal/Desktop hello";
        // exec($executeCommand, $output, $returnCode);
        // // Get the output or return code as needed
        $directory = "C:\Users\jamal\Desktop\car_rental";
        // Change the current working directory to the desired directory
        chdir($directory);
        // $executeCommand = 'java -cp ".;C:\\Users\\jamal\\Desktop\\gson-2.0.jar" hello';
        $executeCommand = 'java -cp ".;C:\Users\jamal\Desktop\ojdbc5.jar" debut';
        // $array = json_decode(shell_exec($executeCommand), true);
        dd(shell_exec($executeCommand));
    }
}
