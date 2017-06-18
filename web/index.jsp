<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">
<link rel="stylesheet" type="text/css" href="semantic/calendar/dist/calendar.min.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script
        src="https://code.jquery.com/jquery-3.1.1.min.js"
        integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
        crossorigin="anonymous"></script>
<script src="semantic/dist/semantic.min.js"></script>
<script src="scripts/utils.js"></script>
<script src="http://www.myersdaily.org/joseph/javascript/md5.js"></script>
<script src="semantic/calendar/dist/calendar.min.js"></script>
<head>
    <title>CPU</title>
    <script>
        $(document).ready(function () {
            alert("slide over the \"patient portal log-in\" logo to get info");
        })

    </script>
    <script>
        $(document).ready(function () {
            $('#logo').hover(function () {
                $('#infoModal').show();
            }, function () {
                $('#infoModal').hide();    })
        })

    </script>

</head>
<body>

<div  class="info">

    <img id="logo"src="250x250_PatientPortalLogin.png" alt="ლოგო არალი">
    <h1 id="description">CLINIC PATIENT UNIT</h1>
</div>


<div class="login">
    <button class="ui button" onclick="(function() {
            $('#signUpModal').hide();
            $('#loginModal').show();
        })()">სისტემაში შესვლა</button>
    <button class="ui positive button" onclick="(function() {
            $('#loginModal').hide();
            $('#signUpModal').show();
        })()">რეგისტრაცია</button>
</div>


<div  style = "width: 30%"id="infoModal"class="ui modal informationModal">
    <div class="header">ინფორმაცია</div>
    <div class="content">
        <p>აქ იქნება ინფორმაცია საიტის ვიზიტორებისთვის</p>
    </div>
</div>
<%--login and sign up modals--%>
<div style="width: 30%" id="loginModal" class="ui modal centerModal">
    <div class="header">შიყვანეთ მონაცემები</div>
    <div class="content">
        <form class="ui fluid form">
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" type="number" id="idInputLogin" placeholder="პირადი ნომერი">
                <div class="ui corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" type="password" id="passInputLogin" placeholder="პაროლი">
                <div class="ui corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
            <button class="ui submit positive button">სისტემაში შესვლა</button>
        </form>
    </div>
</div>

<div style="width: 40%" id="signUpModal" class="ui modal centerModalBig">
    <div class="header">შიყვანეთ მონაცემები</div>
    <div class="content">
        <form class="ui fluid form" onsubmit="(function(){
            let pass = md5($('#passInput').val());
            let data = {
                id : $('#idInput').val(),
                pass : pass,
                email : $('#mailInput').val(),
                name : $('#nameInput').val(),
//                date : $('#dateInput').val(),
                date : '04-04-1994',
                mobile : $('#mobileInput').val()
            };
            $.ajax({
                url: '/signUp',
                method: 'POST',
                data: data,
                dataType: 'html'
            });

        })()">
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" type="number" id="idInput" placeholder="პირადი ნომერი">
                <div class="ui corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
            <div style="width: 90% !important; margin-bottom: 5px !important;" class="ui calendar" id="date">
                <div style="width: 100% !important;" class="ui input left icon">
                    <i class="calendar icon"></i>
                    <input style="width: 100% !important;" type="text" placeholder="აირჩიეთ დაბადების თარიღი">
                </div>
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" type="password" id="passInput" placeholder="პაროლი" onkeyup="(function() {
                      var progress = $('#progressBar');
                      progress.progress('set progress', calcPassScore($('#passInput').val()));

                })()">
                <div class="ui corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
            <div style="width: 90%; height: 30px; margin-bottom: 5px; margin-top: 0px" id="progressBar" class="ui indicating progress">
                <div style="height: 30px;" class="bar">
                    <div style="height: 30px;" class="progress"></div>
                </div>
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" type="text" placeholder="სახელი და გვარი" id="nameInput">
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input type="email" placeholder="ელოქტრონული ფოსტა" id="mailInput" required="true">
                <div class="ui corner label">
                    <i class="mail icon"></i>
                </div>
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input type="number" placeholder="ტელეფონის ნომერი" id="mobileInput">
            </div>
            <button class="ui submit positive button">რეგისტრაცია</button>
        </form>
    </div>
</div>
<script type="text/javascript">
    window.onload = function() {
        $('#date').calendar({
            type: 'date'
        });
    };
</script>
</body>
</html>
