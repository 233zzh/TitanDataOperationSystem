/*******************************************/
// Basic Date Range Picker
/*******************************************/
$('.daterange').daterangepicker();

/*******************************************/
// Date & Time
/*******************************************/
$('.datetime').daterangepicker({
    timePicker: true,
    timePickerIncrement: 30,
    locale: {
        format: 'MM/DD/YYYY h:mm A'
    },
    startDate: moment().subtract('days', 7)
});

/*******************************************/
//Calendars are not linked
/*******************************************/
$('.timeseconds').daterangepicker({
    timePicker: true,
    timePickerIncrement: 30,
    timePicker24Hour: true,
    timePickerSeconds: true,
    locale: {
        format: 'MM-DD-YYYY h:mm:ss'
    },

});

/*******************************************/
// Single Date Range Picker
/*******************************************/
$('.singledate').daterangepicker({
    singleDatePicker: true,
    showDropdowns: true
});

/*******************************************/
// Auto Apply Date Range
/*******************************************/
$('.autoapply').daterangepicker({
    autoApply: true,
});

/*******************************************/
// Calendars are not linked
/*******************************************/
$('.linkedCalendars').daterangepicker({
    linkedCalendars: false,
});

/*******************************************/
// Date Limit
/*******************************************/
$('.dateLimit').daterangepicker({
    dateLimit: {
        days: 7
    },
});

/*******************************************/
// Show Dropdowns
/*******************************************/
$('.showdropdowns').daterangepicker({
    showDropdowns: true,
});

/*******************************************/
// Show Week Numbers
/*******************************************/
$('.showweeknumbers').daterangepicker({
    showWeekNumbers: true,
});

/*******************************************/
// Date Ranges
/*******************************************/
$('.dateranges').daterangepicker({
    ranges: {
        'Today': [moment(), moment()],
        'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
        'Last 7 Days': [moment().subtract(6, 'days'), moment()],
        'Last 30 Days': [moment().subtract(29, 'days'), moment()],
        'This Month': [moment().startOf('month'), moment().endOf('month')],
        'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
    }
});

/*******************************************/
// Always Show Calendar on Ranges
/*******************************************/
$('.shawCalRanges').daterangepicker({
    ranges: {
        'Today': [moment(), moment()],
        'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
        'Last 7 Days': [moment().subtract(6, 'days'), moment()],
        'Last 30 Days': [moment().subtract(29, 'days'), moment()],
        'This Month': [moment().startOf('month'), moment().endOf('month')],
        'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
    },
    alwaysShowCalendars: true,
});

/*******************************************/
// Top of the form-control open alignment
/*******************************************/
$('.drops').daterangepicker({
    drops: "up" // up/down
});

/*******************************************/
// Custom button options
/*******************************************/
$('.buttonClass').daterangepicker({
    drops: "up",
    buttonClasses: "btn",
    applyClass: "btn-info",
    cancelClass: "btn-danger"
});

/*******************************************/
// Language
/*******************************************/
$('.localeRange').daterangepicker({
    ranges: {
        "Aujourd'hui": [moment(), moment()],
        'Hier': [moment().subtract('days', 1), moment().subtract('days', 1)],
        'Les 7 derniers jours': [moment().subtract('days', 6), moment()],
        'Les 30 derniers jours': [moment().subtract('days', 29), moment()],
        'Ce mois-ci': [moment().startOf('month'), moment().endOf('month')],
        'le mois dernier': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
    },
    locale: {
        applyLabel: "Vers l'avant",
        cancelLabel: 'Annulation',
        startLabel: 'Date initiale',
        endLabel: 'Date limite',
        customRangeLabel: 'SÃ©lectionner une date',
        // daysOfWeek: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi','Samedi'],
        daysOfWeek: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'],
        monthNames: ['Janvier', 'fÃ©vrier', 'Mars', 'Avril', 'ÐœÐ°i', 'Juin', 'Juillet', 'AoÃ»t', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        firstDay: 1
    }
});


