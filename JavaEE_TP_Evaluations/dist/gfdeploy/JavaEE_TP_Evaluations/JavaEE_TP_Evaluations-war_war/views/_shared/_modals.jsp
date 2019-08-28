<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Modal Message -->
<div class="modal fade" id="modal_message" tabindex="-1" role="dialog" aria-labelledby="modal_message_titre" aria-hidden="true">
    <div id="modal_message_taille" class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <!-- Header -->
                <p class="font-weight-bold lead">
                    <!-- Title -->
                    <span id="modal_message_titre"></span>
                    <!-- Close bouton -->
                    <button type="button" class="close float-right" data-dismiss="modal" aria-hidden="true">
                        <i class="fa fa-times"></i>
                    </button>
                </p>
                <!-- Message -->
                <p class="text-center" id="modal_message_text"></p>
            </div>
            <div class="modal-footer">
                <button id="modal_message_btn_fermer" type="button" class="btn btn-sm btn-light float-right" data-dismiss="modal">
                    <i class="fa fa-remove"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Message Question -->
<div class="modal fade" id="modal_message_question" role="alert" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <div class="gbalert" role="alert">
                    <!-- Bouton caché d'activation -->
                    <span id="modal_message_question_reponse" hidden="hidden">false</span>
                    <!-- ./Bouton caché d'activation -->
                    <span class="fa fa-info-circle" aria-hidden="true"></span>
                    <span class="sr-only"></span>
                    <span class="gbtext-bold">Confirmation</span>
                    <br />
                    <span id="modal_message_question_message"></span> ?
                </div>
            </div>
            <div class="modal-footer">
                <button id="modal_message_question_bouton_non" type="button" class="btn btn-sm btn-default pull-left" data-dismiss="modal">
                    <i class="fa fa-fw fa-close"></i> No
                </button>
                <button id="modal_message_question_bouton_oui" type="button" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
                    <i class="fa fa-fw fa-check"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Form create or add -->
<c:if test="${sessionScope.con.module_activate eq 'Evaluation'}">
    <div class="modal fade" id="modal_form" role="alert" data-backdrop="static" data-keyboard="false">

        <div class="modal-dialog modal-sm">

            <div class="modal-content">

                <div class="modal-body">

                    <!-- Header -->
                    <p class="font-weight-bold" style="font-size: 18px;">
                        <!-- Title -->
                        <span id="modal_form_titre">
                            <i class="fa fa-info-circle text-info"></i> Form
                        </span>
                        <!-- Close bouton -->
                        <button type="button" class="close float-right font-weight-bold" data-dismiss="modal" aria-hidden="true">
                            <i class="fa fa-times"></i>
                        </button>
                    </p>

                    <!-- Body -->
                    <form method="post" id="form" name="form" data-parsley-validate>

                        <div class="row">

                            <!-- Id -->
                            <input type="hidden" id="form_id" name="id" value="0" />

                            <!-- ligne -->
                            <div class="col-12">
                                <div class="form-group">
                                    <label for="form_code">Code :</label>
                                    <input class="form-control" type="text" id="form_code" name="code" placeholder="Code.*" required />
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="form-group">
                                    <label for="form_title">Title :</label>
                                    <input class="form-control" type="text" id="form_title" name="title" placeholder="Title.*" required />
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="form-group">
                                    <label for="form_duration">Duration :</label>
                                    <input class="form-control" type="number" min="1" max="180" id="form_duration" name="duration" placeholder="duration.*" required />
                                </div>
                            </div>
                            <!-- ./ligne -->

                        </div>

                    </form>

                    <!--Alert Message -->
                    <div id="appAlert"></div>

                </div>

                <div class="modal-footer">

                    <!-- Bouton de fermiture du modal -->
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">
                        <i class="fa fa-remove"></i> Close
                    </button>

                    <!-- Bouton d'enregistrement du formulaire -->
                    <button class="btn btn-default btn-success pull-right" id="btn-enregistrer" type="submit" form="form">
                        <i class="fa fa-check"></i> Save
                    </button>

                </div>

            </div>

        </div>

    </div>
    <div class="modal fade" id="modal_form_display" role="alert" data-backdrop="static" data-keyboard="false">

        <div class="modal-dialog">

            <div class="modal-content">

                <div class="modal-body">

                    <!-- Header -->
                    <p class="font-weight-bold" style="font-size: 18px;">
                        <!-- Title -->
                        <span>
                            <i class="fa fa-list"></i> Questions
                        </span>
                        <!-- Close bouton -->
                        <button type="button" class="close float-right font-weight-bold" data-dismiss="modal" aria-hidden="true">
                            <i class="fa fa-times"></i>
                        </button>
                    </p>

                    <!-- Body -->
                    <div class="row">
                        
                        <div class="col-12">
                            <h5 class="font-weight-bold" id="title_obj"></h5>
                            <hr>
                        </div>

                        <div class="col-12">
                            
                            <!-- Table des données -->
                            <table id="table-donnee-display" class="table table-sm table-striped nowrap" style="width:100%">

                                <thead class="app-table-head">
                                    <tr>
                                        <th width="50px;">N°</th>
                                        <th>Code</th>
                                        <th>Title</th>
                                        <th>Marks</th>
                                    </tr>
                                </thead>

                                <tbody></tbody>

                            </table>
                            
                        </div>

                    </div>

                </div>

                <div class="modal-footer">

                    <!-- Bouton de fermiture du modal -->
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">
                        <i class="fa fa-remove"></i> Close
                    </button>

                </div>

            </div>

        </div>

    </div>
