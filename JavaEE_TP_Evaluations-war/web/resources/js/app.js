/* global moment */

//#region Class
//class NotificationBO {
//
//	constructor(data = null, isSuccess = true, message = null) {
//    
//		// -- Attributes -- //
//		this.data = data;
//		this.isSuccess = isSuccess;
//		this.message = message === null ? isSuccess ? 'success' 
//													: 'fail'
//										: message;
//	  }
//  
//  }
//#endregion

//#region Global parameters
var $parameters, 
    $Confirmation_message_box, 
    $DureeVisibiliteMessageBox = 5000, 
    $ClassTableSelection = 'app-table-success';
var function_setTimeout;
var function_setInterval;
//#endregion

// -- Notificateur -- //
function notification(notification, titre) {

    // -- Ecoute si le notificateur est soumis -- //
    if (!notification) {
        notification = {
            isSuccess: true,
            message: 'Communication error!'
        }
    }

    // - Notifier -- //
    new PNotify({
        title: (!titre) ? 'Information'
                        : titre,
        text: notification.message,
        type: (notification.isSuccess == null) ? 'info'
                                                : (notification.isSuccess) ? 'error'
                                                                           : 'success'
    });

}

function pNotify(notification, titre) {

    // -- Ecoute si le notificateur est soumis -- //
    if (!notification) {
        notification = {
            isSuccess: true,
            message: 'Communication error!'
        }
    }
    
    new PNotify({
        title: (!titre) ? 'Information'
                        : titre,
        text: notification.message,
        type: (notification.isSuccess == null) ? 'info'
                                                : (notification.isSuccess) ? 'error'
                                                                           : 'success',
//        styling: 'bootstrap3'
    });

}

// -- Display or not the loading page -- //
function displayLoadingPage(afficher) {
        afficher = (!afficher) ? false 
                               : afficher;
	if (afficher) {
            // -- Affichier le progress bar -- //
            NProgress.start();
            // -- Afficher le frame de chargelent -- //
            $('#frame_chargement').show();
	} else {
            // -- Finaliser le chargement du progress bar -- //
            NProgress.done();
            // -- Cacher le frae de chargement -- //
            $('#frame_chargement').hide();
	}
}

// -- Load ichack plugins -- //
function loadICheck(color, attribute) {
    try {
        attribute = (!attribute) ? 'flat-iCheck' 
                                 : attribute;
        color = (!color) ? 'green' 
                         : color;
        $('input[type="checkbox"].' + attribute + ', input[type="radio"].' + attribute).iCheck({
            checkboxClass: 'icheckbox_flat-' + color,
            radioClass: 'iradio_flat-' + color
        });
    } catch (e) {
        consoleOut(e.message);
    }
}

// -- Selectionner une ligne de la table -- //
function tableLineSelection(ligne, id_table) {

    // -- Mise à jour du id_table si celui ci n'est pas soumis -- //
    if (!id_table) {
        id_table = 'table-donnee';
    }

    // -- Suppression de toutes les lignes delectionnées -- //
    $('#' + id_table + ' tbody tr').each(
        function () {
            // -- Si l'element n'a pas la classe passer -- //
            if ($(this).hasClass($ClassTableSelection)) {
                $(this).removeClass($ClassTableSelection);
            }
        }
    );

    // -- Mise à jour de la couleur de la ligne -- //
    if (ligne) {
        ligne.addClass($ClassTableSelection);
    }

}

// -- Fonction pour initiliser les style css javascript des tables -- //
function loadCSSTable(type_donnee, id_check_all, id_table) { //
    
    // -- Mise à jour paramètre id_check_all -- //
    if (!id_check_all) {
        id_check_all = 'check-all';
    }

    // -- Mise à jour paramètre id_check_all -- //
    if (!id_table) {
        id_table = 'table-donnee';
    }

    // iCheck
    loadICheck();

    // Table
    $("table input[name='" + type_donnee + "']").on('ifChecked', function () {
        $(this).parent().parent().parent().addClass('selected');
        loadCSSTableCountSelection(type_donnee, '', id_check_all, id_table);
    });
    $("table input[name='" + type_donnee + "']").on('ifUnchecked', function () {
        $(this).parent().parent().parent().removeClass('selected');
        loadCSSTableCountSelection(type_donnee, '', id_check_all, id_table);
    });

    $(".bulk_action input[name='" + type_donnee + "']").on('ifChecked', function () {
        $(this).parent().parent().parent().addClass('selected');
        loadCSSTableCountSelection(type_donnee, '', id_check_all, id_table);
    });
    $(".bulk_action input[name='" + type_donnee + "']").on('ifUnchecked', function () {
        $(this).parent().parent().parent().removeClass('selected');
        loadCSSTableCountSelection(type_donnee, '', id_check_all, id_table);
    });

    $('.bulk_action input#' + id_check_all).on('ifChecked', function () {
        loadCSSTableCountSelection(type_donnee, 'all', id_check_all, id_table);
    });
    $('.bulk_action input#' + id_check_all).on('ifUnchecked', function () {
        loadCSSTableCountSelection(type_donnee, 'none', id_check_all, id_table);
    });

}

