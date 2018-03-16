/**
 * Created by Richard on 6/5/2017.
 */

function team_schedule() {
    tennis_manager.core.schedule($("#season-list option:selected").val(), $("#team-list option:selected").val());
}
;
function show_select_form() {
    $(".schedule").css("visibility", "hidden");
    $(".schedule-form").css("display", "block");
}
;

function show_schedule() {
    $(".schedule-form").css("display", "none");
    $(".schedule").css("visibility", "visible");
};