</c:if>

<c:if test="${sessionScope.con.module_activate eq 'Question'}">
    <div class="modal fade" id="modal_form" role="alert" data-backdrop="static" data-keyboard="false">

        <div class="modal-dialog">

            <div class="modal-content">

                <div class="modal-body">

                    <!-- Header -->
                    <p class="font-weight-bold" style="font-size: 18px;">
                        <!-- Title -->
                        <span id="modal_form_titre">
                            <i class="fa fa-info-circle text-info"></i> Form
                        </span>
                        <!-- Close bouton -->
                        <button type="button" class="close float-right font-weight-bold" data-dismiss="modal" aria-hidden="true">
                            <i class="fa fa-times"></i>
                        </button>
                    </p>

                    <!-- Body -->
                    <form method="post" id="form" name="form" data-parsley-validate>

                        <div class="row">

                            <!-- Id -->
                            <input type="hidden" id="form_id" name="id" value="0" />

                            <!-- ligne -->
                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_code">Code :</label>
                                    <input class="form-control" type="text" id="form_code" name="code" placeholder="Code.*" required />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_title">Title :</label>
                                    <input class="form-control" type="text" id="form_title" name="title" placeholder="Title.*" required />
                                </div>
                            </div>
                            
                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_marks">Marks :</label>
                                    <input class="form-control" type="number" min="1" id="form_marks" name="marks" placeholder="marks.*" required />
                                </div>
                            </div>
                            
                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_id_evaluation">Evaluation :</label>
                                    <select class="form-control" id="form_id_evaluation" name="id_evaluation" required>
                                        <option value="">Select</option>
                                        <c:forEach items="${requestScope.comboBoxData[0]}" var="val" varStatus="list">
                                            <option value="<c:out value="${val.id}" />"><c:out value="${val.title}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <!-- ./ligne -->

                        </div>

                    </form>

                    <!--Alert Message -->
                    <div id="appAlert"></div>

                </div>

                <div class="modal-footer">

                    <!-- Bouton de fermiture du modal -->
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">
                        <i class="fa fa-remove"></i> Close
                    </button>

                    <!-- Bouton d'enregistrement du formulaire -->
                    <button class="btn btn-default btn-success pull-right" id="btn-enregistrer" type="submit" form="form">
                        <i class="fa fa-check"></i> Save
                    </button>

                </div>

            </div>

        </div>

    </div>
    <div class="modal fade" id="modal_form_display" role="alert" data-backdrop="static" data-keyboard="false">

        <div class="modal-dialog">

            <div class="modal-content">

                <div class="modal-body">

                    <!-- Header -->
                    <p class="font-weight-bold" style="font-size: 18px;">
                        <!-- Title -->
                        <span>
                            <i class="fa fa-list"></i> List of answers
                        </span>
                        <!-- Close bouton -->
                        <button type="button" class="close float-right font-weight-bold" data-dismiss="modal" aria-hidden="true">
                            <i class="fa fa-times"></i>
                        </button>
                    </p>

                    <!-- Body -->
                    <div class="row">
                        
                        <div class="col-12">
                            <h5 class="font-weight-bold" id="title_obj"></h5>
                            <hr>
                        </div>

                        <div class="col-12">
                            
                            <!-- Table des données -->
                            <table id="table-donnee-display" class="table table-sm table-striped nowrap" style="width:100%">

                                <thead class="app-table-head">
                                    <tr>
                                        <th width="50px;">N°</th>
                                        <th>Code</th>
                                        <th>Title</th>
                                        <th>Is truth</th>
                                    </tr>
                                </thead>

                                <tbody></tbody>

                            </table>
                            
                        </div>

                    </div>

                </div>

                <div class="modal-footer">

                    <!-- Bouton de fermiture du modal -->
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">
                        <i class="fa fa-remove"></i> Close
                    </button>

                </div>

            </div>

        </div>

    </div>
