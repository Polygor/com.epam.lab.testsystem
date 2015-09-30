//------------------------------START ADMIN PAGE------------------------------------------------

$('.main-quest-panel').on('click', '.add-qwest-button', function () {
    var nextQuestionId = parseInt($('.question-form').last().attr('id')) + 1;
    var nextQuestionNumber = parseInt($('.question-form').length) + 1;
    var csrf = $('input[name=_csrf]').last();
    var testId = $('input[name=testId]').last();
    $('.questions').append(
        '<form name="questionForm" method="post" action="/testsystem/admin/questionAddEdit.do">' +
            testId.prop('outerHTML') +
            '<div class="question-form" id="' + nextQuestionId + '">' +
                '<div>' +
                    '<textarea name="title" rows="3" class="form-control vresize" placeholder="Enter your question here"></textarea>' +
                '</div>' +
                '<div class="answers">' +
                    '<div class="answer">' +
                        '<div>' +
                            '<div class="input-group">' +
                                '<span class="input-group-addon">' +
                                    '<span class="glyphicon glyphicon-remove" onclick="removeAnswer(this)" aria-hidden="true"></span>' +
                                '</span>' +
                                '<textarea name="answers[0].text" rows="3" class="form-control vresize" placeholder="Enter your answer here"></textarea>' +
                                '<span class="input-group-addon">' +
                                    '<input type="checkbox" name="answers[0].right" value="on">' +
                                '</span>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
                '<a onclick="addAnswer(this)" href="javascript:void(0)" class="btn btn-primary btn-raised add-ans-button">Add answer</a>' +
                '<div class="clearfix"></div>' +
                '<div class="text-center">' +
                    csrf.prop('outerHTML') +
                    '<input type="submit" name="" value="Сохранить">' +
                '</div>' +
            '</div>' +
        '</form>'
    );
    $('textarea').last().focus();
});

function addAnswer(element) {
    var currentAnswerCount = $(element).parent('.question-form').children('.answers').children('.answer').size();
    var currentQuestion = $(element).parent('.question-form').attr('id');

    $(element).parent().children('.answers').append('<div class="answer"><div><div class="input-group"><span class="input-group-addon"><span class="glyphicon glyphicon-remove" onclick="removeAnswer(this)" aria-hidden="true"></span></span><textarea name="questions[' + currentQuestion + '].answers[' + currentAnswerCount + '].text" rows="3" class="form-control vresize" style="margin-top: 0px; margin-bottom: 0px; height: 78px;"></textarea> <span class="input-group-addon"><input type="checkbox" name="questions[' + currentQuestion + '].answers[' + currentAnswerCount + '].right"></span></div></div></div>');

    var lastTextArea= $('textarea').last();
    if (lastTextArea.attr('name') == "questions[" + currentQuestion + "].answers[" + currentAnswerCount + "].text")
        lastTextArea.focus();
}

function removeAnswer(element) {
    $(element).parent().parent().parent().parent().remove();
    $('a').last().focus();
}

function selectTestFromTable(element){
    var testId =$(element).attr('test-id');
    window.location.href = "testView.do?id="+testId;
}

var users_tests = {};

$.get("tests-users/.rest", function (result) {
    users_tests = result;
});


function SlidePanel() {
    this.isPushedAway = false;
    this.slidePixelsCount = -190;
    this.animationSpeed = 500;
    var thisPanel = this;

    this.doSlide = function (element) {
        var slidePanel = $(element).parent('div');
        var arrowSign = $(element).children('div');
        if (this.isPushedAway == false) {
            $(slidePanel).animate({right: thisPanel.slidePixelsCount}, thisPanel.animationSpeed);
            $(arrowSign).removeClass("arrow-right");
            $(arrowSign).addClass("arrow-left");
            this.isPushedAway = true;
        } else {
            $(arrowSign).removeClass("arrow-left");
            $(arrowSign).addClass("arrow-right");
            $(slidePanel).animate({right: "0px"}, thisPanel.animationSpeed);
            this.isPushedAway = false;
        }
    }
}
//----------------------------------------------------------------

