<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script
        src="https://code.jquery.com/jquery-3.1.1.min.js"
        integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
        crossorigin="anonymous"></script>
<script src="semantic/dist/semantic.min.js"></script>
<head>
    <title>CPU</title>
</head>
<body>
<div class="login">
    <button class="ui button" onclick="(function() {
            if ($('#signUpModal').hasClass('active')) {
                $('#signUpModal').hide();
            }
            $('#loginModal').show();

        })()">სისტემაში შესვლა</button>
    <button class="ui positive button" onclick="(function() {
            if ($('#loginModal').hasClass('active')) {
                $('#loginModal').hide();
            }
            $('#signUpModal').show();
        })()">რეგისტრაცია</button>
</div>

<%--login and sign up modals--%>
<div style="width: 40%" id="loginModal" class="ui modal centerModal">
    <div class="header">შიყვანეთ მონაცემები</div>
    <div class="content">
        <form class="ui fluid form">
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" type="number" type="text" placeholder="პირადი ნომერი">
                <div class="ui corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" type="password" type="text" placeholder="პაროლი">
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
        <form class="ui fluid form">
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" type="number" type="text" placeholder="პირადი ნომერი">
                <div class="ui corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" type="password" type="text" placeholder="პაროლი" onkeydown="(function() {
                      var progress = $('#progressBar');
                      progress.progress('increment', 10);
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
                <input required="true" type="text" type="text" placeholder="სახელი და გვარი">
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input type="text" type="email" placeholder="ელოქტრონული ფოსტა">
                <div class="ui corner label">
                    <i class="mail icon"></i>
                </div>
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input type="number" placeholder="ტელეფონის ნომერი">
            </div>
            <button class="ui submit positive button">რეგისტრაცია</button>
        </form>
    </div>
</div>
</body>
</html>