</c:if>

<c:if test="${sessionScope.con.module_activate eq 'Answer'}">
    <div class="modal fade" id="modal_form" role="alert" data-backdrop="static" data-keyboard="false">

        <div class="modal-dialog">

            <div class="modal-content">

                <div class="modal-body">

                    <!-- Header -->
                    <p class="font-weight-bold" style="font-size: 18px;">
                        <!-- Title -->
                        <span id="modal_form_titre">
                            <i class="fa fa-info-circle text-info"></i> Form
                        </span>
                        <!-- Close bouton -->
                        <button type="button" class="close float-right font-weight-bold" data-dismiss="modal" aria-hidden="true">
                            <i class="fa fa-times"></i>
                        </button>
                    </p>

                    <!-- Body -->
                    <form method="post" id="form" name="form" data-parsley-validate>

                        <div class="row">

                            <!-- Id -->
                            <input type="hidden" id="form_id" name="id" value="0" />

                            <!-- ligne -->
                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_code">Code :</label>
                                    <input class="form-control" type="text" id="form_code" name="code" placeholder="Code.*" required />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_title">Title :</label>
                                    <input class="form-control" type="text" id="form_title" name="title" placeholder="Title.*" required />
                                </div>
                            </div>
                            
                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_truth">Yes/No :</label>
                                    <select class="form-control" id="form_truth" name="truth" required>
                                        <option value="">Select</option>
                                        <option value="true">Yes</option>
                                        <option value="false">No</option>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_id_question">Question :</label>
                                    <select class="form-control" id="form_id_question" name="id_question" required>
                                        <option value="">Select</option>
                                        <c:forEach items="${requestScope.comboBoxData[0]}" var="val" varStatus="list">
                                            <option value="<c:out value="${val.id}" />"><c:out value="${val.title}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <!-- ./ligne -->

                        </div>

                    </form>

                    <!--Alert Message -->
                    <div id="appAlert"></div>

                </div>

                <div class="modal-footer">

                    <!-- Bouton de fermiture du modal -->
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">
                        <i class="fa fa-remove"></i> Close
                    </button>

                    <!-- Bouton d'enregistrement du formulaire -->
                    <button class="btn btn-default btn-success pull-right" id="btn-enregistrer" type="submit" form="form">
                        <i class="fa fa-check"></i> Save
                    </button>

                </div>

            </div>

        </div>

    </div>
