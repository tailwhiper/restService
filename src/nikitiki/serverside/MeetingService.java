package nikitiki.serverside;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/meetings")
public class MeetingService {
    MeetingsDAO dao;


    @GET
    @Path("/get/{id}")
    public String getMeetingById(@PathParam("id") Integer id) {

        return dao.getInstance().getMeetingJson(id);
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
    @Path("/get/search/{part}")
    public String getMatched(@PathParam("part") String part) {

        return dao.getInstance().SearchJson(part);
    }

    @GET
    @Path("/get/date/{date}")
    public String getShortInfoForDate(@PathParam("date") String date) {

        return dao.getInstance().getTodayMeetingsShortInfoJson(date);
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

        return dao.getInstance().AddParticipantToMeetingJson(meetingId, name, job);
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteMeeting(@PathParam("id") int id) {
        return dao.getInstance().DeleteMeeting(id);


    }

}