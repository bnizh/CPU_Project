<%@ page import="common.users.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="semantic/dist/semantic.css">
<link rel="stylesheet" type="text/css" href="semantic/calendar/dist/calendar.css">
<link type="text/css" href="css/jquery.ui.chatbox.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="https://code.jquery.com/jquery-3.0.0-beta1.min.js"></script>
<link rel="stylesheet" href="css/jquery-ui-1.8.2.custom.css"/>
<script type="text/javascript" src="scripts/jquery-ui-1.8.2.custom.min.js"></script>
<script src="semantic/dist/semantic.js"></script>
<script src="scripts/utils.js"></script>
<script src="http://www.myersdaily.org/joseph/javascript/md5.js"></script>
<script src="semantic/calendar/dist/calendar.js"></script>
<script src="scripts/functions.js"></script>
<script type="text/javascript" src="scripts/jquery.ui.chatbox.js"></script>
<script type="text/javascript" src="scripts/chatboxManager.js"></script>
<script type="text/javascript" src="scripts/chat.js"></script>
<head>
    <title>CPU</title>
</head>
<body>

<% User user = (User) request.getSession().getAttribute("user"); %>

<div style="height: 100%">
    <div style="height: 5%" class="ui top attached pointing menu">
        <div class="left menu">
            <a class="item" id="homePage" onclick="(function() {
          $('.item').removeClass('active');
          $('#homePage').addClass('active');
           $('#menu-content').replaceWith($('#calendar'));
        })()">
                მთავარი
            </a>
            <a class="item" id="contactPage" onclick="(function() {
          $('.item').removeClass('active');
          $('#contactPage').addClass('active');

          $('#menu-content').find('div').find('div').replaceWith($('#contactInfo').clone());
        })()">
                კონტაქტი
            </a>
            <a class="item" id="adminPage" onclick="(function() {
          $('.item').removeClass('active');
          $('#adminPage').addClass('active');
          $('#menu-content').find('div').find('div').replaceWith($('#adminPageContent').clone());
        })()">
                ადმინისტრატორი
            </a>
        </div>
        <div class="right menu">
            <div class="item">
                <%
                    if (user == null) {
                %>
                <div>
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
                <div class="ui dropdown icon button">
                    <i class="settings icon"></i>
                    <div class="menu">
                        <div class="item">
                            <div onclick="(function() {
                            $('#updateModal').show();
                        })()">მონაცემების რედაქტირება</div>
                        </div>
                        <div class="item">
                            <div onclick="logOut(); return false">სისტემიდან გასვლა</div>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    </div>
    <div style="height: 90%;" class="ui bottom attached segment">
        <div id="menu-content">
            <div>
                <div></div>
            </div>
        </div>
    </div>
</div>

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

<%
    if (user != null) {
%>
<script type="text/javascript">
    registerOperator();
</script>
<div style="width: 40%" id="updateModal" class="ui modal centerModalBig">
    <div class="header">შიყვანეთ მონაცემები</div>
    <div class="content">
        <form class="ui fluid form" onsubmit="updateUser(); return false">
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" id="updatePasswordInput" name="password" type="password" placeholder="პაროლი" onkeyup="(function() {
                      var progress = $('#progressBarUpdate');
                      progress.progress('set progress', calcPassScore($('#updatePasswordInput').val()));
                })()">
                <div class="ui corner label">
                    <i class="asterisk icon"></i>
                </div>
            </div>
            <div style="width: 90%; height: 30px; margin-bottom: 5px; margin-top: 0px" id="progressBarUpdate"
                 class="ui indicating progress">
                <div style="height: 30px;" class="bar">
                    <div style="height: 30px;" class="progress"></div>
                </div>
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input required="true" id="updateNameInput" type="text" placeholder="სახელი და გვარი" value="<%=user.getName()%>">
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input type="email" id="updateMailInput" placeholder="ელოქტრონული ფოსტა" value="<%=user.getEmail()%>" required="true">
                <div class="ui corner label">
                    <i class="mail icon"></i>
                </div>
            </div>
            <div style="width: 90%; margin-bottom: 5px" class="ui corner labeled input">
                <input type="number" id="updateMobileInput" placeholder="ტელეფონის ნომერი" value="<%=user.getMobile()%>">
            </div>
            <button class="ui submit positive button">შენახვა</button>
        </form>
    </div>
</div>
<% } %>

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
            <div style="width: 90% !important; margin-bottom: 5px !important;" class="ui calendar" id="date">
                <div style="width: 100% !important;" class="ui input left icon">
                    <i class="calendar icon"></i>
                    <input class="dateInput" style="width: 100% !important;" type="text"
                           placeholder="აირჩიეთ დაბადების თარიღი">
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
        $('.ui.dropdown')
            .dropdown();
    };
    $(document).keydown(function(event) {
        if (event.keyCode == 27) {
            $(".modal").hide();
        }
    });
</script>

<div style="display: none">
    <div id="contactInfo" style="position: relative; top: 30%; left:30%; width: 40%; height: 40%" >
        <table  class="ui definition table">
            <tbody>
            <tr>
                <td><i class="call square icon"></i>საკონტაქტო ნომერი</td>
                <td>598374781</td>
            </tr>
            <tr>
                <td><i class="mail icon"></i>საკონტაქტო მეილი</td>
                <td>bnizh14@freeuni.edu.ge</td>
            </tr>
            <tr>
                <td><i class="wechat icon"></i>ონლაინ დახმარება</td>
                <td style="align-content: center"><button class="ui button">ჩატის დაწყება</button></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="adminPageContent">
        <%@include file="admin.jsp" %>
    </div>
    <table style="position: relative; top: 30%; left:30%; width: 40%; height: 40%" id="contactInfo" class="ui definition table">
        <tbody>
        <tr>
            <td><i class="call square icon"></i>საკონტაქტო ნომერი</td>
            <td>598374781</td>
        </tr>
        <tr>
            <td><i class="mail icon"></i>საკონტაქტო მეილი</td>
            <td>bnizh14@freeuni.edu.ge</td>
        </tr>
        <tr>
            <td><i class="wechat icon"></i>ონლაინ დახმარება</td>
            <td style="align-content: center"><button id="link_add" class="ui button">ჩატის დაწყება</button></td>
        </tr>
        </tbody>
    </table>
</div>

<div id="chat">
    <input id="userId" type="hidden" value="<%=request.getSession().getId()%>">
    <textarea id="username"
              style="display: none"><%=(user != null ? user.getName() : "უცნობი")%></textarea>
    <TEXTAREA id="input" style="display: none"></TEXTAREA>
    <input type="text" id="txtMessage" style="display: none;margin-left: 45%;top: 5%;" class="form-control"
           placeholder="Type your message here."/>
    <input id="reciver-username" type="hidden" value="">
    <input id="reciver-id" type="hidden" value="">
</div>


</body>
</html>
