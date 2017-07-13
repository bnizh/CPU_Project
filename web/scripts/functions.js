function singUp() {
    var pass = md5($('#passInput').val());
    var data = {
        id : $('#idInput').val(),
        pass : pass,
        email : $('#mailInput').val(),
        name : $('#nameInput').val(),
//                date : $('#dateInput').val(),
        date : '04-04-1994',
        mobile : $('#mobileInput').val()
    };
    $.ajax({
        url: '/SingUpServlet',
        method: 'POST',
        data: data,
        dataType: 'html'
    }).done(function (response) {
        if (response == "success") {
            alert('test');
            window.location.replace("http://localhost:8080/confirmPage.jsp");
            console.log("redirect");
            console.log(window.location);
        }
    }).fail(function () {
        alert('error');
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
            console.log("redirect");
            console.log(window.location);
        } else if (response == "true") {
            $('#loginModal').hide();
        }
    }).fail(function () {
        alert('error');
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

function sleep(seconds)
{
    var e = new Date().getTime() + (seconds * 1000);
    while (new Date().getTime() <= e) {}
}