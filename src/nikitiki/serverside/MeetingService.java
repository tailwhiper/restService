package nikitiki.serverside;


import model.Meeting;
import model.MeetingShortInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    //@Produces(MediaType.APPLICATION_JSON)
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
    @Path("/put/{title}/{summary}/{yystart}/{mmstart}/{ddstart}/{hhstart}/{minstart}/{yyend}/{mmend}/{ddend}/{hhend}/{minend}/{priority}")
    public Response addMeeting(@PathParam("title") String title,
                               @PathParam("summary") String summary,
                               @PathParam("yystart") int yystart,
                               @PathParam("mmstart") int mmstart,
                               @PathParam("ddstart") int ddstart,
                               @PathParam("hhstart") int hhstart,
                               @PathParam("minstart") int minstart,
                               @PathParam("yyend") int yyend,
                               @PathParam("mmend") int mmend,
                               @PathParam("ddend") int ddend,
                               @PathParam("hhend") int hhend,
                               @PathParam("minend") int minend,
                               @PathParam("priority") int priority) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String startDate = yystart + "/" + mmstart + "/" + ddstart + " " + hhstart + ":" + minstart;
        String endDate = yyend + "/" + mmend + "/" + ddend + " " + hhend + ":" + minend;
        try {
            dao.getInstance().AddMeeting(title, summary, sdf.parse(startDate), sdf.parse(endDate), priority);
        } catch (ParseException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("errror in dates").build();
        }


        String output = "Meeting has been added.";

        return Response.status(200).entity(output).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/put/new")

    public Response addMeeting(String json) {
        dao.getInstance().AddMeeting(json);
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
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteMeeting(@PathParam("id") int id) {
        return dao.getInstance().DeleteMeeting(id);

        
    }

}