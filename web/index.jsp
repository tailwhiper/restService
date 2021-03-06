<%@ page import="model.Meeting" %>

<%@ page import="model.Participant" %>
<%@ page import="nikitiki.serverside.MeetingsDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 12.12.2015
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meetings Admin page</title>
</head>
<body>


<%! MeetingsDAO meetingsDao; %>
<h2>Input new Meeting</h2>
<h3>Info</h3>
Title:<input id="title"/>
Summary:<input id="summary"/>
<br>
<br>
<h3>Start date</h3>
<br>year:<input id="startyear"/>
month:<input id="startmonth"/>
day:<input id="startday"/>
hour:<input id="starthour"/>
minute:<input id="startminute"/>
<br>
<h3>End date</h3>
<br>year:<input id="endyear"/>
month:<input id="endmonth"/>
day:<input id="endday"/>
hour:<input id="endhour"/>
minute:<input id="endminute"/>
<br>
<br>priority:<input id="priority"/>

<br>
<button id="submit" onclick="putMeeting()">Add</button>
<table title="All meetings" border="2">

    ё
    <th>id</th>
    <th>Title</th>
    <th>Summary</th>
    <th>Starts at</th>
    <th>Ends at</th>
    <th>Priority</th>
    <th>Participants</th>

    <% SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy-HH:mm");
        List<Meeting> meetings = meetingsDao.getInstance().getAllMeetings();
        for (Meeting m : meetings) {
    %>
    <tr>
        <td><%= m.getId()%>
        </td>
        <td><%= m.getTitle()%>
        </td>
        <td><%= m.getSummary()%>
        </td>
        <td><%=sdf.format(m.getStartdate())%>
        <td><%=sdf.format(m.getEnddate()) %>
        <td><%= m.getPriority()%>
        </td>
                <%
       StringBuilder parts = new StringBuilder();
       for(Participant p:m.getParticipants())     {
       parts.append(p.getName());
       parts.append(" ");
       parts.append(p.getJob());
       parts.append("; ");
       }

    %>
        <td><%=parts.toString()%>
        </td>
    </tr>
    <%

        }

    %>

</table>
<script type="text/javascript">
    function putMeeting() {
        var x = new XMLHttpRequest();

        x.open("PUT", "http://localhost:8080/meetings-app/rest/meetings/put/" +
                document.getElementById('title').value + "/" +
                document.getElementById('summary').value + "/" +
                document.getElementById('startyear').value + "." +
                document.getElementById('startmonth').value + "." +
                document.getElementById('startday').value + "-" +
                document.getElementById('starthour').value + ":" +
                document.getElementById('startminute').value + "/" +
                document.getElementById('endyear').value + "." +
                document.getElementById('endmonth').value + "." +
                document.getElementById('endday').value + "-" +
                document.getElementById('endhour').value + ":" +
                document.getElementById('endminute').value + "/" +
                document.getElementById('priority').value, true);
        x.setRequestHeader("Authorization", "Basic " + btoa("nikita" + ":" + "password"));
        // x.onload = function () {
        //     alert(x.responseText);
        // }
        x.send(null);
        setTimeout(window.location.reload(), 2000);
    }

</script>
</body>
</html>
