<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Session;
class LoginController extends Controller
{
    public function index(){
        return view('login');
    }

    public function login(Request $request)
    {
        $directory = "C:\Users\jamal\Desktop\car_rental";
        chdir($directory);
        $executeCommand = 'java -cp ".;C:\Users\jamal\Desktop\ojdbc5.jar;C:\Users\jamal\Desktop\gson-2.0.jar" Login '.$request->cin.' '.$request->password;
        $data = shell_exec($executeCommand);
        
        if ($data == "Invalid") {
            dd($data);
        } else {
            $user = json_decode($data);
            // Store user data in the session
            Session::put('user', $user);
            return redirect('/dashboard');
        }
    }
    public function index1(){
        return view('cars');
    }
}
