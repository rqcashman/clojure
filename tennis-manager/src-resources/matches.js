/**
 * Created by Richard on 6/5/2017.
 */
var current_form_id = "show-schedule";

function change_form(new_form_id) {
    $("#status-panel").css("display", "none");
    $("#" + current_form_id).css("display", "none");
    current_form_id = new_form_id;
    $("#" + new_form_id).css("display", "block");
}



function get_roster_data() {
    $("#sr_team_name").text($("#team_id option:selected").text());
    tennis_manager.roster.roster($("#team_id option:selected").val());
}

function update_player_form(player_id) {
    tennis_manager.roster.load_update_player_form(player_id);
    change_form("update-player");
}

$().ready(function () {
    change_form(current_form_id);
});

function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}


function processRequest(form_id, uri, title) {
    if ($(form_id).valid()) {
        tennis_manager.core.db_update_request(form_id, uri, title);
        $("#" + current_form_id).css("display", "none");
        $("#status-panel").css("display", "block");
    }
    return false;
}
