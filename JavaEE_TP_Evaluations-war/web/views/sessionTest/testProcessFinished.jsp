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

                    <div class="jumbotron mx-4 my-4 bg-white">
                        <h1 class="display-4 text-success">
                            <i class="fa fa-check fa-2x"></i> Session test finished
                        </h1>
                        <p class="lead">
                            Now you can try anothe one or get the transcript of this test. Just follow the action links under.
                        </p>
                        <hr class="my-4">
                        <div class="form-group">
                            <a class="btn btn-success btn-lg" href="SessionTest">
                                <i class="fa fa-refresh"></i> Try othe one
                            </a>
                            <a class="btn btn-info btn-lg ml-3" href="Dashboard">
                                <i class="fa fa-file-text-o"></i> Get the transcript
                            </a>
                        </div>
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

                    }
            );

        </script>

        <jsp:include page="../_shared/_modals.jsp" /> 

    </body>

</html>
