<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="ru" class=""><head id="Head1">
    <meta charset="UTF-8">
    <link rel="alternate" media="handheld" href="http://rabota.ua/mobile/list">
    <link rel="stylesheet" href="http://css.rabota.com.ua/v78.2/theme/rua/css/bundles/jobsearch.css">
    <style>
        #header_img.header_img_variant_1 {
            background-image: url(http://img1.rabota.com.ua/static/2015/04/1.jpg);
        }
        #header_img.header_img_variant_2 {
            background-image: url(http://img1.rabota.com.ua/static/2015/04/2.jpg);
        }
        #header_img.header_img_variant_3 {
            background-image: url(http://img1.rabota.com.ua/static/2015/04/3.jpg);
        }
        #header_img .headline > small {
            display: block;
            color: inherit;
        }
        .registeruser3 hr {
            border-bottom-color: #ccc;
        }
        .box, .box .border {
            width: 220px;
            height: 220px;
        }
        .box {
            position: relative;
            margin: auto;
        }
        .box .border {
            border: 1px solid #B71810;
            border-radius: 50%;
            -moz-transition: border-radius .3s ease-in-out;
            -o-transition: border-radius .3s ease-in-out;
            -webkit-transition: border-radius .3s ease-in-out;
            transition: border-radius .3s ease-in-out;
        }
        .box:hover .border {
            border-radius: 20px;
        }
        .front {
            position: absolute;
            left: 0;
            right: 0;
            top: 29%;
            text-align: center;
            font-size: 17px;
            opacity: 1;
        }
        .front strong {
            font: normal 32px/1.5 Arial;
            color: #B71810;
            display: block;
        }
        .back {
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            padding: 20px;
            color: #7e7e7e;
            text-align: left;
            opacity: 0;
            -moz-transform: scale(0);
            -ms-transform: scale(0);
            -o-transform: scale(0);
            -webkit-transform: scale(0);
            transform: scale(0);
        }
        .box:hover .back {
            opacity: 1;
            -moz-transform: scale(1);
            -ms-transform: scale(1);
            -o-transform: scale(1);
            -webkit-transform: scale(1);
            transform: scale(1);
        }
        .box:hover .front {
            opacity: 0;
            -moz-transform: scale(1.5);
            -ms-transform: scale(1.5);
            -o-transform: scale(1.5);
            -webkit-transform: scale(1.5);
            transform: scale(1.5);
        }
        .front,
        .box:hover .front,
        .back,
        .box:hover .back {
            -moz-transition: opacity .1s ease-in-out .1s, transform .1s ease-in-out .1s;
            -o-transition: opacity .1s ease-in-out .1s, transform .1s ease-in-out .1s;
            -webkit-transition: opacity .1s ease-in-out .1s, transform .1s ease-in-out .1s;
            transition: opacity .1s ease-in-out .1s, transform .1s ease-in-out .1s;
        }
        .rptShort {
            overflow: hidden;
            text-align: center;
        }
        .rptShort a {
            background-position: 50% 0;
            background-repeat: no-repeat;
            display: inline-block;
            padding-top: 70px;
            text-align: center;
            color: #7e7e7e;
            text-decoration: none;
            float: left;
            width: 20%;
            box-sizing: border-box;
            padding: 70px 10px 0 10px;
            margin-bottom: 20px;
        }

        .rptShort a:nth-child(5n+1) {
            clear: left;
        }

        .rptShort a:hover {
            color: #B71810;
            text-decoration: none;
        }

        .rptShort span {
            display: block;
        }

        .rptShort a[data-id="3"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/hr.png');
        }

        .rptShort a[data-id="3"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/hr_red.png');
        }

        .rptShort a[data-id="1"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/it.png');
        }

        .rptShort a[data-id="1"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/it_red.png');
        }

        .rptShort a[data-id="33"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/auto.png');
        }

        .rptShort a[data-id="33"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/auto_red.png');
        }

        .rptShort a[data-id="11"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/administrative_stuff.png');
        }

        .rptShort a[data-id="11"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/administrative_stuff_red.png');
        }

        .rptShort a[data-id="18"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/banks.png');
        }

        .rptShort a[data-id="18"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/banks_red.png');
        }

        .rptShort a[data-id="6"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/accounting.png');
        }

        .rptShort a[data-id="6"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/accounting_red.png');
        }

        .rptShort a[data-id="8"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/hotels.png');
        }

        .rptShort a[data-id="8"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/hotels_red.png');
        }

        .rptShort a[data-id="15"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/design.png');
        }

        .rptShort a[data-id="15"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/design_red.png');
        }

        .rptShort a[data-id="31"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/purchasing.png');
        }

        .rptShort a[data-id="31"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/purchasing_red.png');
        }

        .rptShort a[data-id="14"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/consulting.png');
        }

        .rptShort a[data-id="14"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/consulting_red.png');
        }

        .rptShort a[data-id="21"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/culture.png');
        }

        .rptShort a[data-id="21"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/culture_red.png');
        }

        .rptShort a[data-id="5"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/logistics.png');
        }

        .rptShort a[data-id="5"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/logistics_red.png');
        }

        .rptShort a[data-id="24"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/marketing.png');
        }

        .rptShort a[data-id="24"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/marketing_red.png');
        }

        .rptShort a[data-id="22"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/media.png');
        }

        .rptShort a[data-id="22"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/media_red.png');
        }

        .rptShort a[data-id="9"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/medicine.png');
        }

        .rptShort a[data-id="9"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/medicine_red.png');
        }

        .rptShort a[data-id="25"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/sea.png');
        }

        .rptShort a[data-id="25"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/sea_red.png');
        }

        .rptShort a[data-id="10"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/science.png');
        }

        .rptShort a[data-id="10"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/science_red.png');
        }

        .rptShort a[data-id="28"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/real_estate.png');
        }

        .rptShort a[data-id="28"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/real_estate_red.png');
        }

        .rptShort a[data-id="13"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/ngo.png');
        }

        .rptShort a[data-id="13"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/ngo_red.png');
        }

        .rptShort a[data-id="4"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/safety.png');
        }

        .rptShort a[data-id="4"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/safety_red.png');
        }

        .rptShort a[data-id="17"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/sales.png');
        }

        .rptShort a[data-id="17"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/sales_red.png');
        }

        .rptShort a[data-id="32"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/manufacture.png');
        }

        .rptShort a[data-id="32"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/manufacture_red.png');
        }

        .rptShort a[data-id="20"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/workforce.png');
        }

        .rptShort a[data-id="20"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/workforce_red.png');
        }

        .rptShort a[data-id="26"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/agriculture.png');
        }

        .rptShort a[data-id="26"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/agriculture_red.png');
        }

        .rptShort a[data-id="7"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/sport.png');
        }

        .rptShort a[data-id="7"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/sport_red.png');
        }

        .rptShort a[data-id="19"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/insurance.png');
        }

        .rptShort a[data-id="19"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/insurance_red.png');
        }

        .rptShort a[data-id="27"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/construction.png');
        }

        .rptShort a[data-id="27"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/construction_red.png');
        }

        .rptShort a[data-id="30"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/students.png');
        }

        .rptShort a[data-id="30"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/students_red.png');
        }

        .rptShort a[data-id="2"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/telecommunications.png');
        }

        .rptShort a[data-id="2"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/telecommunications_red.png');
        }

        .rptShort a[data-id="12"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/top.png');
        }

        .rptShort a[data-id="12"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/top_red.png');
        }

        .rptShort a[data-id="16"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/trade.png');
        }

        .rptShort a[data-id="16"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/trade_red.png');
        }

        .rptShort a[data-id="23"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/tourism.png');
        }

        .rptShort a[data-id="23"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/tourism_red.png');
        }

        .rptShort a[data-id="29"] {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/law.png');
        }

        .rptShort a[data-id="29"]:hover {
            background-image: url('http://img1.rabota.com.ua/static/2014/11/rubrics/law_red.png');
        }
        .rptShort a {
            width: 50%;
            background-position: 0 50%;
            padding: 0;
            padding-left: 30px;
            text-align: left;
            background-size: 16px;
            margin-bottom: 5px;
        }

        .rptShort span {
            /*display: inline;
         margin-left: .5em;*/
            float: right;
        }

        .rptShort a:nth-child(5n+1) {
            clear: none;
        }

        .rptShort a:nth-child(2n+1) {
            clear: left;
            padding-right: 20px;
        }
        @media only screen and (max-width: 600px) {
            .rptShort a {
                width: auto;
                display: block;
                background-position: 0 50%;
                padding: 10px 20px 10px 30px;
                text-align: left;
                background-size: 16px;
                margin: 0px 20px;
                float: none;
                margin-right: .5em;
            }

            .rptShort span {
                display: inline;
            }

            .rptShort a:nth-child(5n+1) {
                clear: none;
            }

            .rptShort-inner {
                margin-bottom: 20px;
            }
        }
    </style>
