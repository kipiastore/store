$(document).ready(function() {
    $("#reminderDate").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Вс","Пн","Вт","Ср","Чт","Пт","Сб"],
        firstDay:1,
        dateFormat:"yy-mm-dd"
    });
    $("#actsDate").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Вс","Пн","Вт","Ср","Чт","Пт","Сб"],
        firstDay:1,
        dateFormat:"yy-mm-dd"

    });
    $("#notesDateFrom").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Вс","Пн","Вт","Ср","Чт","Пт","Сб"],
        firstDay:1,
        dateFormat:"yy-mm-dd"

    });
    $("#notesDateTo").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Вс","Пн","Вт","Ср","Чт","Пт","Сб"],
        firstDay:1,
        dateFormat:"yy-mm-dd"

    });
    $(".menuCalendar").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Вс","Пн","Вт","Ср","Чт","Пт","Сб"],
        firstDay:1,
        dateFormat:"yy-mm-dd",
        altField: "#hiddenSearchDate"
    });
});
