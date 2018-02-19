$(function () {
    $("#tabs").tabs();
    $("#tabs ul").on("tabsload",  function (event, ui) {
        alert("tabs create");
    });
});

// $().ready(function () {
//     if(window.location.hash) {
//         var hash = window.location.hash;
//         alert(hash)
//         var index = $("#tabs a[href=hash]").parent().index();
//         alert(index);
//         $('#tabs ul').tabs('select', 3);
//     }
// });


