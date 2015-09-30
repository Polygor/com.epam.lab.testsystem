var users_tests = {};

$.get("tests-users/.rest", function(result){
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
};
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
                    widePanel.slideToLeftRight(widePanel.previousElement, function(){widePanel.slideToLeftRight(element)});
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
            $(elementDropPanel).animate({left: widePanel.slidePixelsCount, width: dropPanelWidth}, widePanel.animationSpeed, function () {
                widePanel.slideToDownTop(elementDropPanel)
            });
        } else {
            widePanel.slideToDownTop(elementDropPanel, function () {
                //$(elementDropPanel).removeClass('panel panel-primary');
                $(elementDropPanel).animate({left: "0px", width: "100%"}, widePanel.animationSpeed);
                $(element).animate({left: "0px", width: "100%"}, widePanel.animationSpeed);
                widePanel.isExpanded = false;

                if (isFunction(callback) == true){
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
};

function isFunction(functionToCheck) {
    var getType = {};
    return functionToCheck && getType.toString.call(functionToCheck) === '[object Function]';
}

var slidePanel = new SlidePanel();
var widePanel = new WidePanel();

function hoverTestLink(element){

    if (slidePanel.isPushedAway == false){
        var rowContainer = parseInt($(element).parent().index(), 10) + 1;
        var heightSlideButton = parseInt($(element).css('height'), 10);
        var middleOfSlideButton = parseInt($(element).css('height'), 10) - (parseInt($(element).css('height'), 10) / 2);
        var newPosition = rowContainer * heightSlideButton;

        $('.slide-in-out-button').css("top", newPosition);
        $('.slide-in-out-button').show();

        var userId = $(element).parent().attr("id");
        var testIDs = users_tests[userId];

        var inputTestsElement = $("input[id=test"+testIDs+"]");

        if (inputTestsElement != null){
            //$(".admin-test-list input").attr("checked", false);
            inputTestsElement.attr("checked", true)
        }

        //testIDs.forEach(function(testIdIndex, IdValue){
        //    var inputTestsElement = $("input[id=test"+IdValue+"]");
        //    if (inputTestsElement != null){
        //        inputTestsElement.attr("checked", "checked")
        //    }
        //})
    }
}