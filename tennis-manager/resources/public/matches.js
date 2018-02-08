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

function change_to_email_form(match_id) {
    $("#send-availability-email").addClass("avail-small");
    tennis_manager.matches.email_form(match_id);
    change_form("send-availability-email");
}

function change_to_email_lineup_form(match_id) {
    $("#send-lineup-email").addClass("avail-small");
    tennis_manager.matches.lineup_email_form(match_id);
    change_form("send-lineup-email");
}

function change_to_avail_form(match_id) {
    //$("#show-availability").addClass("avail-small");
    tennis_manager.matches.availability(match_id);
    change_form("show-availability");
}


function set_lineup(match_id) {
    $("#set-lineup").addClass("avail-small");
    tennis_manager.matches.set_lineup(match_id);
    change_form("set-lineup");
}

function player_selected(selected_value) {
    return tennis_manager.matches.player_selected(selected_value);
}

function get_roster_data() {
    $("#sr_team_name").text($("#team_id option:selected").text());
    tennis_manager.roster.roster($("#team_id option:selected").val());
}

function update_player_form(player_id) {
    tennis_manager.roster.load_update_player_form(player_id);
    change_form("update-player");
}

function updateForfeitBtns(elem) {
    // alert(elem);
    // alert(elem.id);
    // alert(elem.value);
    tennis_manager.matches.forfeit_selected(elem.id, elem.value);
}

$().ready(function () {
    change_form(current_form_id);


    $("#c1-p2xx").mousedown(function (e) {
        if (tennis_manager.matches.player_selected(e.target.valueOf().value)) {
            e.preventDefault();
        }
    });
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

function swapClass(elem) {
    if (elem.checked) {
        elem.parentElement.parentElement.className = "player-avail";
    }
    else {
        elem.parentElement.parentElement.className = "";
    }
}