// -- Mettre à jour le label du nombre d'element selectionné -- //
function loadCSSTableCountSelection(type_donnee, checkState, id_check_all, id_table) {

    if (checkState === 'all') {
        $(".bulk_action input[name='" + type_donnee + "']").iCheck('check');
    }
    if (checkState === 'none') {
        $(".bulk_action input[name='" + type_donnee + "']").iCheck('uncheck');
    }

//    var checkCount = $(".bulk_action input[name='" + type_donnee + "']:checked").length;
//
//    if (checkCount) {
//        $('#' + id_table + ' .column-title').hide();
//        $('#' + id_table + ' .bulk-actions').show();
//        $('#' + id_table + ' .action-cnt').html(checkCount);
//    } else {
//        $('#' + id_table + ' .column-title').show();
//        $('#' + id_table + ' .bulk-actions').hide();
//    }

}

// -- Message box de notification -- //
function messageBox(notification) {
    if (!notification){
        notification = {
                            isSuccess: false,
                            message: 'fail'
                        };
    }
    // -- Kill the the old  -- //
    clearTimeout(function_setTimeout);

    // -- Mise à jour de la taille -- //
    $('#modal_message_taille').removeClass('modal-dialog');
    $('#modal_message_taille').addClass('modal-dialog modal-sm');
    // -- Définir l'entête -- //
    $('#modal_message_titre').html('<i class="fa fa-info-circle text-' + ((notification.isSuccess != null) ? (!notification.isSuccess) ? 'danger'
                                                                                                                                                    : 'success'
                                                                                                                    : 'info') + '"></i> Information');
    // -- Definir le message -- //
    $('#modal_message_text').html(notification.message);
    // -- Afficher -- //
    $('#modal_message').modal('show');

    // -- Ne pas fermer si la valeur est -1 -- //
    if ($DureeVisibiliteMessageBox > 0) {
        // -- Supprimer l'alert après un temps défini -- //
        function_setTimeout = 
            setTimeout(
                    function () {
                            // -- Fermer le modal -- //
                            $('#modal_message').modal('hide');
                    },
                    $DureeVisibiliteMessageBox
            );
    }

}

// -- Load global parameters -- //
function loadGlobalParameters(value){
	$parameters = appModule.convert.toDecryptAES(value[1], value[0]);
}

// -- Redirection to an other page -- //
function redirection(url) {
    
    window.location.href = url;
    
}

// -- Afficher un message de confirmation d'actionn -- //
function confirmationYesOrNo(message, id_soumission, fonction_execution) {

    // -- Initialisation de la réponse -- //
    $Confirmation_message_box = false;

    // -- Mise à jour du message -- //
    $('#modal_message_question_message').html(
        (!message) ? 'Confirm action'
                   : message
    );
    // -- Annuler tous les evenement précédement chargé -- //
    $('#modal_message_question_bouton_oui').off('click');
    $('#modal_message_question_bouton_non').off('click');
    $('#modal_message_question').off('hidden.bs.modal');

    // -- Définir les nouveaux evenements -- //
    // -- Comportement du bouton Oui -- //
    $("#modal_message_question_bouton_oui").on('click',
        function () {
            // -- Cacher le message box -- //
            $('#modal_message_question').modal('hide');
            // -- Mise à jour de la réponse -- //
            $Confirmation_message_box = true;
        }
    );
    // -- Comportement du bouton Non -- //
    $("#modal_message_question_bouton_non").on('click',
        function () {
            // -- Cacher le message box -- //
            $('#modal_message_question').modal('hide');
            // -- Mise à jour de la réponse -- //
            $Confirmation_message_box = false;
        }
    );
    // -- Méthode quand le message box se fermer -- //
    $("#modal_message_question").on('hidden.bs.modal',
        function () {
            // -- Si la réponse est non -- //
            if (!$Confirmation_message_box) {
                return false;
            }

            // -- Si l'id est passé -- //
            if (id_soumission) {
                $("#" + id_soumission).submit();
            }
            // -- Executer la fonction -- //
            else {
                fonction_execution();
            }

            // -- Initialisation de la réponse -- //
            $Confirmation_message_box = false;
        }
    );

    // -- Afficher le message box -- //
    $('#modal_message_question').modal('show');

}

