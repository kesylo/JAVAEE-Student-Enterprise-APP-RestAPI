<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="card-header">

    <ul class="nav nav-tabs card-header-tabs app-menu-module">

        <li class="nav-item">
            <c:if test="${sessionScope.con.module_activate eq 'Dashboard'}">
                <a class="nav-link active" href="Javascript:;" data-app-menu="Dashboard">
                    <i class="fa fa-pie-chart"></i> Dashboard
                </a>
            </c:if>
            <c:if test="${sessionScope.con.module_activate ne 'Dashboard'}">
                <a class="nav-link app-menu-link" href="Dashboard" data-app-menu="Dashboard">
                    <i class="fa fa-pie-chart"></i> Dashboard
                </a>
            </c:if>
        </li>
            
        <c:if test="${!sessionScope.con.person.isIsStudent()}">
            
            <li class="nav-item">
                <c:if test="${sessionScope.con.module_activate eq 'ResultsTest'}">
                    <a class="nav-link active" href="Javascript:;" data-app-menu="ResultsTest">
                        <i class="fa fa-square"></i> Results of tests
                    </a>
                </c:if>
                <c:if test="${sessionScope.con.module_activate ne 'ResultsTest'}">
                    <a class="nav-link app-menu-link" href="ResultsTest" data-app-menu="ResultsTest">
                        <i class="fa fa-square"></i> Results of tests
                    </a>
                </c:if>
            </li>
            
            <li class="nav-item">
                <c:if test="${sessionScope.con.module_activate eq 'Evaluation'}">
                    <a class="nav-link active" href="Javascript:;" data-app-menu="Evaluation">
                        <i class="fa fa-square"></i> Evaluations
                    </a>
                </c:if>
                <c:if test="${sessionScope.con.module_activate ne 'Evaluation'}">
                    <a class="nav-link app-menu-link" href="Evaluation" data-app-menu="Evaluation">
                        <i class="fa fa-square"></i> Evaluations
                    </a>
                </c:if>
            </li>

            <li class="nav-item">
                <c:if test="${sessionScope.con.module_activate eq 'Question'}">
                    <a class="nav-link active" href="Javascript:;" data-app-menu="Question">
                        <i class="fa fa-question-circle"></i> Questions
                    </a>
                </c:if>
                <c:if test="${sessionScope.con.module_activate ne 'Question'}">
                    <a class="nav-link app-menu-link" href="Javascript:;" data-app-menu="Question">
                        <i class="fa fa-question-circle"></i> Questions
                    </a>
                </c:if>
            </li>

            <li class="nav-item">
                <c:if test="${sessionScope.con.module_activate eq 'Answer'}">
                    <a class="nav-link active" href="Javascript:;" data-app-menu="Answer">
                        <i class="fa fa-check-circle"></i> Answers
                    </a>
                </c:if>
                <c:if test="${sessionScope.con.module_activate ne 'Answer'}">
                    <a class="nav-link app-menu-link" href="Javascript:;" data-app-menu="Answer">
                        <i class="fa fa-check-circle"></i> Answers
                    </a>
                </c:if>
            </li>

            <li class="nav-item">
                <c:if test="${sessionScope.con.module_activate eq 'Student'}">
                    <a class="nav-link active" href="Javascript:;" data-app-menu="Student">
                        <i class="fa fa-users"></i> Students
                    </a>
                </c:if>
                <c:if test="${sessionScope.con.module_activate ne 'Student'}">
                    <a class="nav-link app-menu-link" href="Javascript:;" data-app-menu="Student">
                        <i class="fa fa-users"></i> Students
                    </a>
                </c:if>
            </li>

            <li class="nav-item">
                <c:if test="${sessionScope.con.module_activate eq 'Teacher'}">
                    <a class="nav-link active" href="Javascript:;" data-app-menu="Teacher">
                        <i class="fa fa-users"></i> Teachers
                    </a>
                </c:if>
                <c:if test="${sessionScope.con.module_activate ne 'Teacher'}">
                    <a class="nav-link app-menu-link" href="Javascript:;" data-app-menu="Teacher">
                        <i class="fa fa-users"></i> Teachers
                    </a>
                </c:if>
            </li>

            <li class="nav-item">
                <c:if test="${sessionScope.con.module_activate eq 'User'}">
                    <a class="nav-link active" href="Javascript:;" data-app-menu="User">
                        <i class="fa fa-user-circle"></i> Users
                    </a>
                </c:if>
                <c:if test="${sessionScope.con.module_activate ne 'User'}">
                    <a class="nav-link app-menu-link" href="Javascript:;" data-app-menu="User">
                        <i class="fa fa-user-circle"></i> Users
                    </a>
                </c:if>
            </li>
        
        </c:if>
            
        <c:if test="${sessionScope.con.person.isIsStudent()}">
            <li class="nav-item">
                <c:if test="${sessionScope.con.module_activate eq 'SessionTest'}">
                    <a class="nav-link active" href="Javascript:;" data-app-menu="SessionTest">
                        <i class="fa fa-pencil-square"></i> Session Test
                    </a>
                </c:if>
                <c:if test="${sessionScope.con.module_activate ne 'SessionTest'}">
                    <a class="nav-link app-menu-link" href="SessionTest" data-app-menu="SessionTest">
                        <i class="fa fa-pencil-square"></i> Session Test
                    </a>
                </c:if>
            </li>
        </c:if>
        
    </ul>

</div>