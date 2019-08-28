<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>        

        <jsp:include page="_partial/_style.html" /> 

        <!-- Title of the page -->
        <title>SessionTest</title>

    </head>

    <body class="app-body">

        <jsp:include page="../_shared/_top.jsp" /> 

        <div class="container body-content">

            <div class="pt-3"></div>

            <div class="card">

                <jsp:include page="../_shared/_modules.jsp" /> 

                <div id="divTest" class="card-body">

                    <div class="jumbotron mx-4 my-4 bg-light">
                        <h1 class="display-4">
                            <i class="fa fa-pencil fa-2x"></i> Session test
                        </h1>
                        <p class="lead">
                            Select an evaluation and launch the test.
                        </p>
                        <hr class="my-4">
                        <p>
                            <b>NB:</b> Your test will be not validate if it is not canceled. And pay attention at your time, when it will finish your current test will be avorted too with your actually questions.
                        </p>
                        <form method="post" id="form_evaluation" name="form_evaluation" data-parsley-validate>
                            <div class="row">
                                <div class="col-12 col-md-4">
                                    <div class="form-group">
                                        <select class="form-control form-control-lg" id="form_id_evaluation" name="id_evaluation" required>
                                            <option value="">Select</option>
                                            <c:forEach items="${requestScope.comboBoxData[0]}" var="val" varStatus="list">
                                                <option value="${val.id}">${val.title}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <button class="btn btn-success btn-lg" type="submit">
                                            <i class="fa fa-sign-in"></i> Launch
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
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
