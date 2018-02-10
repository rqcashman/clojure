/**
 * Created by Richard on 6/5/2017.
 */

var current_form_id = "add-club";

$().ready(function () {
    $("#" + current_form_id).css("display", "block");
    $("#addclubform").validate({
        rules: {
            club_name: {
                required: true,
                minlength: 4
            }
        },
        messages: {
            club_name: {
                required: "Club name required",
                minlength: "Club name must be least 4 characters"
            }
        }
    });
    $("#addseasonform").validate({
        focusCleanup: true,
        rules: {
            season: {
                required: true,
                minlength: 4
            },
            start_date: {
                required: true
            },
            end_date: {
                required: true
            }
        },
        messages: {
            season: "Season name required to be at least 4 characters",
            start_date: "Start date required",
            end_date: {
                required: "End date required",
            }
        }
    });
    $("#addteamform").validate({
        focusCleanup: true,
        rules: {
            team_name: {
                required: true,
                minlength: 4
            }
        },
        messages: {
            team_name:
                {
                    required: "Team name required",
                    minlength: "Team name must be at least 4 characters"
                }
        }
    });
    $("#season").keyup(function () {
        $("#season").valid();
    });
    $("#club_name").keyup(function () {
        $("#club_name").valid();
    });
    $("#team_name").keyup(function () {
        $("#team_name").valid();
    });
    $("#start_date").datepicker(
        {
            defaultDate: "+1d",
            autoSize: true
        });
    $("#end_date").datepicker(
        {
            defaultDate: "+17w",
            autoSize: true,
        });
    $.validator.addMethod("enddate", function (value, element) {
        var startdatevalue = $(".startdate").val();
        return Date.parse(startdatevalue) < Date.parse(value);
    }, "End Date must be greater than Start Date.");
    $('#match_time').timepicker({
        minTime: '8:00am',
        maxTime: '6:00pm',
        timeFormat: 'h:i A',
        useSelect: true,
        showDuration: false
    });
});

function change_form(new_form_id) {
    $("#status-panel").css("display", "none");
    $("#" + current_form_id).css("display", "none");
    $("#" + new_form_id).css("display", "block");
    current_form_id = new_form_id;
}

function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

function dismissStatusForm() {
    $("#status-panel").css("display", "none");
    $("#status-content").text("Processing...");
    $("#" + current_form_id).css("display", "block");
}

function processRequest(form_id, uri, title) {
    if ($(form_id).valid()) {
        tennis_manager.core.db_update_request(form_id, uri, title);
        $("#" + current_form_id).css("display", "none");
        $("#status-panel").css("display", "block");
    }
    return false;
}

function team_schedule_all() {
    tennis_manager.core.schedule-all($("#season-id option:selected").val(), $("#team-id option:selected").val());
};