</c:if>

<c:if test="${sessionScope.con.module_activate eq 'User'}">
    <div class="modal fade" id="modal_form" role="alert" data-backdrop="static" data-keyboard="false">

        <div class="modal-dialog">

            <div class="modal-content">

                <div class="modal-body">

                    <!-- Header -->
                    <p class="font-weight-bold" style="font-size: 18px;">
                        <!-- Title -->
                        <span id="modal_form_titre">
                            <i class="fa fa-info-circle text-info"></i> Form
                        </span>
                        <!-- Close bouton -->
                        <button type="button" class="close float-right font-weight-bold" data-dismiss="modal" aria-hidden="true">
                            <i class="fa fa-times"></i>
                        </button>
                    </p>

                    <!-- Body -->
                    <form method="post" id="form" name="form" data-parsley-validate>

                        <div class="row">

                            <!-- Id -->
                            <input type="hidden" id="form_id" name="id" value="0" />

                            <!-- ligne -->
                            <div class="col-12">
                                <div class="form-group">
                                    <label for="form_login">Login :</label>
                                    <input class="form-control" type="text" id="form_login" name="login" placeholder="Login.*" required />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_password">Password :</label>
                                    <input class="form-control" type="password" id="form_password" name="password" placeholder="Password.*" required />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_password_confirmation">Password confirmation :</label>
                                    <input class="form-control" type="password" id="form_password_confirmation" data-parsley-equalto="#form_password" placeholder="Password.*" required />
                                </div>
                            </div>
                            
                            <div class="col-12">
                                <div class="form-group">
                                    <label for="form_enabled">Status :</label>
                                    <select class="form-control" id="form_enabled" name="enabled" required>
                                        <option value="">Select</option>
                                        <option value="true">Enabled</option>
                                        <option value="false">Disabled</option>
                                    </select>
                                </div>
                            </div>
                            
                            <%--                        
                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_id_student">Student :</label>
                                    <select class="form-control" id="form_id_student" name="id_student" required>
                                        <option value="">Select</option>
                                        <c:forEach items="${sessionScope.con.comboBoxData[0]}" var="val" varStatus="list">
                                            <option value="<c:out value="${val.id}" />"><c:out value="${val.firstname} ${val.lastname}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_id_teacher">Teacher :</label>
                                    <select class="form-control" id="form_id_teacher" name="id_teacher" required>
                                        <option value="">Select</option>
                                        <c:forEach items="${sessionScope.con.comboBoxData[1]}" var="val" varStatus="list">
                                            <option value="<c:out value="${val.id}" />"><c:out value="${val.firstname} ${val.lastname}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            --%>
                            <!-- ./ligne -->

                        </div>

                    </form>

                    <!--Alert Message -->
                    <div id="appAlert"></div>

                </div>

                <div class="modal-footer">

                    <!-- Bouton de fermiture du modal -->
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">
                        <i class="fa fa-remove"></i> Close
                    </button>

                    <!-- Bouton d'enregistrement du formulaire -->
                    <button class="btn btn-default btn-success pull-right" id="btn-enregistrer" type="submit" form="form">
                        <i class="fa fa-check"></i> Save
                    </button>

                </div>

            </div>

        </div>

    </div>
</c:if>

