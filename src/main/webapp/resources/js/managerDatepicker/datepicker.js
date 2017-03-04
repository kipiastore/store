$(document).ready(function() {
    $("#reminderDate").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Пн","Вт","Ср","Чт","Пт","Сб","Вс"],
        firstDay:0,
        dateFormat:"yy-mm-dd"
    });
    $("#actsDate").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Пн","Вт","Ср","Чт","Пт","Сб","Вс"],
        firstDay:0,
        dateFormat:"yy-mm-dd"

    });
    $("#notesDateFrom").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Пн","Вт","Ср","Чт","Пт","Сб","Вс"],
        firstDay:0,
        dateFormat:"yy-mm-dd"

    });
    $("#notesDateTo").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Пн","Вт","Ср","Чт","Пт","Сб","Вс"],
        firstDay:0,
        dateFormat:"yy-mm-dd"

    });
    $(".menuCalendar").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Пн","Вт","Ср","Чт","Пт","Сб","Вс"],
        firstDay:0,
        dateFormat:"yy-mm-dd",
        altField: "#hiddenSearchDate"
    });
});
