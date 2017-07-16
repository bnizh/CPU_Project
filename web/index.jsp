<%@ page import="common.users.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">
<link rel="stylesheet" type="text/css" href="semantic/calendar/dist/calendar.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
        integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
        crossorigin="anonymous"></script>
<script src="semantic/dist/semantic.min.js"></script>
<script src="scripts/utils.js"></script>
<script src="http://www.myersdaily.org/joseph/javascript/md5.js"></script>
<script src="semantic/calendar/dist/calendar.js"></script>
<script src="scripts/functions.js"></script>
<head>
    <title>CPU</title>
</head>
<body>
<%
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
%>
<div class="login" id="authBtns">
    <button class="ui button" onclick="(function() {
            $('#signUpModal').hide();
            $('#loginModal').show();
        })()">სისტემაში შესვლა
    </button>
    <button class="ui positive button" onclick="(function() {
            $('#loginModal').hide();
            $('#signUpModal').show();
        })()">რეგისტრაცია
    </button>
</div>
<% } else { %>
<div class="login" id="userInfo">
    <span>მოგესალმებით <%=user.getName()%></span>
    <button class="ui positive button" onclick="logOut()">სისტემიდან გასვლა</button>
</div>
<% } %>

<%--login and sign up modals--%>
<div style="width: 30%" id="loginModal" class="ui modal centerModal">
    <div class="header">შიყვანეთ მონაცემები</div>
    <div class="content">
        <form class="ui fluid form" onsubmit="logIn(); return false;">
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
        <form class="ui fluid form" onsubmit="singUp(); return false">
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" type="number" id="idInput" placeholder="პირადი ნომერი">
                <div class="ui corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
            <div style="width: 90% !important; margin-bottom: 5px !important;" class="ui calendar"  id="date">
                <div style="width: 100% !important;" class="ui input left icon">
                    <i class="calendar icon"></i>
                    <input  class="dateInput" style="width: 100% !important;" type="text" placeholder="აირჩიეთ დაბადების თარიღი">
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
            <div style="width: 90%; height: 30px; margin-bottom: 5px; margin-top: 0px" id="progressBar"
                 class="ui indicating progress">
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
    window.onload = function () {
        $('#date').calendar({
            type: 'date'
        });
    };
</script>
</body>
</html>
