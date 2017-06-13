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
            $('#loginModal').show();
        })()">სისტემაში შესვლა</button>
        <button class="ui positive button" onclick="(function() {
            $('#signUpModal').show();
        })()">რეგისტრაცია</button>
    </div>

    <%--login and sign up modals--%>
    <div style="width: 35%" id="loginModal" class="ui modal">
        <div class="header">შიყვანეთ მონაცემები</div>
        <div class="content">
            <div style="width: 90%" class="ui left corner labeled input">
                <input required="true" type="number" type="text" placeholder="პირადი ნომერი">
                <div class="ui left corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
            <div style="width: 90%" class="ui left corner labeled input">
                <input required="true" type="password" type="text" placeholder="პაროლი">
                <div class="ui left corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
        </div>
        <div class="actions">
            <div class="ui positive button">სისტემაში შესვლა</div>
        </div>
    </div>

    <div style="width: 35%" id="signUpModal" class="ui modal">
        <div class="header">შიყვანეთ მონაცემები</div>
        <div class="content">
            <div style="width: 90%" class="ui left corner labeled input">
                <input required="true" type="number" type="text" placeholder="პირადი ნომერი">
                <div class="ui left corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
            <div style="width: 90%" class="ui left corner labeled input">
                <input required="true" type="password" type="text" placeholder="პაროლი" onkeydown="(function() {
                      var progress = $('#progressBar');
                      progress.progress('increment', 10);
                })()">
                <div class="ui left corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
            <div style="width: 90%" id="progressBar" class="ui indicating progress">
                <div class="bar">
                    <div class="progress"></div>
                </div>
            </div>
        </div>
        <div class="actions">
            <div class="ui positive button">რეგისტრაცია</div>
        </div>
    </div>

</body>
</html>
