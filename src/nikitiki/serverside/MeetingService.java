package nikitiki.serverside;


import model.Meeting;
import model.MeetingShortInfo;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/meetings")
public class MeetingService {
    MeetingsDAO dao;



    @GET
    @Produces("text/html")
    @Path("/get/{id}")
    public Meeting getMeetingById(@PathParam("id") Integer id) {

        return
                dao.getInstance().getMeeting(id);
    }

    @GET

    @Path("/get/all/full")
    public Response getAllMeetings() {
        //fillMeetings();
        return Response.status(200).entity(dao.getInstance().getAllMeetingsJson()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/all/short")
    public List<MeetingShortInfo> getAllShortInfo() {

        return dao.getInstance().getAllMeetingsShortInfo();
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
    @Path("/put/{title}/{summary}/{yystart}/{mmstart}/{ddstart}/{hhstart}/{minstart}/{yyend}/{mmend}/{ddend}/{hhend}/{minend}/{priority}")
    public Response addMeeting(@PathParam("title") String title, @PathParam("summary") String summary, @PathParam("yystart") int yystart, @PathParam("mmstart") int mmstart, @PathParam("ddstart") int ddstart, @PathParam("hhstart") int hhstart, @PathParam("minstart") int minstart, @PathParam("yyend") int yyend, @PathParam("mmend") int mmend, @PathParam("ddend") int ddend, @PathParam("hhend") int hhend, @PathParam("minend") int minend, @PathParam("priority") int priority) {


        dao.getInstance().AddMeeting(title, summary, new DateTime(yystart, mmstart, ddstart, hhstart, minstart), new DateTime(yyend, mmend, ddend, hhend, minend), priority);


        String output = "Meeting has been added.";

        return Response.status(200).entity(output).build();
    }

    @PUT
    @Path("/put/{id}/{name}/{job}")
    public Response addParticipantToMeeting(@PathParam("id") int meetingId, @PathParam("name") String name, @PathParam("job") String job) {


        dao.getInstance().AddParticipantToMeeting(meetingId, name, job);


        String output = "Participant has been added to meeting.";

        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteMeeting(@PathParam("id") int id) {
        dao.getInstance().DeleteMeeting(id);
        String output = "Meeting has been deleted successfully.";
        return Response.status(200).entity(output).build();
    }

}