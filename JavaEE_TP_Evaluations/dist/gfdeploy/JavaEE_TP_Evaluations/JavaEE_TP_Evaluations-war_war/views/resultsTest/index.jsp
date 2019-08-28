<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>        

        <jsp:include page="_partial/_style.html" /> 

        <!-- Title of the page -->
        <title>Evaluations</title>

    </head>

    <body class="app-body">

        <jsp:include page="../_shared/_top.jsp" /> 

        <div class="container body-content">

            <div class="pt-3"></div>

            <div class="card">

                <jsp:include page="../_shared/_modules.jsp" /> 

                <div class="card-body">

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
                                        <th>Matricule</th>
                                        <th>Firstname</th>
                                        <th>Lastname</th>
                                        <th>Evaluation</th>
                                        <th>Question</th>
                                        <th>Answer</th>
                                        <th>Marks</th>
                                        <th width="100px;">Status</th>
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
                                    "module": "Student_Answer"
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
                                {"data": "col_6"},
                                {"data": "col_7"},
                                {"data": "col_8"},
                                {"data": "col_9"},
                                {"data": "col_10", "class": "text-center"},
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
                                loadCSSTable('evaluation');
                            }
                        );

                        // -- Set responsive table -- //
                        new $.fn.dataTable.FixedHeader(table);

                    } catch (e) {
                        console.error(e.message);
                    }

                }
            );

        </script>

        <jsp:include page="../_shared/_modals.jsp" /> 

    </body>

</html>