<c:if test="${sessionScope.con.module_activate eq 'Student'}">
    <div class="modal fade" id="modal_form" role="alert" data-backdrop="static" data-keyboard="false">

        <div class="modal-dialog">

            <div class="modal-content">

                <div class="modal-body">

                    <!-- Header -->
                    <p class="font-weight-bold" style="font-size: 18px;">
                        <!-- Title -->
                        <span id="modal_form_titre">
                            <i class="fa fa-info-circle text-info"></i> Form
                        </span>
                        <!-- Close bouton -->
                        <button type="button" class="close float-right font-weight-bold" data-dismiss="modal" aria-hidden="true">
                            <i class="fa fa-times"></i>
                        </button>
                    </p>

                    <!-- Body -->
                    <form method="post" id="form" name="form" data-parsley-validate>

                        <div class="row">

                            <!-- Id -->
                            <input type="hidden" id="form_id" name="id" value="0" />

                            <!-- ligne -->
                            <div class="col-12">
                                <div class="form-group">
                                    <label for="form_matricule">Matricule :</label>
                                    <input class="form-control" type="text" id="form_matricule" name="matricule" placeholder="Matricule.*" required />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_firstname">Firstname :</label>
                                    <input class="form-control" type="text" id="form_firstname" name="firstname" placeholder="Firstname.*" required />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_lastname">Lastname :</label>
                                    <input class="form-control" type="text" id="form_lastname" name="lastname" placeholder="Lastname.*" />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_address">Address :</label>
                                    <input class="form-control" type="text" id="form_address" name="address" placeholder="Address.*" />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_mobile_phone">Mobile :</label>
                                    <input class="form-control" type="text" id="form_mobile_phone" name="mobile_phone" placeholder="Mobile.*" />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_email">Email :</label>
                                    <input class="form-control" type="email" id="form_email" name="email" placeholder="Email.*" />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_birthday">Birthday :</label>
                                    <input class="form-control app-date-picker" type="text" id="form_birthday" name="birthday" placeholder="dd/MM/yyyy.*" />
                                </div>
                            </div>
                                                
                            <div class="col-12">
                                <div class="form-group">
                                    <label for="form_id_user">User :</label>
                                    <select class="form-control" id="form_id_user" name="id_user">
                                        <option value="">Select</option>
                                        <c:forEach items="${requestScope.comboBoxData[0]}" var="val" varStatus="list">
                                            <option value="<c:out value="${val.id}" />"><c:out value="${val.login}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <!-- ./ligne -->

                        </div>

                    </form>

                    <!--Alert Message -->
                    <div id="appAlert"></div>

                </div>

                <div class="modal-footer">

                    <!-- Bouton de fermiture du modal -->
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">
                        <i class="fa fa-remove"></i> Close
                    </button>

                    <!-- Bouton d'enregistrement du formulaire -->
                    <button class="btn btn-default btn-success pull-right" id="btn-enregistrer" type="submit" form="form">
                        <i class="fa fa-check"></i> Save
                    </button>

                </div>

            </div>

        </div>

    </div>
</c:if>

