<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\File;
class CarController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $user = session('user');
        $directory = "C:\Users\jamal\Desktop\car_rental";
        chdir($directory);
        $executeCommand = 'java -cp ".;C:\Users\jamal\Desktop\ojdbc5.jar;C:\Users\jamal\Desktop\gson-2.0.jar" Car 1 '.$user->idAgency;
        $data = shell_exec($executeCommand);
        $executeCommand = 'java -cp ".;C:\Users\jamal\Desktop\ojdbc5.jar;C:\Users\jamal\Desktop\gson-2.0.jar" Customer 1 '.$user->idAgency;
        $data1 = shell_exec($executeCommand);
        if ($data == "Invalid") {
            dd($data);
        } else {
            $cars = json_decode($data);
            $customers = json_decode($data1);
            return view('cars',compact('cars','customers'));
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
        $user = session('user');
        $directory = "C:\Users\jamal\Desktop\car_rental";
        chdir($directory);
        $request->validate([
            'image' => 'required|image|mimes:jpeg,png,jpg,gif,svg|max:2048',
        ]);
        $imageName = time().'.'.$request->image->extension();  
        $request->image->move(public_path('/images/cars'), $imageName);
        $executeCommand = 'java -cp ".;C:\Users\jamal\Desktop\ojdbc5.jar;C:\Users\jamal\Desktop\gson-2.0.jar" Car 2 ' . $user->idAgency . ' ' . $request->brand . ' ' . $request->model . ' ' . $request->year . ' ' . $request->color . ' ' . $request->price . ' ' . $imageName;
        $data = shell_exec($executeCommand);
        if ($data == "valid") {
            return redirect('/cars');
    }
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
    public function update(Request $request, $id)
{
    $user = session('user');
    $directory = "C:\Users\jamal\Desktop\car_rental";
    chdir($directory);
    $imageName = 'null';
    if ($request->image) {
        $imageName = time() . '.' . $request->image->extension();
        $request->image->move(public_path('/images/cars'), $imageName);
    }
    $brand = $request->brand ?? 'null';
    $model = $request->model ?? 'null';
    $year = $request->year ?? 0;
    $color = $request->color ?? 'null';
    $price = $request->price ?? 0;
    $availabilityStatus = $request->availabilityStatus ?? 'null';
    $executeCommand = 'java -cp ".;C:\Users\jamal\Desktop\ojdbc5.jar;C:\Users\jamal\Desktop\gson-2.0.jar" Car 3 ' . $id . ' ' . $user->idAgency . ' ' . $brand . ' ' . $model . ' ' . $year . ' ' . $color . ' ' . $price . ' ' . $availabilityStatus . ' ' . $imageName;
    $data = shell_exec($executeCommand);
    if ($data) {
        return redirect('/cars');
    } else {
        dd($data);
    }
}


    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $directory = "C:\Users\jamal\Desktop\car_rental";
        chdir($directory);
        $executeCommand = 'java -cp ".;C:\Users\jamal\Desktop\ojdbc5.jar;C:\Users\jamal\Desktop\gson-2.0.jar" Car 4 '.$id;
        $data = shell_exec($executeCommand);
        if ($data) {
            return redirect('/cars');
        } else {
            dd($data);
        }
    }
}
