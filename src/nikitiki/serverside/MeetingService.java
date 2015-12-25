package nikitiki.serverside;


import model.Meeting;
import model.MeetingShortInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/meetings")
public class MeetingService {
    MeetingsDAO dao;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Meeting getMeetingById(@PathParam("id") Integer id) {

        return
                dao.getInstance().getMeeting(id);
    }

    @GET

    @Path("/get/all/full")
    public Response getAllMeetings() {
        return Response.status(200).entity(dao.getInstance().getAllMeetingsJson()).build();
    }

    @GET
    @Path("/get/all/short")
    public String getAllShortInfo() {

        return dao.getInstance().getAllMeetingsShortInfoJson();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/search/{part}")
    public List<MeetingShortInfo> getMatched(@PathParam("part") String part) {

        return dao.getInstance().Search(part);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/date/{date}")
    public List<MeetingShortInfo> getShortInfoForDate(@PathParam("date") long date) {

        return dao.getInstance().getTodayMeetingsShortInfo(date);
    }

    // post to add data
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/put/{title}/{summary}/{startDate}/{endDate}/{priority}")
    public String addMeeting(@PathParam("title") String title,
                             @PathParam("summary") String summary,
                             @PathParam("startDate") String startDate,
                             @PathParam("endDate") String endDate,
                             @PathParam("priority") int priority) {


        return dao.getInstance().AddMeeting(title, summary, startDate, endDate, priority);
    }


    /*@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/put/new")

    public Response addMeeting(Meeting  json) {
         json.toString();
       // dao.getInstance().AddMeeting(json);
        String output = "Meeting has been added.";
        return Response.status(200).entity(output).build();
    }*/

    @PUT
    @Path("/put/{id}/{name}/{job}")
    public String addParticipantToMeeting(@PathParam("id") int meetingId, @PathParam("name") String name, @PathParam("job") String job) {


        dao.getInstance().AddParticipantToMeeting(meetingId, name, job);


        String output = "Participant has been added to meeting.";
        return output;

    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteMeeting(@PathParam("id") int id) {
        return dao.getInstance().DeleteMeeting(id);


    }

}