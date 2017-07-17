function singUp() {
    var pass = md5($('#passInput').val());
    var data = {
        id : $('#idInput').val(),
        pass : pass,
        email : $('#mailInput').val(),
        name : $('#nameInput').val(),
        date : $('.dateInput').val(),
        mobile : $('#mobileInput').val()
    };
    $.ajax({
        url: '/SingUpServlet',
        method: 'POST',
        data: data,
        dataType: 'html'
    }).done(function (response) {
        if (response == "success") {
            window.location.replace("http://localhost:8080/confirmPage.jsp");
        }
    }).fail(function () {
    });
}

function logIn() {
    var pass = md5($('#passInputLogin').val());
    var data = {
        id : $('#idInputLogin').val(),
        pass : pass
    };
    $.ajax({
        url: '/LogInServlet',
        method: 'POST',
        data: data,
        dataType: 'html'
    }).done(function (response) {
        if (response == "false") {
            window.location.replace("http://localhost:8080/confirmPage.jsp");
        } else if (response == "true") {
            $('#loginModal').hide();
            window.location.replace("http://localhost:8080/");
        }
    }).fail(function () {
    });
}

function logOut() {
    $.ajax({
        url: '/LogOutServlet',
        method: 'POST'
    }).done(function (response) {
        window.location.replace("http://localhost:8080/");
    }).fail(function () {
        window.location.replace("http://localhost:8080/");
    });
}

function activateUser() {
    $('#loading').addClass('active');
    $.ajax({
        url: '/UserActivationServlet',
        method: 'POST',
        data:{
            code: $('#code').val(),
            action: $('#action').val()
        },
        dataType: 'html'
    }).done(function (response) {
        if (response == "activation-success") {
            sleep(0.5);
            $('#loading').removeClass('active');
            $('#errorMessage').hide();
            $('#successMessage').show();
            $('#activationMessage').hide();
            $('#incorrectMessage').hide();
            sleep(0.5);
            window.location.replace('http://localhost:8080/index.jsp');
        } else  if (response == "activation-incorrectCode") {
            sleep(1);
            $('#loading').removeClass('active');
            $('#errorMessage').hide();
            $('#successMessage').hide();
            $('#activationMessage').hide();
            $('#incorrectMessage').show();
        } else  if (response == "resend-success") {
            sleep(1);
            $('#loading').removeClass('active');
            $('#errorMessage').hide();
            $('#successMessage').hide();
            $('#activationMessage').show();
            $('#incorrectMessage').hide();
        } else {
            sleep(1);
            $('#loading').removeClass('active');
            $('#errorMessage').show();
            $('#successMessage').hide();
            $('#activationMessage').hide();
            $('#incorrectMessage').hide();
        }
    }).fail(function () {
        sleep(1);
        $('#loading').removeClass('active');
        $('#errorMessage').show();
        $('#successMessage').hide();
        $('#activationMessage').hide();
        $('#incorrectMessage').hide();
    });
}

function updateUser() {
    var pass = md5($('#updatePasswordInput').val());
    var data = {
        password : pass,
        email : $('#updateMailInput').val(),
        name : $('#updateNameInput').val(),
        mobile : $('#updateMobileInput').val()
    };
    $.ajax({
        url: '/UserUpdateServlet',
        method: 'POST',
        data: data,
        dataType: 'html'
    }).done(function (response) {
        if (response == "mailChanged") {
            window.location.replace("http://localhost:8080/confirmPage.jsp");
        } else {
            $('#updateModal').hide();
        }
    }).fail(function () {
    });
}


function sleep(seconds)
{
    var e = new Date().getTime() + (seconds * 1000);
    while (new Date().getTime() <= e) {}
}