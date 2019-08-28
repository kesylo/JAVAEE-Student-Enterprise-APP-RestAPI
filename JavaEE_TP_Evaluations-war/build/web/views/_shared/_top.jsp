<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container sticky-top mt-3 mb-3">

    <nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm rounded">
        <a class="navbar-brand d-md-block d-none" href="#">
            <img src='resources/images/png/iroll.png' style="height: 35px;" class="img-responsive" />
        </a>

        <form class="form-inline my-2 my-lg-0">
            <div class="input-group">
                <input type="text" class="form-control" aria-describedby="val1"
                       placeholder="Search some one..." aria-label="Search" />
                <div class="input-group-append">
                    <button class="btn mm-process-in-production" type="button" id="val1">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
        </form>

        <ul class="navbar-nav ml-auto float-right">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle font-weight-bold" href="#" id="navbarDropdownUser" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-user text-dark"></i> ${sessionScope.con.person.firstname} ${sessionScope.con.person.lastname} (${sessionScope.con.person.isStudent ? "Strudent" 
                                                                                                                                                                       : "Teacher"})
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownUser">
                    <a class="dropdown-item" href="Javascript:;" data-toggle="modal" data-target="#modal_profile">
                        <i class="fa fa-user"></i> Profil
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item app-disconnect" href="Javascript:;">
                        <i class="fa fa-sign-out"></i> Sign Out
                    </a>
                </div>
            </li>
        </ul>
    </nav>

</div>