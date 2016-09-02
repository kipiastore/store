<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Home Page</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.0.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/home.js" />"></script>
</head>
<body>
<a name="top"></a>
<form method="post">
    <header class="header header-anon header-sticky" id="Header_header">
        <ul class="header-inner">
            <li class="header-item header-menu">
                <a class="header-link" href="">Страница поиска</a>
                <ul class="header-menu-inner">
                    <li class="header-menu-item">
                        <a class="header-menu-link" href="">По рубрикам</a>
                    </li>
                    <li class="header-menu-item">
                        <a class="header-menu-link" href="">По районам</a>
                    </li>
                    <li class="header-menu-item">
                        <a class="header-menu-link" href="">По компаниям</a>
                    </li>
                </ul>
            </li>
            <li class="header-item">
                <a class="header-link header-link-button" href="login">Вход</a>
            </li>
        </ul>
        <div class="topHideButt" onClick="window.location.href='#top'"><br/>&uarr;</div>
    </header>
    <div class="header_img_variant_1" id="header_img">
        <h2 class="headline centered rua-p-c-white mtmb" style="margin-top: 0">Заголовок 1<small>Текст 2</small></h2>
        <div>
            <div class="searchform form-inline">
                <div class="rua-l-wrapper">
                    <div>
                        <div class="rua-g-right">
                            <a class="btn btn-primary btn-block submit_button" href="">Поиск</a>
                        </div>
                        <div class="rua-g-clearfix">
                            <div>
                                <input name="search" type="text" class="keyword input-block-level ui-autocomplete-input" placeholder="Введите ключевые слова" autocomplete="off"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="margin-top: -30px;">
        <div style="padding: 40px 0;">
            <div class="rua-l-wrapper" style="margin-top: 20px; margin-bottom: 40px;">
                <div class="row text-center">
                    <div class="col d-1of3 m-1of1">
                        <div class="box">
                            <div class="border"></div>
                            <div class="front"><strong>100%</strong><span>Проверенные<br>данные</span></div>
                            <div class="back">
                                <div class="rua-p-c-light">Наша команда профессиональных модераторов внимательно изучает новые ресурсы.</div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-1of3 m-1of1">
                        <div class="box">
                            <div class="border"></div>
                            <div class="front"><strong>53 765</strong><span>Наибольшее количество<br>предложений</span></div>
                            <div class="back">
                                <div class="rua-p-c-light">Ежемесячно на нашем сайте размещают более 500 новых ресурсов.</div>
                            </div>
                        </div>
                    </div>
                    <div class="col d-1of3 m-1of1">
                        <div class="box">
                            <div class="border"></div>
                            <div class="front"><strong>24/7</strong><span>Актуальные<br>данные</span></div>
                            <div class="back">
                                <div class="rua-p-c-light">Вы видите только актуальные предложения.</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr style="width: 300px; margin: 110px auto 35px auto;"/>
            <div class="rua-l-wrapper">
                <h2 class="headline centered mtmb">Поиск по рубрикам</h2>
                <div class="rptShort">
                    <div class="rptShort-inner">
                        <a data-id="3" href="">HR специалисты - Бизнес-тренеры<span class="rua-p-c-red">1605</span></a>
                        <a data-id="1" href="">IT<span class="rua-p-c-red">7797</span> </a>
                        <a data-id="33" href="">Автобизнес - Сервисное обслуживание<span class="rua-p-c-red">4123</span> </a>
                        <a data-id="11" href="">Административный персонал - Водители - Курьеры<span class="rua-p-c-red">11418</span> </a>
                        <a data-id="18" href="">Банки - Инвестиции - Лизинг<span class="rua-p-c-red">3938</span> </a>
                        <a data-id="6" href="">Бухгалтерия - Налоги - Финансы предприятия<span class="rua-p-c-red">4687</span> </a>
                        <a data-id="8" href="">Гостиницы - Рестораны - Кафе<span class="rua-p-c-red">8647</span> </a>
                        <a data-id="15" href="">Дизайн - Графика - Фото<span class="rua-p-c-red">1976</span> </a>
                        <a data-id="31" href=""> Закупки - Снабжение<span class="rua-p-c-red">1325</span> </a>
                        <a data-id="14" href=""> Консалтинг - Аналитика - Аудит
                            <span class="rua-p-c-red">533</span> </a> <a data-id="21" href=""> Культура - Шоу-бизнес - Развлечения
                        <span class="rua-p-c-red">1257</span> </a> <a data-id="5" href=""> Логистика - Таможня - Склад
                        <span class="rua-p-c-red">10454</span> </a> <a data-id="24" href=""> Маркетинг - Реклама - PR
                        <span class="rua-p-c-red">7159</span> </a> <a data-id="22" href=""> Медиа - Издательское дело
                        <span class="rua-p-c-red">2726</span> </a> <a data-id="9" href=""> Медицина - Фармацевтика - Здравоохранение
                        <span class="rua-p-c-red">4413</span> </a> <a data-id="25" href=""> Морские специальности
                        <span class="rua-p-c-red">117</span> </a> <a data-id="10" href=""> Наука - Образование - Перевод
                        <span class="rua-p-c-red">3692</span> </a> <a data-id="28" href=""> Недвижимость
                        <span class="rua-p-c-red">1349</span> </a> <a data-id="13" href=""> Некоммерческие организации - НГО
                        <span class="rua-p-c-red">332</span> </a> <a data-id="4" href=""> Охрана - Безопасность - Силовые структуры
                        <span class="rua-p-c-red">2362</span> </a> <a data-id="17" href=""> Продажи - Клиент-менеджмент
                        <span class="rua-p-c-red">25021</span> </a> <a data-id="32" href=""> Производство - Инженеры - Технологи
                        <span class="rua-p-c-red">14896</span> </a> <a data-id="20" href=""> Рабочие специальности - Персонал для дома
                        <span class="rua-p-c-red">13791</span> </a> <a data-id="26" href=""> Сельское хозяйство - Агробизнес - Лесное хозяйство
                        <span class="rua-p-c-red">3088</span> </a> <a data-id="7" href=""> Спорт - Красота - Оздоровление
                        <span class="rua-p-c-red">3869</span> </a> <a data-id="19" href=""> Страхование
                        <span class="rua-p-c-red">451</span> </a> <a data-id="27" href=""> Строительство - Архитектура
                        <span class="rua-p-c-red">8776</span> </a> <a data-id="30" href=""> Студенты - Начало карьеры - Без опыта
                        <span class="rua-p-c-red">11478</span> </a> <a data-id="2" href=""> Телекоммуникации - Связь
                        <span class="rua-p-c-red">2542</span> </a> <a data-id="12" href=""> Топ-менеджмент - Директора
                        <span class="rua-p-c-red">1433</span> </a> <a data-id="16" href=""> Торговля
                        <span class="rua-p-c-red">17500</span> </a> <a data-id="23" href=""> Туризм - Путешествия
                        <span class="rua-p-c-red">997</span> </a> <a data-id="29" href=""> Юристы, адвокаты, нотариусы
                        <span class="rua-p-c-red">1067</span> </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="background: #f7f7f7; overflow: hidden;">
        <h2 class="headline centered mtmb">Лучшие ресурсы</h2>
        <div class="rua-l-wrapper text-center mtmb">
            <table class="logos">
                <tbody>
                <tr>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Imperial-Tobako-Yukrejn---Imperial-Tobacco-Group---ITG_20141113030253.png);">Работа в Імперіал Тобако Юкрейн / Imperial Tobacco Group / ITG</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Samsung-Electronics_20160217035710.png);">Работа в Samsung Electronics</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/lifecell_20160622022534.png);">Работа в lifecell</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Tikkurila_20160617035953.png);">Работа в KOLORIT</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/AShAN_20160118022934.png);">Работа в АШАН</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Comfy_20160413033301.png);">Работа в Comfy</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/JTI---Japan-Tobacco-International_20141113030408.png);">Работа в JTI / Japan Tobacco International</a></td>
                </tr>
                <tr>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/PP-Etana-Pivdenj_20150618101439.png);">Работа в Точка, розничная сеть</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/MTS-Ukraina_20160317041048.jpg);">Работа в Vodafone</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/1-1-media_20141113025548.png);">Работа в 1+1 медіа</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/FORA-OOO_20160316125227.jpg);">Работа в ФОРА, ООО</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Filip-Morris-Ukraina--Filip-Morris-Ukraіna_20141113031000.png);">Работа в Філіп Морріс Україна*</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/British-American-Tobacco-Ukraіna_20141113025807.png);">Работа в British American Tobacco Україна</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Siljpo-Fozzy-Group---Fozzi-Grupp_20141114094220.png);">Работа в Сильпо, Fozzy Group / Фоззи Групп</a></td>
                </tr>
                <tr>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Kyivstar---Kievstar_20160210093519.png);">Работа в Kyivstar / Киевстар</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Epitsentr-K---Epitsentr-K_20141113025952.png);">Работа в Эпицентр К / Епіцентр К</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Fokstrot-Tehnika-dlya-doma_20111006112851.jpg);">Работа в Фокстрот. Техника для дома</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/TVOYa-LOGISTIKA-OOO_20160822095843.png);">Работа в Твоя Логистика, ООО</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Novaya-Liniya_20140603115045.jpg);">Работа в Новая Линия</a></td>
                    <td><a href="" title="" style="background-image: url(http://img1.rabota.com.ua/Data/cImg/Mondelis-Ukraіna-PAT---Kraft-Fudz-Ukraіna_20140410041234.png);">Работа в Монделіс Україна, ПАТ / Крафт Фудз Україна</a></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="rua-l-wrapper mtmb">
        <div class="row middle m-text-center">
            <div class="col d-1of2 m-1of1">
                <img src="<c:url value="/resources/images/mac.png"/>"/>
            </div>
            <div class="col d-1of2 m-1of1 m-pl-1of1">
                <h2 class="headline mtmb" id="headlinemtmb">Почему стоит с нами сотрудничать?</h2>
                <div class="rua-p-c-mid">
                    <ol>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ol>
                </div>
                <p class="mtmb">
                    <a class="btn btn-danger" href="">Оставить заявку</a>
                </p>
            </div>
        </div>
    </div>
    <div class="text-center" style="margin: 0; margin-bottom: -40px; background: #f7f7f7 url(<c:url value="/resources/images/header.jpg" />); overflow: hidden;">
        <h2 class="headline centered mtmb rua-p-c-white">еще крутой текст<br>самый крутой</h2>
        <p class="mtmb">
            <a class="btn btn-primary" href="">кнопка</a>
        </p>
    </div>
    <footer class="f">
        <div class="c">
            <ul>
                <li>
                    <b>store.ua</b>
                    <a href="">О store.ua</a>
                    <a href="">Конфиденциальность</a>
                    <a href="" rel="nofollow">Карта сайта</a>
                </li>
                <li>
                    <b>кому-то</b>
                    <a id="Footer_lnkVacancyLink" href="">Найти</a>
                    <a href="">Рассылка</a>
                    <a href="">Как</a>
                    <a href="">Советы</a>
                    <a href="" rel="nofollow">Архив</a>
                </li>
                <li>
                    <b>клиенту</b>
                    <a href="">Добавить</a>
                    <a href="">Рассылка</a>
                    <a href="">Наши услуги</a>
                    <a href="">Советы</a>
                </li>
                <li>
                    <a class="b" href="">Написать нам</a>
                    <div>
                        <p>Присылайте свои замечания и пожелания по работе и наполнению портала.</p>
                    </div>
                </li>
            </ul>
            <div class="media rua-p-t-12">
                <div class="media-body">
                    <b>ООО «Store Интернешнл» © 2016</b>
                    <br>Все права защищены и охраняются действующим законодательством Украины. Использование материалов с данного сайта возможно только с письменного разрешения компании ООО «Store Интернешнл». Администрация сайта не несет ответственности за содержание размещенных объявлений.
                </div>
            </div>
            <p class="cr"></p>
        </div>
    </footer>
</form>
</body>
</html>