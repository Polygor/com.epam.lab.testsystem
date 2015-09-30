(function () {
    $('#createUserInputsId').hide();
    $('#save').hide();
    $('#cancel').hide();
})();

function createCancelStudent() {
    $('#createUserInputsId').toggle();
    $('#createButton').toggle();
    $('#save').toggle();
    $('#cancel').toggle();
}
