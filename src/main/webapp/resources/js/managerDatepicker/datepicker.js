$(document).ready(function() {
    $("#reminderDate").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Пн","Вт","Ср","Чт","Пт","Сб","Вс"],
        firstDay:1,
        dateFormat:"dd.mm.yy"
    });
    $("#actsDate").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Пн","Вт","Ср","Чт","Пт","Сб","Вс"],
        firstDay:1,
        dateFormat:"yy-mm-dd"

    });
    $("#notesDateFrom").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Пн","Вт","Ср","Чт","Пт","Сб","Вс"],
        firstDay:1,
        dateFormat:"dd.mm.yy"

    });
    $("#notesDateTo").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Пн","Вт","Ср","Чт","Пт","Сб","Вс"],
        firstDay:1,
        dateFormat:"dd.mm.yy"

    });
    $(".menuCalendar").datepicker({
        monthNames:["Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"],
        dayNamesMin:["Пн","Вт","Ср","Чт","Пт","Сб","Вс"],
        firstDay:1,
        dateFormat:"yy-mm-dd",
        altField: "#hiddenSearchDate"
    });
});
