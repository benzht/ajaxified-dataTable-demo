// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function ($) {
        $('#spinner').ajaxStart(function () {
            $(this).fadeIn();
        }).ajaxStop(function () {
                $(this).fadeOut();
            });
    })(jQuery);
}

$(document).ready(function () {
    enableAjaxifiedDataTable();
    drawTable($('#employeeListing'), 1)
});

function drawTable(table, noOfPagesToCache) {
    $(table).dataTable({
        'aoColumnDefs': [
            {
                'bSortable': false,
                'aTargets': ['nosort']
            }
        ],
        "lengthMenu": [
            [10, 25, 50, 100, parseInt($(table).data('totalRecords'))],
            [10, 25, 50, 100, "All"]
        ],
        "serverSide": true,
        "bProcessing": true,
        "ajax": $.fn.dataTable.pipeline({
            url: $(table).data('ajax-url'),
            pages: noOfPagesToCache
        })
    });
}
