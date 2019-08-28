<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>        

        <jsp:include page="_partial/_style.html" /> 

        <!-- Title of the page -->
        <title>Error page</title>

    </head>

    <body class="bg-light">

        <main role="main" class="container">
            <h1 class="mt-5">
                <i class="fa fa-warning"></i> Error ${requestScope.codeError}
            </h1>
            <p class="lead">
                <c:if test="${requestScope.codeError eq 404}">
                    Page not found!
                </c:if>
                <c:if test="${requestScope.codeError ne 404}">
                    An error was occured during the process.
                </c:if>
            </p>
            <p>
                Go to the <a href="Authentication">login page</a>.
            </p>
        </main>

        <footer class="footer">
            <div class="container">
                <p>
                    © 2018 - ISIEB, <i>${applicationScope.appSettings.app_name}</i> <i><small>${applicationScope.appSettings.app_version}</small></i> 
                    <a class="link-gray ml-4" href="${applicationScope.appSettings.url_web_site}">
                        Contacter ISIEB
                    </a>
                </p>
            </div>
        </footer>

        <jsp:include page="_partial/_script.html" />

    </body>

</html>
