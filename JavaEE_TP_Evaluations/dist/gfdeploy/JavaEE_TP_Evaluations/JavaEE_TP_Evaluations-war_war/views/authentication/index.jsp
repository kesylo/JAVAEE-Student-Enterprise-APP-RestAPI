<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>        

        <jsp:include page="_partial/_style.html" /> 

        <!-- Title of the page -->
        <title>Authentication</title>

    </head>

    <body class="bg-light">

        <div class="header header-logged-out width-full pt-5 pb-4" role="banner">
            <div class="container clearfix width-full text-center">
                <a class="header-logo" href="https://github.com/">
                    <img src='resources/images/png/iroll.png' style="height: 50px;" class="img-responsive" />
                </a>
            </div>
        </div>

        <div class="container">
            <div class="auth-form px-3">
                <form role="form" id="form" method="POST" data-parsley-validate>
                    <div class="col-xl-12 text-center">
                        <p class="h4 mb-3">
                            Login to ISIEB
                        </p>
                    </div>
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="form-group">
                                <label for="login">Account :</label>
                                <input class="form-control" placeholder="Account..." name="login" id="login" type="text"
                                       required autofocus />
                            </div>
                            <div class="form-group">
                                <label for="password">Password :</label>
                                <input class="form-control" placeholder="Password..." name="password" id="password"
                                       type="password" required />
                            </div>
                            <div class="pt-3">
                                <button id="bt_submit" type="submit" class="btn btn-lg btn-success btn-block">
                                    Login <i class="fa fa-sign-in"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="text-center">
                        <p>
                            © 2018 - ISIB, <i>${applicationScope.appSettings.app_name}</i> <i><small>${applicationScope.appSettings.app_version}</small></i> 
                            <a class="link-gray ml-4" href="${applicationScope.appSettings.url_web_site}">
                                Contacter ISIB
                            </a>
                        </p>
                    </div>
                </form>
            </div>
        </div>

        <jsp:include page="_partial/_script.html" /> 

        <script type="text/javascript">

            $(
                function () {
                    // -- On form submit -- //
                    $("#form").submit(
                            function (event) {
                                // -- Annuler l'action par défaut -- //
                                event.preventDefault();

                                // -- Variable -- //
                                var form = $(this);

                                // -- Check if form is validate -- //
                                if (!form.parsley().isValid()) {
                                    return false;
                                }

                                // -- Afficher/Cacher l'etat de chargement de la page -- //
                                displayLoadingPage(true);

                                // -- Ajax -- //
                                $.ajax({
                                    type: "POST",
                                    url: 'Authentication',
                                    data: form.serialize() + '&action=login',
                                    success: function (result) {
                                        if (result.isSuccess) {
                                            // -- Redirection -- //
                                            redirection(result.data);
                                        } else {
                                            // -- Reset le formulaire -- //
                                            form[0].reset();
                                            // -- Notifier -- //
                                            messageBox(result);
                                        }
                                        // -- Afficher/Cacher l'etat de chargement de la page -- //
                                        displayLoadingPage(false);
                                    },
                                    error: function () {
                                        // -- Afficher/Cacher l'etat de chargement de la page -- //
                                        displayLoadingPage(false);
                                        // -- Notifier -- //
                                        messageBox();
                                    }
                                });
                            }
                    );
                }
            );

        </script>

        <jsp:include page="../_shared/_modals.jsp" /> 

    </body>

</html>
