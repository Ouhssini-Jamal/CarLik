<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/x-icon" href="/images/logo/logo.png">
    <title>Dashboard - {{$user->fname}}</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha384-...insert integrity hash here..." crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/bootstrap.css">

    <link rel="stylesheet" href="/vendor/iconly/bold.css">

    <link rel="stylesheet" href="/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="/vendor/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/app.css">
    <link rel="shortcut icon" href="/images/icons/favicon.svg" type="image/x-icon">
</head>

<body>
    <div id="app">
     @include('sidebar')
        <div id="main">
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
                </a>
            </header>

            <div class="page-heading">
                    @if($user->idAgency == 1)
                    <h1 style="color: cadetblue;text-align: center;" class="mb-4">Agadir agency</h1>
                    @else 
                    <h1 style="color: cadetblue;text-align: center;" class="mb-4">Marrakech agency</h1>
                    @endif
                <h3>Agent {{$user->fname}}</h3>
            </div>
            <div class="page-content">
                <section class="row">
                    <div class="col-12 col-lg-12">
                        <div class="row">
                            <div class="col-6 col-lg-4 col-md-6">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon green">
                                                    <i class="iconly-boldShow"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">Currently rented cars</h6>
                                                <h6 class="font-extrabold mb-0">{{$dash->cur}}</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6 col-lg-4 col-md-6">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon blue">
                                                    <i class="iconly-boldTick-Square"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">Earnings</h6>
                                                <h6 class="font-extrabold mb-0">{{$dash->erns}} MAD</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6 col-lg-4 col-md-6">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon purple">
                                                    <i class="iconly-boldTicket"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">Rents</h6>
                                                <h6 class="font-extrabold mb-0">{{$dash->nb_rents}}</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4>Latest Rents</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-hover table-lg">
                                                <thead>
                                                <tr>
                                                    <th>Car</th>
                                                    <th>Name</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td class="col-3">
                                                        <div class="d-flex align-items-center">
                                                            <div class="avatar avatar-md">
                                                                <img src="/images/faces/5.jpg">
                                                            </div>
                                                            <p class="font-bold ms-3 mb-0">AUDI Q5</p>
                                                        </div>
                                                    </td>
                                                    <td class="col-3">
                                                        <div class="d-flex align-items-center">
                                                            <div class="avatar avatar-md">
                                                                <img src="/images/faces/5.jpg">
                                                            </div>
                                                            <p class="font-bold ms-3 mb-0">Ayoub Malih</p>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-3">
                                                        <div class="d-flex align-items-center">
                                                            <div class="avatar avatar-md">
                                                                <img src="/images/faces/5.jpg">
                                                            </div>
                                                            <p class="font-bold ms-3 mb-0">BMW S8</p>
                                                        </div>
                                                    </td>
                                                    <td class="col-3">
                                                        <div class="d-flex align-items-center">
                                                            <div class="avatar avatar-md">
                                                                <img src="/images/faces/2.jpg">
                                                            </div>
                                                            <p class="font-bold ms-3 mb-0">Amine kadir</p>
                                                        </div>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </section>
            </div>

            @include('footer')
        </div>
    </div>
    <script src="/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>

    <!-- <script src="/vendor/apexcharts/apexcharts.js"></script>
    <script src="/js/pages/dashboard.js"></script> -->

    <!-- <script src="/js/main.js"></script> -->
</body>

</html>