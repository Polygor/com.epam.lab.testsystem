function SlidePanel() {
    this.isPushedAway = false;
    this.doSlide = function (element) {
        var slidePanel = $(element).parent('div');
        if (this.isPushedAway == false) {
            $(slidePanel).animate({left: "-200px"}, 500);
            this.isPushedAway = true;
        } else {
            $(slidePanel).animate({left: "0px"}, 500);
            this.isPushedAway = false;
        }
    }
};
//----------------------------------------------------------------
function WidePanel() {
    this.isExpanded = false;
    this.previousElement = null;
    var widePanel = this;

    this.doExpand = function (element) {
        if (widePanel.previousElement != null && widePanel.previousElement != element){
            if (widePanel.isExpanded == false)
                widePanel.slideToDownTop(widePanel.previousElement);
            else
                widePanel.slideToLeftRight(widePanel.previousElement, function(){
                    widePanel.previousElement = element;
                    widePanel.slideToDownTop(element);
                });
        }else{
            widePanel.previousElement = element;
            if (widePanel.isExpanded == false)
                widePanel.slideToDownTop(element);
            else
                widePanel.slideToLeftRight(element);
        }
    };

    this.slideToLeftRight = function (element, callback) {
        if (widePanel.isExpanded == false) {
            $(element).parent('td').addClass('selected-row');
            $(element).addClass('selected-row');
            $(element).animate({left: "-200px"}, 150);
        } else {
            $(element).parent('td').removeClass('selected-row');
            if (isFunction(callback))$(element).animate({left: "0px"}, 150, widePanel.slideToDownTop(element), callback);
            else $(element).animate({left: "0px"}, 150, widePanel.slideToDownTop(element));
            widePanel.isExpanded = false;
        }
    };

    this.slideToDownTop = function (element) {
        if (widePanel.isExpanded == false) {
            $(element).parent('td').addClass('selected-row');
            $(element).animate({height: "300px"}, 150, widePanel.slideToLeftRight(element));
            widePanel.isExpanded = true;
        } else {
            $(element).parent('td').removeClass('selected-row');
            $(element).animate({height: "100%"}, 150);
        }
    };
};

function isFunction(functionToCheck) {
    var getType = {};
    return functionToCheck && getType.toString.call(functionToCheck) === '[object Function]';
}

var slidePanel = new SlidePanel();
var widePanel = new WidePanel();