<c:if test="${sessionScope.con.module_activate eq 'Teacher'}">
    <div class="modal fade" id="modal_form" role="alert" data-backdrop="static" data-keyboard="false">

        <div class="modal-dialog">

            <div class="modal-content">

                <div class="modal-body">

                    <!-- Header -->
                    <p class="font-weight-bold" style="font-size: 18px;">
                        <!-- Title -->
                        <span id="modal_form_titre">
                            <i class="fa fa-info-circle text-info"></i> Form
                        </span>
                        <!-- Close bouton -->
                        <button type="button" class="close float-right font-weight-bold" data-dismiss="modal" aria-hidden="true">
                            <i class="fa fa-times"></i>
                        </button>
                    </p>

                    <!-- Body -->
                    <form method="post" id="form" name="form" data-parsley-validate>

                        <div class="row">

                            <!-- Id -->
                            <input type="hidden" id="form_id" name="id" value="0" />

                            <!-- ligne -->
                            <div class="col-12">
                                <div class="form-group">
                                    <label for="form_matricule">Matricule :</label>
                                    <input class="form-control" type="text" id="form_matricule" name="matricule" placeholder="Matricule.*" required />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_firstname">Firstname :</label>
                                    <input class="form-control" type="text" id="form_firstname" name="firstname" placeholder="Firstname.*" required />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_lastname">Lastname :</label>
                                    <input class="form-control" type="text" id="form_lastname" name="lastname" placeholder="Lastname.*" />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_address">Address :</label>
                                    <input class="form-control" type="text" id="form_address" name="address" placeholder="Address.*" />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_mobile_phone">Mobile :</label>
                                    <input class="form-control" type="text" id="form_mobile_phone" name="mobile_phone" placeholder="Mobile.*" />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_email">Email :</label>
                                    <input class="form-control" type="email" id="form_email" name="email" placeholder="Email.*" />
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label for="form_birthday">Birthday :</label>
                                    <input class="form-control app-date-picker" type="text" id="form_birthday" name="birthday" placeholder="dd/MM/yyyy.*" />
                                </div>
                            </div>
                                                
                            <div class="col-12">
                                <div class="form-group">
                                    <label for="form_id_user">User :</label>
                                    <select class="form-control" id="form_id_user" name="id_user">
                                        <option value="">Select</option>
                                        <c:forEach items="${requestScope.comboBoxData[0]}" var="val" varStatus="list">
                                            <option value="<c:out value="${val.id}" />"><c:out value="${val.login}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <!-- ./ligne -->

                        </div>

                    </form>

                    <!--Alert Message -->
                    <div id="appAlert"></div>

                </div>

                <div class="modal-footer">

                    <!-- Bouton de fermiture du modal -->
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">
                        <i class="fa fa-remove"></i> Close
                    </button>

                    <!-- Bouton d'enregistrement du formulaire -->
                    <button class="btn btn-default btn-success pull-right" id="btn-enregistrer" type="submit" form="form">
                        <i class="fa fa-check"></i> Save
                    </button>

                </div>

            </div>

        </div>

    </div>
</c:if>

<!-- Form profile -->
<c:if test="${sessionScope.con.person != null}">
    <div class="modal fade" id="modal_profile" role="alert" data-backdrop="static" data-keyboard="false">

        <div class="modal-dialog">

            <div class="modal-content">

                <div class="modal-body">

                    <!-- Header -->
                    <p class="font-weight-bold" style="font-size: 18px;">
                        <!-- Title -->
                        <span>
                            <i class="fa fa-user text-info"></i> My profile
                        </span>
                        <!-- Close bouton -->
                        <button type="button" class="close float-right font-weight-bold" data-dismiss="modal" aria-hidden="true">
                            <i class="fa fa-times"></i>
                        </button>
                    </p>

                    <div class="pt-3"></div>

                    <!-- Body -->
                    <div class="row">

                        <div class="col-xl-12">

                            <!-- table -->
                            <table class="table table-sm">
                                <tbody>
                                    <tr>
                                        <th scope="row">
                                            Type
                                        </th>
                                        <td>
                                            ${sessionScope.con.person.isStudent ? "Strudent" 
                                                                                : "Teacher"}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            Login
                                        </th>
                                        <td>
                                            ${sessionScope.con.person.user.login}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            Firstname
                                        </th>
                                        <td>
                                            ${sessionScope.con.person.firstname}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            Lastname
                                        </th>
                                        <td>
                                            ${sessionScope.con.person.lastname}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            Email
                                        </th>
                                        <td>
                                            ${sessionScope.con.person.email}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            Address
                                        </th>
                                        <td>
                                            ${sessionScope.con.person.address}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            Phone
                                        </th>
                                        <td>
                                            ${sessionScope.con.person.mobile_phone}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            Connected since
                                        </th>
                                        <td>
                                            ${sessionScope.con.connection_date}
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- ./table -->

                        </div>

                    </div>

                </div>

                <div class="modal-footer">

                    <!-- Bouton de fermiture du modal -->
                    <button type="button" class="btn btn-sm btn-default pull-left" data-dismiss="modal">
                        <i class="fa fa-close"></i> Close
                    </button>

                </div>

            </div>

        </div>

    </div>
</c:if>