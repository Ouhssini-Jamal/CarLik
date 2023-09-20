
<div id="sidebar" class="active">
            <div class="sidebar-wrapper active">
                <div class="sidebar-header">
                    <div class="d-flex justify-content-between">
                        <div class="logo">
                            <a href="/dashboard">
                                <img src="/images/logo/logo.png" alt="Logo" srcset="" style="width: 200px; height: auto;">
                              </a>
                              
                            <!-- <a href="Dashboard-user.html"><img src="/images/logo/logo.png" alt="Logo" srcset=""></a> -->
                        </div>
                        <div class="toggler">
                            <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                        </div>
                    </div>
                </div>
                <div class="sidebar-menu">
                    <ul class="menu">
                        <li class="sidebar-title">Menu</li>
                        @if(Route::currentRouteName() === 'dashboard')
                        <li class="sidebar-item active ">
                            <a href="/dashboard" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        @else
                        <li class="sidebar-item">
                            <a href="/dashboard" class='sidebar-link'>
                                <i class="bi bi-grid-fill"></i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        @endif

                        <!-- <li class="sidebar-item  has-sub"> -->
                        @if(Route::currentRouteName() === 'cars.index')
                        <li class="sidebar-item active">
                        <a href="{{ route('cars.index') }}" class="sidebar-link">
                                <i class="fas fa-car"></i>
                                <span>Cars</span>
                            </a>
                        </li>
                        @else
                        <li class="sidebar-item">
                        <a href="{{ route('cars.index') }}" class="sidebar-link">
                                <i class="fas fa-car"></i>
                                <span>Cars</span>
                            </a>
                        </li>
                        @endif
                        @if(Route::currentRouteName() === 'customers.index')
                        <li class="sidebar-item active">
                            <a href="/customers" class='sidebar-link'>
                                <i class="bi bi-people-fill"></i>
                                <span>Clients</span>
                            </a>
                        </li>
                        @else
                        <li class="sidebar-item">
                            <a href="/customers" class='sidebar-link'>
                                <i class="bi bi-people-fill"></i>
                                <span>Clients</span>
                            </a>
                        </li>
                        @endif
                        @if(Route::currentRouteName() === 'rents.index')
                        <li class="sidebar-item active">
                            <a href="/rents" class='sidebar-link'>
                                <i class="bi bi-journal-bookmark-fill"></i>
                                <span>Rent Management</span>
                            </a>
                        </li>
                        @else
                        <li class="sidebar-item">
                            <a href="/rents" class='sidebar-link'>
                                <i class="bi bi-journal-bookmark-fill"></i>
                                <span>Rent Management</span>
                            </a>
                        </li>
                        @endif
              
                        <!-- <li class="sidebar-item  has-sub"> -->
                          
                            <!-- <ul class="submenu ">
                                <li class="submenu-item ">
                                    <a href=#>Manage</a>
                                 </li> 
                            </ul> -->
                        </li>
                        <!--<li class="sidebar-title">Forms &amp; Tables</li>-->

                    </ul>
                </div>
                <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
            </div>
        </div>