</head>
<body class="rua-l-body">
<form method="post" action="" id="form1" class="inversed-header jobsearcher anonymous">
    <header id="Header_header" class="header header-anon header-sticky">
        <ul class="header-inner">

            <li class="header-item header-menu">
                <a class="header-link" href="">Найти что-то
                </a>
                <ul class="header-menu-inner">
                    <li class="header-menu-item "><a class="header-menu-link" href="">По рубрикам
                    </a>
                    </li>
                    <li class="header-menu-item "><a class="header-menu-link" href="">по районам</a>
                    </li>
                    <li class="header-menu-item "><a class="header-menu-link" href="">По компаниям
                    </a>
                    </li>
                </ul>
            </li>
            <li class="header-item"><a class="header-link header-link-button" href=""> Разместить что-то</a></li>

        </ul>

    </header>
    <div id="header_img" class="header_img_variant_1">
        <h2 class="headline centered rua-p-c-white mtmb" style="margin-top: 0">еще текст<small class="rua-p-white">текст</small></h2>
        <div id="search_holder">
            <div id="beforeContentZone_vacSearch_pnlsearch" class="searchform form-inline" onkeypress="javascript:return WebForm_FireDefaultButton(event, 'beforeContentZone_vacSearch_lnkSearch')">
                <div class="rua-l-wrapper">
                    <div class="main-form">
                        <div class="rua-g-right"> <a id="beforeContentZone_vacSearch_lnkSearch" class="btn btn-primary btn-block submit_button" href="javascript:__doPostBack('ctl00$beforeContentZone$vacSearch$lnkSearch','')">Поиск</a> </div>

                        <div class="rua-g-clearfix">
                            <div class="row-fluid"> <span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input name="ctl00$beforeContentZone$vacSearch$Keyword" type="text" id="beforeContentZone_vacSearch_Keyword" class="keyword input-block-level ui-autocomplete-input" placeholder="Введите ключевые" autocomplete="off"> </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
    <div style="margin-top: -30px;">
        <div style="padding: 40px 0; background: white;">
            <div class="rua-l-wrapper" style="margin-top: 20px; margin-bottom: 40px;">
                <div class="row text-center">
                    <div class="col d-1of3 m-1of1">
                        <div class="box">
                            <div class="border">&nbsp;</div>
                            <div class="front"><strong>100%</strong><span>проверенные<br>кто-то</span></div>
                            <div class="back">
                                <div class="rua-p-c-light">Наша команда профессиональных модераторов внимательно изучает кого-то и что-то.. там делает</div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-1of3 m-1of1">
                        <div class="box">
                            <div class="border">&nbsp;</div>
                            <div class="front"><strong>24/7</strong><span>актуальные<br>данные</span></div>
                            <div class="back">
                                <div class="rua-p-c-light">вы видите только актуальные предложения и т.д.</div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-1of3 m-1of1">
                        <div class="box">
                            <div class="border">&nbsp;</div>
                            <div class="front"><strong>153 000</strong><span>наибольшее количество<br>всяко херни</span></div>
                            <div class="back">
                                <div class="rua-p-c-light">Ежемесячно компании размещают на нашем сайте более..
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr style="width: 190px; margin: 20px auto 35px auto;">
            <div class="rua-l-wrapper"><h2 class="headline centered mtmb">Поиск вакансий по рубрикам в Одессе </h2>
                <div class="rptShort"> <div class="rptShort-inner"> <a data-id="3" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/hr-%d1%81%d0%bf%d0%b5%d1%86%d0%b8%d0%b0%d0%bb%d0%b8%d1%81%d1%82%d1%8b/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> HR специалисты - Бизнес-тренеры
                    <span class="rua-p-c-red">1605</span> </a> <a data-id="1" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d0%b8%d0%bd%d1%82%d0%b5%d1%80%d0%bd%d0%b5%d1%82%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> IT
                    <span class="rua-p-c-red">7797</span> </a> <a data-id="33" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d0%b0%d0%b2%d1%82%d0%be%d0%b1%d0%b8%d0%b7%d0%bd%d0%b5%d1%81%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Автобизнес - Сервисное обслуживание
                    <span class="rua-p-c-red">4123</span> </a> <a data-id="11" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d0%be%d1%84%d0%b8%d1%81%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Административный персонал - Водители - Курьеры
                    <span class="rua-p-c-red">11418</span> </a> <a data-id="18" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d0%b1%d0%b0%d0%bd%d0%ba%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Банки - Инвестиции - Лизинг
                    <span class="rua-p-c-red">3938</span> </a> <a data-id="6" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d1%84%d0%b8%d0%bd%d0%b0%d0%bd%d1%81%d0%b0%d1%85/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Бухгалтерия - Налоги - Финансы предприятия
                    <span class="rua-p-c-red">4687</span> </a> <a data-id="8" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b3%d0%be%d1%81%d1%82%d0%b8%d0%bd%d0%b8%d1%86%d1%8b-%d1%80%d0%b5%d1%81%d1%82%d0%be%d1%80%d0%b0%d0%bd%d1%8b/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Гостиницы - Рестораны - Кафе
                    <span class="rua-p-c-red">8647</span> </a> <a data-id="15" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b4%d0%b8%d0%b7%d0%b0%d0%b9%d0%bd-%d0%b3%d1%80%d0%b0%d1%84%d0%b8%d0%ba%d0%b0-%d1%84%d0%be%d1%82%d0%be/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Дизайн - Графика - Фото
                    <span class="rua-p-c-red">1976</span> </a> <a data-id="31" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d1%81%d0%bd%d0%b0%d0%b1%d0%b6%d0%b5%d0%bd%d0%b8%d0%b8/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Закупки - Снабжение
                    <span class="rua-p-c-red">1325</span> </a> <a data-id="14" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d0%ba%d0%be%d0%bd%d1%81%d0%b0%d0%bb%d1%82%d0%b8%d0%bd%d0%b3%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Консалтинг - Аналитика - Аудит
                    <span class="rua-p-c-red">533</span> </a> <a data-id="21" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d1%88%d0%be%d1%83_%d0%b1%d0%b8%d0%b7%d0%bd%d0%b5%d1%81%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Культура - Шоу-бизнес - Развлечения
                    <span class="rua-p-c-red">1257</span> </a> <a data-id="5" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%bb%d0%be%d0%b3%d0%b8%d1%81%d1%82%d0%b8%d0%ba%d0%b0-%d1%82%d0%b0%d0%bc%d0%be%d0%b6%d0%bd%d1%8f-%d1%81%d0%ba%d0%bb%d0%b0%d0%b4/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Логистика - Таможня - Склад
                    <span class="rua-p-c-red">10454</span> </a> <a data-id="24" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d0%bc%d0%b0%d1%80%d0%ba%d0%b5%d1%82%d0%b8%d0%bd%d0%b3%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Маркетинг - Реклама - PR
                    <span class="rua-p-c-red">7159</span> </a> <a data-id="22" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d1%81%d0%bc%d0%b8/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Медиа - Издательское дело
                    <span class="rua-p-c-red">2726</span> </a> <a data-id="9" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d0%bc%d0%b5%d0%b4%d0%b8%d1%86%d0%b8%d0%bd%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Медицина - Фармацевтика - Здравоохранение
                    <span class="rua-p-c-red">4413</span> </a> <a data-id="25" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d0%bc%d0%be%d1%80%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Морские специальности
                    <span class="rua-p-c-red">117</span> </a> <a data-id="10" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%bd%d0%b0%d1%83%d0%ba%d0%b0-%d0%be%d0%b1%d1%80%d0%b0%d0%b7%d0%be%d0%b2%d0%b0%d0%bd%d0%b8%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Наука - Образование - Перевод
                    <span class="rua-p-c-red">3692</span> </a> <a data-id="28" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%bd%d0%b5%d0%b4%d0%b2%d0%b8%d0%b6%d0%b8%d0%bc%d0%be%d1%81%d1%82%d1%8c/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Недвижимость
                    <span class="rua-p-c-red">1349</span> </a> <a data-id="13" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%bd%d0%b5%d0%ba%d0%be%d0%bc%d0%bc%d0%b5%d1%80%d1%87%d0%b5%d1%81%d0%ba%d0%b8%d0%b5_%d0%be%d1%80%d0%b3%d0%b0%d0%bd%d0%b8%d0%b7%d0%b0%d1%86%d0%b8%d0%b8/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Некоммерческие организации - НГО
                    <span class="rua-p-c-red">332</span> </a> <a data-id="4" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b1%d0%b5%d0%b7%d0%be%d0%bf%d0%b0%d1%81%d0%bd%d0%be%d1%81%d1%82%d1%8c/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Охрана - Безопасность - Силовые структуры
                    <span class="rua-p-c-red">2362</span> </a> <a data-id="17" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d0%bf%d1%80%d0%be%d0%b4%d0%b0%d0%b6%d0%b0%d1%85/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Продажи - Клиент-менеджмент
                    <span class="rua-p-c-red">25021</span> </a> <a data-id="32" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d0%bf%d1%80%d0%be%d0%b8%d0%b7%d0%b2%d0%be%d0%b4%d1%81%d1%82%d0%b2%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Производство - Инженеры - Технологи
                    <span class="rua-p-c-red">14896</span> </a> <a data-id="20" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d1%80%d0%b0%d0%b1%d0%be%d1%87%d0%b8%d0%b5_%d1%81%d0%bf%d0%b5%d1%86%d0%b8%d0%b0%d0%bb%d1%8c%d0%bd%d0%be%d1%81%d1%82%d0%b8/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Рабочие специальности - Персонал для дома
                    <span class="rua-p-c-red">13791</span> </a> <a data-id="26" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d1%81%d0%b5%d0%bb%d1%8c%d1%81%d0%ba%d0%be%d0%b5_%d1%85%d0%be%d0%b7%d1%8f%d0%b9%d1%81%d1%82%d0%b2%d0%be/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Сельское хозяйство - Агробизнес - Лесное хозяйство
                    <span class="rua-p-c-red">3088</span> </a> <a data-id="7" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b2_%d1%81%d0%bf%d0%be%d1%80%d1%82%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Спорт - Красота - Оздоровление
                    <span class="rua-p-c-red">3869</span> </a> <a data-id="19" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d1%81%d1%82%d1%80%d0%b0%d1%85%d0%be%d0%b2%d0%b0%d0%bd%d0%b8%d0%b5/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Страхование
                    <span class="rua-p-c-red">451</span> </a> <a data-id="27" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d1%81%d1%82%d1%80%d0%be%d0%b8%d1%82%d0%b5%d0%bb%d1%8c%d1%81%d1%82%d0%b2%d0%be-%d0%b0%d1%80%d1%85%d0%b8%d1%82%d0%b5%d0%ba%d1%82%d1%83%d1%80%d0%b0/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Строительство - Архитектура
                    <span class="rua-p-c-red">8776</span> </a> <a data-id="30" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d0%b4%d0%bb%d1%8f_%d1%81%d1%82%d1%83%d0%b4%d0%b5%d0%bd%d1%82%d0%be%d0%b2/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Студенты - Начало карьеры - Без опыта
                    <span class="rua-p-c-red">11478</span> </a> <a data-id="2" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d1%82%d0%b5%d0%bb%d0%b5%d0%ba%d0%be%d0%bc%d0%bc%d1%83%d0%bd%d0%b8%d0%ba%d0%b0%d1%86%d0%b8%d0%b8-%d1%81%d0%b2%d1%8f%d0%b7%d1%8c/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Телекоммуникации - Связь
                    <span class="rua-p-c-red">2542</span> </a> <a data-id="12" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d1%82%d0%be%d0%bf-%d0%bc%d0%b5%d0%bd%d0%b5%d0%b4%d0%b6%d0%bc%d0%b5%d0%bd%d1%82/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Топ-менеджмент - Директора
                    <span class="rua-p-c-red">1433</span> </a> <a data-id="16" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d1%82%d0%be%d1%80%d0%b3%d0%be%d0%b2%d0%bb%d1%8f/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Торговля
                    <span class="rua-p-c-red">17500</span> </a> <a data-id="23" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d1%82%d1%83%d1%80%d0%b8%d0%b7%d0%bc-%d0%bf%d1%83%d1%82%d0%b5%d1%88%d0%b5%d1%81%d1%82%d0%b2%d0%b8%d1%8f/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Туризм - Путешествия
                    <span class="rua-p-c-red">997</span> </a> <a data-id="29" href="/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/%d1%8e%d1%80%d0%b8%d1%81%d1%82%d1%8b-%d0%b0%d0%b4%d0%b2%d0%be%d0%ba%d0%b0%d1%82%d1%8b/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"> Юристы, адвокаты, нотариусы
                    <span class="rua-p-c-red">1067</span> </a> </div> </div></div>
        </div>
    </div>
    <div style="background: #f7f7f7; overflow: hidden;">
        <h2 class="headline centered mtmb"> Лучшие кто-то там
        </h2>
        <div class="rua-l-wrapper text-center mtmb">

            <table id="beforeContentZone_vacTop_CompanyGallaryDataList" class="logos" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
                <tbody>
                <tr>
                    <td><a href="/company182210" title="Работа в Імперіал Тобако Юкрейн / Imperial Tobacco Group / ITG" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Imperial-Tobako-Yukrejn---Imperial-Tobacco-Group---ITG_20141113030253.png);">Работа в Імперіал Тобако Юкрейн / Imperial Tobacco Group / ITG</a></td>
                    <td><a href="/company1218975" title="Работа в Samsung Electronics" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Samsung-Electronics_20160217035710.png);">Работа в Samsung Electronics</a></td>
                    <td><a href="/company1014" title="Работа в lifecell" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/lifecell_20160622022534.png);">Работа в lifecell</a></td>
                    <td><a href="/company1024716" title="Работа в KOLORIT" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Tikkurila_20160617035953.png);">Работа в KOLORIT</a></td>
                    <td><a href="/company233178" title="Работа в АШАН" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/AShAN_20160118022934.png);">Работа в АШАН</a></td>
                    <td><a href="/company70957" title="Работа в Comfy" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Comfy_20160413033301.png);">Работа в Comfy</a></td>
                    <td><a href="/jti" title="Работа в JTI / Japan Tobacco International" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/JTI---Japan-Tobacco-International_20141113030408.png);">Работа в JTI / Japan Tobacco International</a></td>
                </tr>



                <tr>
                    <td><a href="/company3302955" title="Работа в Точка, розничная сеть" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/PP-Etana-Pivdenj_20150618101439.png);">Работа в Точка, розничная сеть</a></td>
                    <td><a href="/company806" title="Работа в Vodafone" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/MTS-Ukraina_20160317041048.jpg);">Работа в Vodafone</a></td>
                    <td><a href="/company828062" title="Работа в 1+1 медіа" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/1-1-media_20141113025548.png);">Работа в 1+1 медіа</a></td>
                    <td><a href="/company39974" title="Работа в ФОРА, ООО" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/FORA-OOO_20160316125227.jpg);">Работа в ФОРА, ООО</a></td>
                    <td><a href="/philipmorris" title="Работа в Філіп Морріс Україна*" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Filip-Morris-Ukraina--Filip-Morris-Ukraіna_20141113031000.png);">Работа в Філіп Морріс Україна*</a></td>
                    <td><a href="/company771" title="Работа в British American Tobacco Україна" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/British-American-Tobacco-Ukraіna_20141113025807.png);">Работа в British American Tobacco Україна</a></td>
                    <td><a href="/company562625" title="Работа в Сильпо, Fozzy Group / Фоззи Групп" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Siljpo-Fozzy-Group---Fozzi-Grupp_20141114094220.png);">Работа в Сильпо, Fozzy Group / Фоззи Групп</a></td>
                </tr>

                <tr>
                    <td><a href="/company874" title="Работа в Kyivstar / Киевстар" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Kyivstar---Kievstar_20160210093519.png);">Работа в Kyivstar / Киевстар</a></td>
                    <td><a href="/company799" title="Работа в Эпицентр К / Епіцентр К" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Epitsentr-K---Epitsentr-K_20141113025952.png);">Работа в Эпицентр К / Епіцентр К</a></td>
                    <td><a href="/company378807" title="Работа в Фокстрот. Техника для дома" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Fokstrot-Tehnika-dlya-doma_20111006112851.jpg);">Работа в Фокстрот. Техника для дома</a></td>
                    <td><a href="/company3177004" title="Работа в Твоя Логистика, ООО" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/TVOYa-LOGISTIKA-OOO_20160822095843.png);">Работа в Твоя Логистика, ООО</a></td>
                    <td><a href="/company648657" title="Работа в Новая Линия" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Novaya-Liniya_20140603115045.jpg);">Работа в Новая Линия</a></td>
                    <td><a href="/company469382" title="Работа в Монделіс Україна, ПАТ / Крафт Фудз Україна" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Mondelis-Ukraіna-PAT---Kraft-Fudz-Ukraіna_20140410041234.png);">Работа в Монделіс Україна, ПАТ / Крафт Фудз Україна</a></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="rua-l-wrapper mtmb">
        <div class="row middle m-text-center">
            <div class="col d-1of2 m-1of1"> <img src="http://img1.rabota.com.ua/static/2014/11/MAC.png"> </div>
            <div class="col d-1of2 m-1of1 m-pl-1of1">
                <h2 class="headline mtmb"> Почему стоит с нами сотрудничать?</h2>
                <div class="rua-p-c-mid" style="display: inline-block; margin: auto; text-align: left;">
                    <ol>
                        <li>пункт</li>
                        <li>2</li>
                        <li>3</li>
                    </ol>
                </div>
                <p class="mtmb"> <a class="btn btn-danger" href="/jobsearch/cvwelcome" onclick="_gaq.push(['_trackEvent', 'Home', 'Click', 'CVBuilder']);">связаться с нами</a> </p>
            </div>
        </div>
    </div>
    <div class="text-center" style="margin: 0; margin-bottom: -40px; background: #f7f7f7 url(http://img1.rabota.com.ua/static/2014/11/bg1.jpg); overflow: hidden;">
        <h2 class="headline centered mtmb rua-p-c-white">еще крутой текст<br>самый крутой</h2>
        <p class="mtmb"> <a class="btn btn-primary" href="/%D0%B2%D0%B0%D0%BA%D0%B0%D0%BD%D1%81%D0%B8%D0%B8">зачем эта кнопка?</a>
        </p>
    </div>
    <div class="rua-l-wrapper"> </div>
    <footer class="f">
        <div class="c">
            <ul>
                <li> <b>store.ua</b> <a href="http://hrsovet.rabota.ua/about_rabota_ua"> О store.ua</a> <a href="http://hrsovet.rabota.ua/terms"> Конфиденциальность</a> <a href="/jobsearch/sitemap" rel="nofollow"> Карта сайта</a>  </li>
                <li> <b>кому-то</b> <a id="Footer_lnkVacancyLink" href="/%D0%B2%D0%B0%D0%BA%D0%B0%D0%BD%D1%81%D0%B8%D0%B8">Найти </a>  <a href="/jobsearch/notepad/new_mailing_list"> Рассылка </a> <a href="http://blog.rabota.ua/rezyume-na-myllyon-kak-harantyrovano-popast-na-sobesedovanye/" onclick="_gaq.push(['_trackEvent', 'click_from_RUA', 'footer_menu']); return true;"> Как </a> <a href="http://blog.rabota.ua" onclick="_gaq.push(['_trackEvent', 'click_from_RUA', 'footer_menu']); return true;"> Советы </a> <a href="/%D0%B2%D0%B0%D0%BA%D0%B0%D0%BD%D1%81%D0%B8%D0%B8/%D0%B0%D1%80%D1%85%D0%B8%D0%B2" rel="nofollow"> Архив </a> </li>
                <li> <b>клиенту</b> <a href="/employer/add_vacancy"> Добавить </a> <a href="/%D1%80%D0%B5%D0%B7%D1%8E%D0%BC%D0%B5"> Поиск </a> <a href="/employer/find/cv_list"> Рассылка </a> <a href="/employer/notepad/services"> Наши услуги</a> <a href="http://hrsovet.rabota.ua"> Советы </a> </li>
                <li>
                    <a class="b" href="http://rabota.ua/service/feedback?page=%2f"> Написать Меру города</a>
                    <div>
                        <p>Присылайте свои замечания и пожелания по работе и наполнению портала.</p>
                    </div>
                </li>
            </ul>
            <div class="media rua-p-t-12">
                <div class="media-body"> <b> ООО «Store Интернешнл»
                    © 2016</b> <br> Все права защищены и охраняются действующим законодательством Украины. Использование материалов с данного сайта возможно только с письменного разрешения компании ООО «Store Интернешнл». Администрация сайта не несет ответственности за содержание размещенных объявлений.
                </div>
            </div>
            <p class="cr"></p>
        </div>
    </footer>
</form>
</body></html>
