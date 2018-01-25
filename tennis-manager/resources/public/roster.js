/**
 * Created by Richard on 6/5/2017.
 */
var current_form_id = "add-player";

function change_form(new_form_id) {
    $("#status-panel").css("display", "none");
    $("#" + current_form_id).css("display", "none");
    current_form_id = new_form_id;
    team_changed($("#team_id option:selected").val());
    if(current_form_id == "update-player") {
        $("#team_id").prop("disabled", true);
        $("#updateplayerform").data('validator').resetForm();
        $("#updateplayerform").data('validator').reset();
    }
    else {
        $("#team_id").prop("disabled", false);
    }
    $("#" + new_form_id).css("display", "block");
}

function team_changed(selected_team) {
    $("#ap_team_id").val(selected_team);
    if(current_form_id == "show-roster") {
        get_roster_data();
    }

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
    $("#addplayerform").validate({
        focusCleanup: true,
        rules: {
            first_name: {
                required: true,
                minlength: 2
            },
            last_name: {
                required: true,
                minlength: 2
            }
        },
        messages: {
            first_name: "First name required to be at least 2 characters",
            last_name: "Last name required to be at least 2 characters"
        }
    });
    $("#updateplayerform").validate({
        focusCleanup: true,
        rules: {
            first_name: {
                required: true,
                minlength: 2
            },
            last_name: {
                required: true,
                minlength: 2
            }
        },
        messages: {
            first_name: "First name required to be at least 2 characters",
            last_name: "Last name required to be at least 2 characters"
        }
    });
    $("#ap_first_name").keyup(function () {
        $("#ap_first_name").valid();
    });
    $("#ap_last_name").keyup(function () {
        $("#ap_last_name").valid();
    });
    $("#up_first_name").keyup(function () {
        $("#up_first_name").valid();
    });
    $("#up_last_name").keyup(function () {
        $("#up_last_name").valid();
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