// -- Activer/Desactiver formulaire -- //
function enableOrDisableForm(id_form, disable) {

    // -- Si le id_form n'est pas soumis ne rien faire -- //
    if (!id_form) {
        return false;
    }

    // -- Si il s'agit d'une activation -- //
    if (disable) {
        $("#" + id_form + " :input").attr("disabled", true);
    } else {
        $("#" + id_form + " :input").attr("disabled", false);
    }

}

// -- Afficher un message de confirmation d'actionn -- //
function confirmationAlertYesOrNo(id_alert, message, id_form, fonction_execution) {

    // -- Annuler le time out actuel -- //
    clearTimeout(function_setTimeout);

    // -- Mise à jour de id_element -- //
    id_alert = (!id_alert) ? 'appAlert'
                           : id_alert;

    // -- Initialisation de la réponse -- //
    $Confirmation_message_box = false;

    // -- Afficher l'alert -- //
    $('#' + id_alert).html(

        '<div id="appAlert_id" class="mmalert alert alert-secondary alert-dismissible fade show" role="alert">' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span>' +
                '</button>' +
                '<div class="row">' +
                    '<div class="col-lg-12">' +
                        '<div class="float-left">' +
                            '<b>Information</b><br/>' +
                            ((!message) ? 'Confirm action'
                                        : message) +
                        '</div>' +
                        '<div class="float-right">' +
                            '<button id="alert_message_question_bouton_non" type="button" class="btn btn-sm btn-default bg-white" data-dismiss="alert" aria-label="Close">' +
                                '<i class="fa fa-remove"></i> ' + 'No' +
                            '</button>' +
                            '<button id="alert_message_question_bouton_oui" type="button" class="btn btn-sm btn-success" data-dismiss="alert" aria-label="Close">' +
                                '<i class="fa fa-check"></i> ' + 'Yes' +
                            '</button>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
        '</div>'
    );

    // -- Annuler tous les evenement précédement chargé -- //
    $('#alert_message_question_bouton_oui').off('click');
    $('#alert_message_question_bouton_non').off('click');
    $('#appAlert_id').off('closed.bs.alert');

    // -- Définir les nouveaux evenements -- //
    // -- Comportement du bouton Oui -- //
    $("#alert_message_question_bouton_oui").on('click',
        function () {
            // -- Fermer le message box -- //
            $('#appAlert_id').alert('close');
            // -- Mise à jour de la réponse -- //
            $Confirmation_message_box = true;
        }
    );
    // -- Comportement du bouton Non -- //
    $("#alert_message_question_bouton_non").on('click',
        function () {
            // -- Fermer le message box -- //
            $('#appAlert_id').alert('close');
            // -- Mise à jour de la réponse -- //
            $Confirmation_message_box = false;
        }
    );
    // -- Méthode quand le message box se fermer -- //
    $('#appAlert_id').on('closed.bs.alert',
        function () {
            // -- Activer/Desactiver formulaire -- //
            enableOrDisableForm(id_form, false);

            // -- Si la réponse est non -- //
            if (!$Confirmation_message_box) {
                return false;
            }

            // -- Si l'id est passé -- //
            if (id_form) {
                $("#" + id_form).submit();
            }
            // -- Executer la fonction -- //
            else {
                fonction_execution();
            }

            // -- Initialisation de la réponse -- //
            $Confirmation_message_box = false;
        }
    );

    // -- Activer/Desactiver formulaire -- //
    enableOrDisableForm(id_form, true);

    // -- Ne pas fermer si la valeur est -1 -- //
    if ($DureeVisibiliteMessageBox > 0) {
        // -- Supprimer l'alert après un temps défini -- //
        function_setTimeout =
            setTimeout(
                function () {
                    // -- Fermer l'alert -- //
                    $('#appAlert_id').alert('close');
                },
                $DureeVisibiliteMessageBox
            );
    }

}

