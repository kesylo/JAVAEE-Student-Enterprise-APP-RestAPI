<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>        

        <jsp:include page="_partial/_style.html" /> 

        <!-- Title of the page -->
        <title>Dashboard history</title>

    </head>

    <body class="app-body">

        <jsp:include page="../_shared/_top.jsp" /> 

        <div class="container body-content">

            <div class="pt-3"></div>

            <div class="card">

                <jsp:include page="../_shared/_modules.jsp" /> 

                <div class="card-body">

                    <div class="row">

                        <div class="col-12">

                            <div class="card bg-light">
                                <div class="card-body ">
                                    <h1 class="display-4">
                                        Student <i class="fa fa-file-text-o fa-2x float-right"></i>
                                    </h1>
                                    <p class="lead">
                                        Select a student and display his transcript.
                                    </p>
                                    <hr class="my-4">
                                    <form method="post" id="form_student" action="Dashboard" name="form_student" data-parsley-validate>
                                        <div class="row">
                                            <div class="col-12 col-md-4">
                                                <div class="form-group">
                                                    <select class="form-control form-control-lg" id="form_id_student" name="id_student" required>
                                                        <option value="">Select</option>
                                                        <c:forEach items="${requestScope.comboBoxData[0]}" var="val" varStatus="listStudent">
                                                            <option value="${val.id}">${val.firstname} ${val.lastname}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <button class="btn btn-success btn-lg" type="submit">
                                                        <i class="fa fa-list"></i> Display
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <c:if test="${requestScope.student != null}">
                            <!--
                            <div class="col-12">

                                <div class="card bg-white mt-3">
                                    <div class="card-body ">
                                        <h2 class="display-5">
                                            <i class="fa fa-user"></i> KEMING Loic <span class="float-right font-weight-bold text-info">5/10</span>
                                        </h2>
                                        <hr class="my-4">
                                        <div class="form-group">
                                            <button class="btn btn-light btn-lg" type="button" onclick="messageBox();">
                                                <i class="fa fa-print"></i> Print
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                
                            </div>
                            -->
                            
                            <div class="col-12 pt-3">

                                <div class="card">

                                    <div class="card-body">

                                        <div class="row pb-5 px-5 pt-3">
                                            <div class="col-md-6">
                                                <p class="font-weight-bold mb-4 h4">Student Information</p>
                                                <p class="mb-1 h5">
                                                    <b>Matricule</b>: ${requestScope.student.matricule}
                                                </p>
                                                <p class="mb-1 h5">
                                                    <b>Name</b>: ${requestScope.student.firstname} ${requestScope.student.lastname}
                                                </p>
                                                <p class="mb-1 h5">
                                                    <b>Age</b>: ${sessionScope.con.getAge(requestScope.student.birthday)} years old
                                                </p>
                                            </div>

                                            <div class="col-md-6 text-right">
                                                <p class="font-weight-bold mb-4 h4">Address details</p>
                                                <p class="mb-1 h5">
                                                    <b>Address</b>: ${requestScope.student.address}
                                                </p>
                                                <p class="mb-1 h5">
                                                    <b>Phone</b>: ${requestScope.student.mobile_phone}
                                                </p>
                                                <p class="mb-1 h5">
                                                    <b>Email</b>: ${requestScope.student.email}
                                                </p>
                                            </div>
                                        </div>

                                        <div class="row px-5 pb-5">
                                            <div class="col-md-12">
                                                <c:set var="lazyObject" value="${sessionScope.con.getLazyObjectEvaluation(requestScope.student_answers)}" />
                                                <c:forEach items="${lazyObject.prop_0}" var="evaluation" varStatus="listEvaluation">
                                                    <table class="table table-sm">
                                                        <thead>
                                                            <tr>
                                                                <th class="border-0 text-uppercase small font-weight-bold" colspan="6">
                                                                    N°${listEvaluation.count}: ${evaluation.title}
                                                                </th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${evaluation.questions}" var="question" varStatus="listQuestion">
                                                                <c:if test="${sessionScope.con.isAswered(question.id, requestScope.student_answers)}">
                                                                    <c:set var="truthAnswer" value="${sessionScope.con.getAswered(question.answers, requestScope.student_answers)}" />
                                                                    <tr>
                                                                        <td style="width: 25px;">-</td>
                                                                        <td>Question N°${listQuestion.count}: </td>
                                                                        <td>${question.title} ?</td>
                                                                        <td style="width: 100px;" class="font-weight-bold text-center">${truthAnswer.title}</td>
                                                                        <td style="width: 100px;" class="text-center">[${question.marks}]</td>
                                                                        <td style="width: 100px;" class="text-center">${truthAnswer.truth   ? "<i class=\"fa fa-check text-success\"></i>" 
                                                                                                                                            : "<i class=\"fa fa-remove text-danger\"></i>"}</td>
                                                                    </tr>
                                                                </c:if>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </c:forEach>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row-reverse bg-light text-dark p-4">
                                            <div class="py-3 px-5 text-right">
                                                <div class="mb-2">Max marks</div>
                                                <div class="h2 font-weight-light text-info">${lazyObject.prop_3}</div>
                                            </div>

                                            <div class="py-3 px-5 text-right">
                                                <div class="mb-2">You marks</div>
                                                <div class="h2 font-weight-bold">${lazyObject.prop_2}</div>
                                            </div>

                                            <div class="py-3 px-5 text-right">
                                                <div class="mb-2">Status</div>
                                                <div class="h2 font-weight-light">${lazyObject.prop_1}</div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </div>
                            
                        </c:if>

                    </div>

                </div>

            </div>

            <jsp:include page="../_shared/_bottom.jsp" /> 

        </div>

        <jsp:include page="_partial/_script.html" /> 

        <script type="text/javascript">

            // -- Lorsque le document est chargé -- //
            $(
                function () {

                    if ('${requestScope.error ne null}'.toLowerCase() === 'true') {
                        messageBox({isSuccess: false, message: '${requestScope.error}'});
                    }

                }
            );

        </script>

        <jsp:include page="../_shared/_modals.jsp" /> 

    </body>

</html>
