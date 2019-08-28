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

                <div class="card-body">

                    <div class="row">

                        <div class="col-12">
                            <p class="h2 float-left">
                                <i class="fa fa-pencil"></i> ${requestScope.evaluation.title} (${requestScope.evaluation.code})
                            </p>
                            <span class="float-right">
                                <i class="fa fa-clock-o"></i> Your time: ${requestScope.evaluation.duration} min 0 sec.
                            </span>
                        </div>

                        <div class="col-12">
                            <hr>
                        </div>

                        <div class="col-12">
                            <ul class="nav nav-pills d-none" id="pills-tab" role="tablist">
                                <c:set var="indexQuestion" value="1" scope="page" />
                                <c:forEach items="${requestScope.evaluation.questions.toArray()}" var="question" varStatus="listQuestion">
                                    <li class="nav-item">
                                        <a class="nav-link ${indexQuestion == 1 ? "active" : ""}" data-id_question="${question.id}" data-indexQuestion="${indexQuestion}" id="pills-${indexQuestion}-tab" data-toggle="pill" href="#pills-${indexQuestion}" role="tab" aria-controls="pills-${indexQuestion}" aria-selected="${indexQuestion == 1 ? "true" : "false"}">${indexQuestion}</a>
                                    </li>
                                    <c:set var="indexQuestion" value="${indexQuestion + 1}" scope="page" />
                                </c:forEach>
                            </ul>
                            <div class="tab-content" id="pills-tabContent">
                                <c:set var="indexQuestion" value="1" scope="page" />
                                <c:forEach items="${requestScope.evaluation.questions.toArray()}" var="question" varStatus="listQuestion">
                                    <div class="tab-pane fade ${indexQuestion == 1 ? "show active" : ""}" id="pills-${indexQuestion}" role="tabpanel" aria-labelledby="pills-${indexQuestion}-tab">
                                        <div class="px-4">
                                            <div class="jumbotron jumbotron-fluid bg-light">
                                                <div class="container">
                                                    <p class="h3">
                                                        Question N°${indexQuestion} <i class="fa fa-question-circle fa-4x float-right mr-4"></i>
                                                    </p>
                                                    <p class="lead">
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${question.title} ?
                                                    </p>
                                                </div>
                                            </div>
                                            <form method="POST" id="form_id_question_${question.id}">
                                                <c:set var="indexAnswer" value="1" scope="page" />
                                                <c:forEach items="${question.answers.toArray()}" var="answer" varStatus="listAnswer">
                                                    <div class="form-check my-4">
                                                        <label class="form-check-label">
                                                            <input name="answer_${question.id}" type="radio" class="flat-iCheck" value="${answer.id}" required /> ${indexAnswer}. ${answer.title}
                                                        </label>
                                                    </div>
                                                    <c:set var="indexAnswer" value="${indexAnswer + 1}" scope="page" />
                                                </c:forEach>
                                                <span class="float-right"><i>${indexQuestion}/${requestScope.evaluation.questions.size()}</i></span>
                                            </form>
                                        </div>
                                    </div>
                                    <c:set var="indexQuestion" value="${indexQuestion + 1}" scope="page" />
                                </c:forEach>
                            </div>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-12">
                            <hr>
                            <button id="btn-prev" type="button" class="btn btn-light float-left" disabled>
                                <i class="fa fa-chevron-circle-left"></i> Previous question
                            </button>
                            <button id="btn-next" type="button" class="btn btn-light float-right">
                                <i class="fa fa-chevron-circle-right"></i> Next question
                            </button>
                        </div>
                    </div>

                </div>

            </div>

            <jsp:include page="../_shared/_bottom.jsp" /> 

        </div>

        <jsp:include page="_partial/_script.html" /> 

        <script type="text/javascript">
             
            var questionActive = 1,
                question_answer = [];
        
            function lastQuestion(indexQuestionTarget) {
                if(indexQuestionTarget == $('div[role="tabpanel"]').length) {
                    $("#btn-next").removeClass('btn-light');
                    $("#btn-next").addClass('btn-success');
                    $("#btn-next").html('<i class="fa fa-check"></i> Submit');
                } else {
                    $("#btn-next").removeClass('btn-success');
                    $("#btn-next").addClass('btn-light');
                    $("#btn-next").html('<i class="fa fa-chevron-circle-right"></i> Next question');
                }
            }

            function getAnswer(id_question) {

                var form = $('#form_id_question_' + id_question);

                // -- Check if form is validate -- //
                if (!form.parsley().isValid()) {
                    messageBox({isSuccess: false, message: 'You must select an answer!'});

                    return false;
                }

                var isUpdate = false;

                for(var i = 0; i < question_answer.length; i++) {
                    if (question_answer[i].indexOf('answer_' + id_question) != (-1)){
                        question_answer[i] = form.serialize();
                        isUpdate = true;
                        break;
                    }
                }

                if(!isUpdate) {
                    question_answer.push(form.serialize());
                }

                return true;

            }

            function submitQuestions() {

                // -- Ecouter la réponse du message de confirmation -- //
                if (!$Confirmation_message_box) {
                    // -- Afficher le message d'action -- //
                    confirmationYesOrNo(null, null, submitQuestions);
                    // -- Annuler l'action -- //
                    return false;
                }

                var data = [],
                    id_answers = [];
                for(var i = 0; i < question_answer.length; i++) {
                    data.push({
                       id_question:  question_answer[i].split('=')[0].replace('answer_', ''),
                       id_answer: question_answer[i].split('=')[1]
                    });
                    id_answers.push(question_answer[i].split('=')[1]);
                }

                console.log(JSON.stringify(data));

                // -- Afficher/Cacher l'etat de chargement de la page -- //
                displayLoadingPage(true);

                // -- Ajax -- //
                $.ajax({
                    type: "POST",
                    url: 'SessionTest',
                    data: {
                        action: 'getAnswers',
//                        answers: JSON.stringify(data),
                        id_answers: JSON.stringify(id_answers)
//                        id_evaluation: '${requestScope.evaluation.id}'
                    },
                    success: function (result) {
                        if (result.isSuccess) {
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
                
            // -- Lorsque le document est chargé -- //
            $(
                function () {
                    
                    $("#btn-prev").on('click', 
                        function () {
                            questionActive--;
                            
                            $('#pills-' + questionActive + '-tab').trigger('click'); 
                        }
                    );
            
                    $("#btn-next").on('click', 
                        function () {
                            var id_question = $('a[data-indexQuestion="' + questionActive + '"]').attr('data-id_question');
                            var isGetAnswer = getAnswer(id_question);
                            
                            if ($(this).html() != '<i class="fa fa-check"></i> Submit'){
                                if (isGetAnswer){
                                    questionActive++;
                                    $('#pills-' + questionActive + '-tab').trigger('click');
                                }
                            } else {
                                if (isGetAnswer){
                                    submitQuestions();
                                }
                            }
                        }
                    );
            
                    $('a[data-toggle="pill"]').on('shown.bs.tab', 
                        function (e) {
                            var indexQuestionTarget = $(e.target).attr('data-indexQuestion'),
                                indexQuestionRelatedTarget = $(e.relatedTarget).attr('data-indexQuestion'),
                                isNext = (indexQuestionRelatedTarget < indexQuestionTarget);
                            
                            if (isNext) {
                                var idQuestionRelatedTarget = $(e.relatedTarget).attr('data-id_question');
                                
//                                if (!getAnswer(idQuestionRelatedTarget)){
//                                    return;
//                                }
                            }

                            lastQuestion(indexQuestionTarget);
                            
                            $("#btn-prev").attr('disabled', (indexQuestionTarget == 1));
                        }
                    );
                    
                    lastQuestion(questionActive);

                }
            );

        </script>

        <jsp:include page="../_shared/_modals.jsp" /> 

    </body>

</html>