// -- Afficher une alerte sur un element -- //
function alert(notification, id_element) {

    // -- Annuler le time out actuel -- //
    clearTimeout(function_setTimeout);

    // -- Mise à jour de id_element -- //
    id_element = (!id_element) ? 'appAlert'
                               : id_element;

    // -- Ecoute si le notificateur est soumis -- //
    if (!notification) {
        notification = {
            isSuccess: true,
            message: 'Error communication',
        }
    }
    
    // -- Afficher l'alert -- //
    $('#' + id_element).html(
        '<div class="mmalert alert alert-' + ((notification.isSuccess != null) ? (!notification.isSuccess) ? 'danger'
                                                                                                          : 'success'
                                                                               : 'info') + ' alert-dismissible fade show" role="alert">' +
            '<div class="row">' +
                '<div class="col-lg-12">' +
                    '<div class="pull-left">' +
                        '<b>Information</b><br/>' +
                        notification.message +
                    '</div>' +
                    '<div class="pull-right">' +
                        '<button type="button" class="btn btn-sm btn-danger" data-dismiss="alert" aria-label="Close">' +
                            '<i class="fa fa-remove"></i> ' + 'Close' +
                        '</button>' +
                    '</div>' +
                '</div>' +
            '</div>' +
        '</div>'
    );

    // -- Ne pas fermer si la valeur est -1 -- //
    if ($DureeVisibiliteMessageBox > 0) {
        // -- Supprimer l'alert après un temps défini -- //
        function_setTimeout =
            setTimeout(
                function () {
                    // -- Fermer l'alert -- //
                    $('#' + id_element + ' .alert').alert('close');
                },
                $DureeVisibiliteMessageBox
            );
    }

}

// -- On loading page -- //
$(
    function(){

        //#region Grame loading page
        $('<div id="frame_chargement"/>')
        .css({
            position: 'fixed',
            top: 0,
            left: 0,
            width: '100%',
            height: $(window).height() + 'px',
            opacity: 0.4,
            zIndex: 999999,
            cursor: 'wait',
            background: 'lightgray url(resources/images/gif/loading_50px.gif) no-repeat center'
        })
        .hide()
        .appendTo('body');
        //#endregion

        //#region iCheck
        loadICheck();
        //#endregion
        
        // -- Init confirmation message box -- //
        $Confirmation_message_box = false;
        
        // -- On app disconnect is cliked -- //
        try {
            
            $(".app-disconnect").click(
                function (event) {
                    // -- Annuler l'action par défaut -- //
                    event.preventDefault();

                    // -- Afficher/Cacher l'etat de chargement de la page -- //
                    displayLoadingPage(true);

                    // -- Ajax -- //
                    $.ajax({
                        type: "POST",
                        url: 'Authentication',
                        data: {
                            action: "logout"
                        },
                        success: function (result) {
                            if (result.isSuccess) {
                                // -- Redirection -- //
                                redirection(result.data);
                            } else {
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
    
        } catch(ex) {
            console.error(ex.message);
        }
        
        // -- When menu link is clicked -- //
        try {

            $('a.app-menu-link').on('click',
            function () {

                // -- Get url menu -- //
                var url = $(this).attr('data-app-menu');

                // -- Check if menu enable -- //
                if (!url) {
                    messageBox({ isSuccess: true, message: 'Process in production' });
                    // -- Cancel the action -- //
                    return false;
                }

                // -- Afficher/Cacher l'etat de chargement de la page -- //
                displayLoadingPage(true);

                // -- Redirection -- //
                redirection(url);

            }
        );

        } catch (e) {
            console.log(e.message);
        }
        
        // -- Load data table picker plugin -- //
        try {
            
            $('.app-date-picker').daterangepicker({
                singleDatePicker: true,
                showDropdowns: true,
                minYear: 19,
                maxYear: (parseInt(moment().format('YYYY')) - 10),
                locale: {
                    format: 'DD/MM/YYYY'
                }
            });

        } catch(e) { }
    }
);