function WidePanel() {
    this.animationSpeed = 150;
    this.slidePixelsCount = -200;
    this.slidePanelIndent = 10;
    this.dropPanelPixelsCount = 300;
    this.isExpanded = false;
    this.previousElement = null;
    this.processing = false;
    var widePanel = this;

    this.doExpand = function (element) {
        if (widePanel.processing == false) {
            widePanel.processing = true;
            if (widePanel.previousElement != null && widePanel.previousElement != element && widePanel.isExpanded == true) {
                widePanel.slideToDownTop($(widePanel.previousElement).prev(), function () {
                    widePanel.slideToLeftRight(widePanel.previousElement, function () {
                        widePanel.slideToLeftRight(element)
                    });
                    widePanel.previousElement = element;
                });
            } else {
                widePanel.previousElement = element;
                if (widePanel.isExpanded == false)
                    widePanel.slideToLeftRight(element);
                else
                    widePanel.slideToLeftRight(element);
            }
        }
    };


    this.slideToLeftRight = function (element, callback) {
        var elementDropPanel = $(element).prev();

        if (widePanel.isExpanded == false) {
            var elementWidth = parseInt($(element).css('width'), 10) + Math.abs(widePanel.slidePixelsCount);
            var dropPanelWidth = Math.abs(widePanel.slidePixelsCount) - widePanel.slidePanelIndent;
            $(element).addClass('selected-row');
            $(element).animate({left: widePanel.slidePixelsCount, width: elementWidth}, widePanel.animationSpeed);
            $(elementDropPanel).animate({
                left: widePanel.slidePixelsCount,
                width: dropPanelWidth
            }, widePanel.animationSpeed, function () {
                widePanel.slideToDownTop(elementDropPanel)
            });
        } else {
            widePanel.slideToDownTop(elementDropPanel, function () {
                //$(elementDropPanel).removeClass('panel panel-primary');
                $(elementDropPanel).animate({left: "0px", width: "100%"}, widePanel.animationSpeed);
                $(element).animate({left: "0px", width: "100%"}, widePanel.animationSpeed);
                widePanel.isExpanded = false;

                if (isFunction(callback) == true) {
                    callback();
                    $(element).removeClass('selected-row');
                } else {
                    widePanel.processing = false;
                    $(element).removeClass('selected-row');
                }
            })
        }
    };

    this.slideToDownTop = function (element, callback) {
        if (widePanel.isExpanded == false) {

            //$(element).animate({height: widePanel.dropPanelPixelsCount}, widePanel.animationSpeed);
            $(element).slideDown(widePanel.animationSpeed);
            widePanel.isExpanded = true;
            widePanel.processing = false;
        } else {
            $(element).slideUp(widePanel.animationSpeed, callback);
            //$(element).animate({height: "100%"}, widePanel.animationSpeed, callback);
        }
    };
}
function isFunction(functionToCheck) {
    var getType = {};
    return functionToCheck && getType.toString.call(functionToCheck) === '[object Function]';
}

var slidePanel = new SlidePanel();
var widePanel = new WidePanel();

function hoverTestLink(element) {

    if (slidePanel.isPushedAway == false) {

        var newPosition = getNewPositionSlideInOutButton($(element).parents("tr").index());

        $('.slide-in-out-button').css("top", newPosition);
        $('.slide-in-out-button').show();

        var userId = $(element).parents("tr").attr("id");
        $('.slide-in-out-button').attr("id", "userId" + userId);

        $(".admin-test-list input").prop("checked", false);
        users_tests.forEach(function (val, index) {
            if (val.userId == userId){
                var inputTestsElement = $("input[value=" + val.testId + "]");
                inputTestsElement.prop("checked", true)
            }
        })
    }
}

function getNewPositionSlideInOutButton(currentIndex){
    var rows = $('.stud-table tr');
    var resultPosition = 0;
    rows.each(function(index, val){
        if (currentIndex >= index)
            resultPosition = resultPosition + parseInt($(val).height());
    });
    return resultPosition;
}

function setTestsForUser() {
    var userId = $('.slide-in-out-button').attr("id").substr(6);

    var testIds = [];
    var tests = $(".admin-test-list div input:checked");
    tests.each(function (index, val) {
        testIds.push($(val).val())
    });
    var all = {userId: userId, testIds: testIds};
    var jsonObject = JSON.stringify(all);
    $.ajax({
        type: "post",
        url: "tests-users/setUserTests/.rest",
        contentType: "application/json",
        data: jsonObject,
        success: function (data) {
            alert('Success');
        }
    });
}

function findUsers(element){
    var soughtForText = $(element).val().toLowerCase();
    var foundAllCels = $(element).parents('table').children('tbody').children('tr').children('td').children('.stud-cell');

    foundAllCels.each(function(index, val){
        if ($(val).html().toLowerCase().indexOf(soughtForText) < 0 && soughtForText.length > 0){
            $(val).parents('tr').css('display', 'none');
        } else {
            $(val).parents('tr').css('display', '');
        }
    })
}

