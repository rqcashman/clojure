function statusOK(evt) {
    if($("#status-content").hasClass("success") ) {
        location.reload()
    }
    else {
        change_form(roster_current_form_id);
    }
    $("#status-content").text("Processing...");
    evt.preventDefault();
}
