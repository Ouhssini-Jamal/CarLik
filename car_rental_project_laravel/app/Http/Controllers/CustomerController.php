<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class CustomerController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $user = session('user');
        $directory = "C:\Users\jamal\Desktop\car_rental";
        chdir($directory);
        $executeCommand = 'java -cp ".;C:\Users\jamal\Desktop\ojdbc5.jar;C:\Users\jamal\Desktop\gson-2.0.jar" Customer 1 '.$user->idAgency;
        $data = shell_exec($executeCommand);
        if ($data) {
            $customers = json_decode($data);
            return view('customers',compact('customers'));
        }
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        //
    }
}