function findTest(element){
    var soughtForText = $(element).val().toLowerCase();
    var foundAllCels = $(element).parents('.front-panel').children('.admin-test-list').children('div').children('label');

    foundAllCels.each(function(index, val){
        //var celText = $(val).html().indexOf(soughtForText);
        var celText = $(val).text().toLowerCase();
        if ($(val).text().toLowerCase().indexOf(soughtForText) < 0 && soughtForText.length > 0){
            $($(val).parents('div').get(0)).css('display', 'none');
        } else {
            $($(val).parents('div').get(0)).css('display', '');
        }
    })
}
//------------------------------END ADMIN PAGE------------------------------------------------

//------------------------------START USER PAGE------------------------------------------------

$(document).keydown(function (e) {
    if (window.location.pathname == "/testsystem/test.do") {
        switch (e.which) {
            case 37: // left
                switchQuestionToPrevious();
                break;
            case 39: // right
                switchQuestionToNext();
                break;
            default:
                return; // exit this handler for other keys
        }
        e.preventDefault(); // prevent the default action (scroll / move caret)
    }
});

function switchQuestionToNext() {
    var currentQuest = $('.question-button.current').attr('id');
    if (currentQuest < $('.question-button').size()) {
        var nextQuest = currentQuest - 1 + 2;
        $(".question-button#" + currentQuest + "").removeClass('current');
        $(".question-button#" + nextQuest + "").addClass('current');

        $("#question" + currentQuest + "").addClass('hidden');
        $("#question" + nextQuest + "").removeClass('hidden');

        var answeredCount = $("#question" + currentQuest + " .answers div div input:checked").size();
        if (answeredCount < 1) {
            $(".question-button#" + currentQuest + "").addClass('missed');
        } else $(".question-button#" + currentQuest + "").removeClass('missed');
    }
}

function switchQuestionToPrevious() {
    var currentQuest = $('.question-button.current').attr('id');
    if (currentQuest > 1) {
        var previousQuest = currentQuest - 1;
        $(".question-button#" + currentQuest + "").removeClass('current');
        $(".question-button#" + previousQuest + "").addClass('current');
        $("#question" + currentQuest + "").addClass('hidden');
        $("#question" + previousQuest + "").removeClass('hidden');

        var answeredCount = $("#question" + currentQuest + " .answers div div input:checked").size();
        if (answeredCount < 1) {
            $(".question-button#" + currentQuest + "").addClass('missed');
        } else $(".question-button#" + currentQuest + "").removeClass('missed');
    }
}

function selectQuestion(element) {
    var currentQuest = $('.question-button.current').attr('id');
    var selectedQuest = $(element).parent().children('button').attr('id');

    $("#question" + currentQuest + "").addClass('hidden');
    $("#question" + selectedQuest + "").removeClass('hidden');

    $(".question-button#" + currentQuest + "").removeClass('current');
    $(element).addClass('current');

    var answeredCount = $("#question" + currentQuest + " .answers div div input:checked").size();
    if (answeredCount < 1) {
        $(".question-button#" + currentQuest + "").addClass('missed');
    } else $(".question-button#" + currentQuest + "").removeClass('missed');
}

$('.onoffswitch').on('click', 'input', function () {
    if ($(this).is(':checked'))
        $('.question-button.current').addClass('not-sure');
    else
        $('.question-button.current').removeClass('not-sure');

});

function getButtonChar(element, currentIndex) {
    var firsChar = "A".charCodeAt(0) - 1;
    var nextChar = String.fromCharCode(firsChar + currentIndex);
    $(element).text(nextChar)
}

function selectAnswer(element) {
    $(element).parent().parent().parent().children('div').children('div').children('input').parent().children('label').removeClass('selected-answer');
    $(element).parent().parent().parent().children('div').children('div').children('input:checked').parent().children('label').addClass('selected-answer');

    $(".question-button.current").addClass('answered');
}

$(document).ready(function () {
    var allSpans = $('.custom-btn-block label span');
    var firsChar = "A".charCodeAt(0);

    $.each(allSpans, function (index, value) {
        var currentIndex = $(value).attr('current-index');
        var summ = parseInt(firsChar, 10) + parseInt(currentIndex, 10);
        var nextChar = String.fromCharCode(summ);
        $(value).text(nextChar)
    });

    var questCount = $('.question');

    $.each(questCount, function (index, value) {
        var nextNumber = parseInt(index)+1;
        if (nextNumber == 1)
            $('.test-case-bottons').append('<div class="col-sm-3"><button onclick="selectQuestion(this)" id="'+nextNumber+'" class="btn btn-default question-button current">'+nextNumber+'</button></div>');
        else
            $('.test-case-bottons').append('<div class="col-sm-3"><button onclick="selectQuestion(this)" id="'+nextNumber+'" class="btn btn-default question-button">'+nextNumber+'</button></div>');
    });
});
//------------------------------END USER PAGE------------------------------------------------
