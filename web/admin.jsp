<%--
  Created by IntelliJ IDEA.
  User: andro
  Date: 7/17/17
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="ui icon input focus" id="adminDiv">
    <input type="text" placeholder="Search..." id="PersonSearch"  onkeyup="(function(){
        $('#searchButton').trigger('onclick');
    })()">
    <i class="inverted circular search link icon" id="searchButton" onclick="(function(){
        var data = {
            id : $('#PersonSearch').val()
        };
    $.ajax({
        url: '/SearchServlet',
        method: 'POST',
        data: data,
        dataType: 'html'
    }).done(function (response) {
        adminSearchSuccess(response);
    }).fail(function () {
    });
    })()"></i>
</div>
<div id="userGrid"></div>