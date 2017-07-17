$(document).ready(function () {
    var socket;
    var idList;
    var nickname;
    var receiver = $('#receiver-id').val();
    var rec = $('#reciver-username').val();

    function initializeChat() {
        var id = receiver;
        idList.push(id);
        chatboxManager.addBox(id,
            {
                dest: rec, // not used in demo
                title: rec,
                first_name: "" + id,
                last_name: ""
                //you can add your own options too
            });

    }

    function startClient() {
        console.log("opening socket");
        socket = new WebSocket("ws://" + document.domain + ":8080/Chat");
        socket.onopen = function () {
            nickname = $('#userId').val();
            socket.send(nickname);
        };

        socket.onmessage = function (a) {
            var message = a.data;
            if (message != "") {
                var inp = $('#input').val();
                inp = inp.concat('\n' + message.substring(0, message.indexOf("#")) + " says: " + message.substring(message.indexOf("#") + 1));
                var msg = message.substring(message.indexOf("#") + 1);
                $('#input').val(inp);
                receiver = message.substring(0, message.indexOf("@"));
                rec = message.substring(message.indexOf("@") + 1, message.indexOf("#"));
                initializeChat();
                var alert = new Audio("alert.mp3");
                alert.play();
                $("#" + idList[idList.indexOf(receiver)]).chatbox("option", "boxManager").addMsg(rec, msg);
            }

        };

        socket.onclose = function () {
            //event handler when the socket has been properly closed
        };

        socket.onerror = function () {
        }

    }

    //for sending data to the server
    function sendMessage() {
        if ($("#txtMessage").val()) {
            console.log("sent to socket.");
            socket.send(receiver + "$" + nickname + "@" + $('#username').val() + "#" + $("#txtMessage").val());
            $("#txtMessage").val("");
        }
    }

    $(document).ready(function () {
        startClient();
        idList = [];
        var broadcastMessageCallback = function (from, msg) {
            chatboxManager.addBox(idList[idList.indexOf(from)]);
            $("#txtMessage").val(msg);
            receiver = from;
            sendMessage();
            $("#" + idList[idList.indexOf(from)]).chatbox("option", "boxManager").addMsg($('#username').val(), msg);
        };


        // chatboxManager is excerpt from the original project
        // the code is not very clean, I just want to reuse it to manage multiple chatboxes
        chatboxManager.init({messageSent: broadcastMessageCallback});

        $("#link_add").click(function (event, ui) {

            $.ajax({
                url: '/ChatServlet',
                method: 'GET',
                dataType: 'html'
            }).done(function (response) {
                if (response != 'error') {
                    rec = response;
                    receiver = response
                    var id = receiver;
                    idList.push(id);
                    chatboxManager.addBox(id,
                        {
                            dest: rec,
                            title: "ონლაინ ჩატი",
                            first_name: receiver,
                            last_name: ""
                        });
                }
                event.preventDefault();
            }).fail(function () {
                event.preventDefault();
            });
        });

    });


});