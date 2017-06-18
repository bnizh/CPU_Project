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
<head>
    <title>CPU</title>
</head>
<body>

<div id="loading" class="ui dimmer">
    <div class="ui massive text loader">Loading</div>
</div>

<div style="position: absolute; left: 30%; top:30%; width: 40%; height: 40%">

    <form class="ui fluid form" onsubmit="(function() {
         $('#loading').addClass('active');
         setTimeout(function () {
            $.ajax({
                url: '/userActivation',
                method: 'POST',
                data:{
                   code: $('#code').val(),
                   action: $('#action').val()
                },
                dataType: 'html'
            }).done(function (response) {
                if (response.get === 'incorrectCode') {
                    $('#activationMessage').css('display', 'none');
                    $('#errorMessage').css('display', 'block');
                } else if (response.get === 'success') {
                    $('#activationMessage').css('display', 'none');
                    $('#successMessage').css('display', 'block');
                    setTimeout(function() {
                      window.location =
                    },1000)
                }
            });
            $('#loading').removeClass('active');
        }, 1000);
    })()">
        <div id="activationMessage" class="ui icon message">
            <i class="inbox icon"></i>
            <div class="content">
                <div class="header">
                    მომხმარებლის აქტივაცია
                </div>
                <p>შეიყვანოთ კოდი რომელიც მოგივიდათ რეგისტრაციისას მითითებულ ელექტრონულ ფოსტაზე</p>
            </div>
        </div>

        <div id="successMessage" style="display: none" class="ui message">
            <div class="header">
                მომხმარებელი წარმატებით გააქტიურდა!
            </div>
            <p>მადლობა რომ სარგებლობთ ჩვენი სერვისით</p>
        </div>

        <div id="errorMessage" style="display: none" class="ui error message">
            <div class="header">
                დაფიქსირდა შეცდომა
            </div>
            <p>თქვენ მიერ შეყვანილი კოდი არ ემთხვევა მეილზე გამოგზავნილს. გთხოვთ თავიდან შეიყვანოთ ან მოითხოვოთ ახალი კოდი</p>
        </div>

        <div style="width:100%; margin-bottom: 10px" class="ui corner labeled input">
            <input id="code" type="text" id="codeInput" placeholder="შეიყვანეთ აქტივაციის კოდი">
            <div class="ui corner label">
                <i class="asterisk icon"></i>
            </div>
        </div>
        <div>
            <button style="float:left" class="ui submit positive button" onclick="(function() {
                $('#action').val('verify')
            })()">დადასტურება</button>
            <button style="float:right" class="ui submit button" onclick="(function() {
                $('#action').val('resend')
            })()">თავიდან გამოგზავნა</button>
        </div>
        <input hidden id="action" value=""/>
    </form>

</div>
</body>
</html>
