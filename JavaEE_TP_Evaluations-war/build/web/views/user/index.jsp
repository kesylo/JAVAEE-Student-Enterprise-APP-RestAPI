<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>        

        <jsp:include page="_partial/_style.html" /> 

        <!-- Title of the page -->
        <title>Users</title>

    </head>

    <body class="app-body">

        <jsp:include page="../_shared/_top.jsp" /> 

        <div class="container body-content">

            <div class="pt-3"></div>

            <div class="card">

                <jsp:include page="../_shared/_modules.jsp" /> 

                <div class="card-body">

                    <div class="row">
                        <div class="col-12 col-sm-9">
                            <h5 class="card-password">
                                <i class="fa fa-cogs"></i> User management
                            </h5>
                        </div>
                        <div class="col-12 col-sm-3">
                            <a class="btn btn-light float-right app-btn-print ml-1" href="Javascript:;">
                                <i class="fa fa-print"></i> Print
                            </a>
                            <a class="btn btn-light float-right" href="Javascript:;" data-toggle="modal" data-target="#modal_form">
                                <i class="fa fa-plus"></i> New
                            </a>
                        </div>
                    </div>

                    <div class="pt-3"></div>

                    <div class="row">

                        <div class="col-xl-12">

                            <!-- Table des données -->
                            <table id="table-donnee" class="table table-sm table-striped nowrap jambo_table bulk_action" style="width:100%">

                                <thead class="app-table-head">
                                    <tr>
                                        <th width="20px">
                                            <input type="checkbox" id="check-all" class="flat-iCheck" />
                                        </th>
                                        <th width="50px;">N°</th>
                                        <th>Login</th>
                                        <th>Password</th>
                                        <th width="100px;">Status</th>
                                        <th width="200px;">Person</th>
                                        <th width="150px;">Action</th>
                                    </tr>
                                </thead>

                                <tbody></tbody>

                            </table>

                        </div>

                    </div>

                </div>

            </div>
                
            <jsp:include page="../_shared/_bottom.jsp" /> 

        </div>

        <jsp:include page="_partial/_script.html" /> 

        <script type="text/javascript">

            var table = $('#table-donnee');

            // -- Modify -- //
            function table_donnee_modifier(id) {

                // -- Afficher le chargement -- //
                displayLoadingPage(true);

                // -- Ajax -- //
                $.ajax({
                    type: "POST",
                    url: 'User',
                    data: {
                        id: id,
                        action: 'getDataById'
                    },
                    success: function(result) {
                        // -- Tester si le traitement s'est bien effectué -- //
                        if (result.isSuccess) {
                            // -- Update modal form -- //
                            $('#form_id').val(result.data.col_0);
                            $('#form_login').val(result.data.col_1);
                            $('#form_password').val(result.data.col_2);
                            $('#form_password_confirmation').val(result.data.col_2);
                            $('#form_enabled').val(result.data.col_3.toString());
                            $('#form_id_student').val(result.data.col_4);
                            $('#form_id_teacher').val(result.data.col_5);

                            // -- Set password -- //
                            $('#modal_form_titre').html('<i class="fa fa-plus"></i> Update data');

                            // -- Show modal -- //
                            $('#modal_form').modal('show');
                        } else {
                            // -- Message -- //
                            messageBox(result);
                        }
                        // -- Afficher le chargement -- //
                        displayLoadingPage();
                    },
                    error: function() {
                        // -- Afficher le chargement -- //
                        displayLoadingPage();
                        // -- Notifier -- //
                        messageBox();
                    }
                });

            }

            // -- Delete -- //
            function table_donnee_supprimer(id) {

                // -- Ecouter la réponse du message de confirmation -- //
                if (!$Confirmation_message_box) {
                    // -- Afficher le message d'action -- //
                    confirmationYesOrNo(null, null, function() {
                        table_donnee_supprimer(id);
                    });
                    // -- Annuler l'action -- //
                    return false;
                }

                // -- Afficher le chargement -- //
                displayLoadingPage(true);

                // -- Ajax -- //
                $.ajax({
                    type: "POST",
                    url: 'User',
                    data: {
                        "id": id,
                        "action": 'delete'
                    },
                    success: function(result) {
                        // -- Tester si le traitement s'est bien effectué -- //
                        if (result.isSuccess) {
                            // -- Reload table -- //
                            table.DataTable().ajax.reload(function() { });
                        } else {
                            // -- Message -- //
                            messageBox(result);
                        }
                        // -- Afficher le chargement -- //
                        displayLoadingPage();
                    },
                    error: function() {
                        // -- Afficher le chargement -- //
                        displayLoadingPage();
                        // -- Notifier -- //
                        messageBox();
                    }
                });

            }

            // -- Lorsque le document est chargé -- //
            $(
                function() {

                    // -- Traitement table -- //
                    try {

                        // -- Table d'affichage des données -- //
                        table.DataTable({
                            "lengthMenu": [[5, 10, 25, 50, 100, -1], [5, 10, 25, 50, 100, 'All']],
                            "scrollCollapse": true,
                            "paging": true,
                            "searching": true,
                            "autoWidth": false,
                            "responsive": true,
//                            "language": {
//                                "url": 'Base/?action=dataTablesLang'
//                            },
                            "ajax": {
                                "url": 'Base',
                                "type": 'POST',
                                "data": {
                                    "action": "dataTablesValue",
                                    "module": "User"
                                },
                                "dataSrc": function(result) {
                                    return result.data;
                                }
                            },
                            "columns": [
                                {"data": "col_1", "class": "text-center"},
                                {"data": "col_2"},
                                {"data": "col_3"},
                                {"data": "col_4"},
                                {"data": "col_5"},
                                {"data": "col_6", "class": "text-center"},
                                {"data": "col_7", "class": "text-center"},
                            ]
                        });

                        // -- Lorsqu'un click survient sur une ligne de la table -- //
//                        $('#table-donnee tbody').on('click', 'tr',
//                            function() {
//                                // -- Selectionner une ligne de la table -- //
//                                tableLineSelection($(this));
//                            }
//                        );
                
                        // -- Lorsque la table est redessiné -- //
                        table.on('draw.dt',
                            function () {
                                // -- Fonction pour initiliser les style css javascript des tables -- //
                                loadCSSTable('user');
                            }
                        );

                        // -- Set responsive table -- //
                        new $.fn.dataTable.FixedHeader(table);

                    } catch (e) {
                        console.error(e.message);
                    }

                    // -- Comportement lorsque le modal du formulaire se ferme -- //
                    try {

                        $('#modal_form').on('hidden.bs.modal',
                            function(e) {

                                // -- Reinitialiser le formulaire -- //
                                $('#form')[0].reset();

                                // -- Reinitialiser le id -- //
                                $('#form_id').val(0);

                                // -- Set password -- //
                                $('#modal_form_titre').html('<i class="fa fa-plus"></i> Add new data');

                                // -- Suppression de l'alert message box -- //
                                $('#appAlert').html(null);

                                // -- Suppression de l'alert de confirmation -- //
                                $('#appAlert_Message_Box').html(null);

                                // -- Activer/Desactiver formulaire -- //
                                enableOrDisableForm($('#form').attr('id'), false);

                            }
                        );

                    } catch (e) {
                        console.error(e.message);
                    }

                    // -- Soumission du formulaire ajout/modification -- //
                    try {

                        // -- Soumet le formulaire -- //
                        $('#form').on("submit",
                            function(e) {
                                // -- Désactiver la soumission -- //
                                e.preventDefault();

                                // If the form is valid
                                if ($('#form').parsley().isValid()) {

                                    // -- Ecouter la réponse du message de confirmation -- //
                                    if (!$Confirmation_message_box) {
                                        // -- Afficher le message d'action -- //
                                        confirmationAlertYesOrNo(null, null, $('#form').attr('id'), null);
                                        // -- Annuler l'action -- //
                                        return false;
                                    }

                                    // -- Définition de l'action de traitement -- //
                                    var action_ajouter = (parseInt($('#form_id').val()) == 0);

                                    // -- Afficher le chargement -- //
                                    displayLoadingPage(true);

                                    // -- Ajax -- //
                                    $.ajax({
                                        type: "POST",
                                        url: 'User',
                                        data: $('#form').serialize() + '&action=' + (action_ajouter ? 'create'
                                                                                                    : 'update'),
                                        success: function(result) {
                                            // -- Tester si le traitement s'est bien effectué -- //
                                            if (result.isSuccess) {
                                                // -- Reload table -- //
                                                table.DataTable().ajax.reload(function() { });
                                                // -- Fermer le modal -- //
                                                $('#modal_form').modal('hide');
                                            }
                                            else {
                                                // -- Afficher une alerte sur un element -- //
                                                alert(result);
                                            }
                                            // -- Afficher le chargement -- //
                                            displayLoadingPage();
                                        },
                                        error: function() {
                                            // -- Fermer le modal -- //
                                            $('#modal_form').modal('hide');
                                            // -- Afficher le chargement -- //
                                            displayLoadingPage();
                                            // -- Notifier -- //
                                            alert();
                                        }
                                    });

                                }
                            }
                        );

                    } catch (e) {
                        console.error(e.message);
                    }
                    
                    try { 
                        
                        // -- Soumet le formulaire -- //
                        $('.app-btn-print').on("click",
                            function(e) {
                                // -- Désactiver la soumission -- //
                                e.preventDefault();

                                // -- Notifier -- //
                                messageBox({isSuccess: null, message: 'Process is not finished!'});
                            }
                        );
                        
                    } catch(e) {
                        console.error(e.message);
                    }

                }
            );

        </script>

        <jsp:include page="../_shared/_modals.jsp" /> 

    </body>

</html>
