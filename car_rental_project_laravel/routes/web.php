<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\TestController;
use App\Http\Controllers\LoginController;
use App\Http\Controllers\DashboardController;
use App\Http\Controllers\CarController;
use App\Http\Controllers\RentController;
use App\Http\Controllers\CustomerController;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', [LoginController::class, 'index']);
Route::get('/dashboard',[DashboardController::class, 'dash_index'])->name('dashboard');
Route::post('/', [LoginController::class, 'login']);
Route::get('/cars', [CarController::class, 'index'])->name('cars.index');
Route::get('/cars/create', [CarController::class, 'create'])->name('cars.create');
Route::post('/cars', [CarController::class, 'store'])->name('cars.store');
Route::get('/cars/{car}', [CarController::class, 'show'])->name('cars.show');
Route::put('/cars/{id}', [CarController::class, 'update'])->name('cars.update');
Route::delete('/cars/{id}', [CarController::class, 'destroy'])->name('cars.destroy');



Route::get('/customers', [CustomerController::class, 'index'])->name('customers.index');
Route::get('/customers/create', [CustomerController::class, 'create'])->name('customers.create');
Route::post('/customers', [CustomerController::class, 'store'])->name('customers.store');
Route::get('/customers/{customer}', [CustomerController::class, 'show'])->name('customers.show');
Route::get('/customers/{customer}/edit', [CustomerController::class, 'edit'])->name('customers.edit');
Route::put('/customers/{customer}', [CustomerController::class, 'update'])->name('customers.update');
Route::delete('/customers/{customer}', [CustomerController::class, 'destroy'])->name('customers.destroy');

Route::get('/rents', [RentController::class, 'index'])->name('rents.index');
Route::get('/rents/create', [RentController::class, 'create'])->name('rents.create');
Route::post('/rents', [RentController::class, 'store'])->name('rents.store');
Route::get('/rents/{rent}', [RentController::class, 'show'])->name('rents.show');
Route::get('/rents/{rent}/edit', [RentController::class, 'edit'])->name('rents.edit');
Route::put('/rents/{rent}', [RentController::class, 'update'])->name('rents.update');
Route::delete('/rents/{rent}', [RentController::class, 'destroy'])->name('rents.destroy');
