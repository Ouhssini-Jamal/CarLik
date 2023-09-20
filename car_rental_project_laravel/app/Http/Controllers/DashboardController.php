<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class DashboardController extends Controller
{
    public function dash_index(){
        $user = session('user');
        $directory = "C:\Users\jamal\Desktop\car_rental";
        chdir($directory);
        $executeCommand = 'java -cp ".;C:\Users\jamal\Desktop\ojdbc5.jar;C:\Users\jamal\Desktop\gson-2.0.jar" Dash '.$user->idAgency;
        $data = shell_exec($executeCommand);
        $dash = json_decode($data);
        return view('dashboard',compact('user','dash'));
    }
}
