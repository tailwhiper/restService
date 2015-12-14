<%@ page import="model.Meeting" %>

<%@ page import="model.Participant" %>
<%@ page import="nikitiki.serverside.MeetingsDAO" %>
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
    <title>$Title$</title>
</head>
<body>
<%! MeetingsDAO meetingsDao; %>
<table title="All meetings">

    <th>id</th>
    <th>Title</th>
    <th>Summary</th>
    <th>Starts at</th>
    <th>Ends at</th>
    <th>Participants</th>

    <% List<Meeting> meetings = meetingsDao.getInstance().getAllMeetings();
        for (Meeting m : meetings) {
    %>
    <tr>
        <td><%= m.getId()%>
        </td>
        <td><%= m.getTitle()%>
        </td>
        <td><%= m.getSummary()%>
        </td>
        <td><%=(m.getStartdate().getDayOfMonth()+"."+m.getStartdate().getMonthOfYear()+"."+m.getStartdate().getYear())%>
        <td><%=(m.getEnddate().getDayOfMonth()+"."+m.getEnddate().getMonthOfYear()+"."+m.getEnddate().getYear())%>
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

</body>
</html>
