package nikitiki.serverside;

import com.google.gson.Gson;
import model.Meeting;
import model.MeetingShortInfo;
import model.Participant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class MeetingsDAO {
    private static MeetingsDAO instance = new MeetingsDAO();
    private Gson gson;
    private int currentId;
    private HashMap<Integer, Meeting> allmeetings;

    private MeetingsDAO() {
        allmeetings = new HashMap<Integer, Meeting>();
        currentId = 0;
        gson = new Gson();
    }

    public static MeetingsDAO getInstance() {
        return instance;
    }

    public List<Meeting> getAllMeetings() {

        return new ArrayList<Meeting>(allmeetings.values());

    }

    public String getAllMeetingsJson() {

        return gson.toJson(new ArrayList<Meeting>(allmeetings.values()));

    }

    public String getAllMeetingsShortInfoJson() {
        List<MeetingShortInfo> infos = new ArrayList<MeetingShortInfo>();
        List<Meeting> meetings = new ArrayList<Meeting>(allmeetings.values());
        for (Meeting m : meetings) {
            infos.add(m.toShortInfo());
        }
        return gson.toJson(infos);
    }
    public List<MeetingShortInfo> getAllMeetingsShortInfo() {
        List<MeetingShortInfo> infos = new ArrayList<MeetingShortInfo>();
        List<Meeting> meetings = new ArrayList<Meeting>(allmeetings.values());
        for (Meeting m : meetings) {
            infos.add(m.toShortInfo());
        }
        return infos;
    }

    public List<MeetingShortInfo> getTodayMeetingsShortInfo(long datelong) {
        Date date = new Date(datelong);
        List<MeetingShortInfo> infos = new ArrayList<MeetingShortInfo>();
        List<Meeting> meetings = new ArrayList<Meeting>(allmeetings.values());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

        for (Meeting m : meetings) {
            if (sdf.format(m.getStartdate()).equals(sdf.format(date)))
                infos.add(m.toShortInfo());
        }
        return infos;
    }

    public Meeting getMeeting(int id) {
        return allmeetings.get(id);
    }

    public void AddMeeting(String title, String summary, Date datestart, Date dateend, int priority) {
        Meeting meeting = new Meeting(currentId, title, summary, datestart, dateend, null, priority);
        allmeetings.put(currentId, meeting);
        currentId++;

    }

    public void AddMeeting(String json) {
        Meeting m = gson.fromJson(json, Meeting.class);
        m.setId(currentId);
        allmeetings.put(currentId, m);
        currentId++;
    }

    public void AddMeeting(String title, String summary, Date datestart, Date dateend, List<Participant> p, int priority) {
        Meeting meeting = new Meeting(currentId, title, summary, datestart, dateend, p, priority);
        allmeetings.put(currentId, meeting);
        currentId++;

    }

    public void AddParticipantToMeeting(int meetingId, String partName, String partJob) {
        allmeetings.get(meetingId).addParticipant(new Participant(partName, partJob));

    }

    public void DeleteParticipantFromMeeting(int meetingId, String partName, String partJob) {
        allmeetings.get(meetingId).deleteParticipant(new Participant(partName, partJob));
    }

    public String DeleteMeeting(int id) {
        allmeetings.remove(id);
        String s = "Meeting was deleted on server";
        return gson.toJson(s);
    }

    public List<MeetingShortInfo> Search(String s) {
        List<MeetingShortInfo> infos = new ArrayList<MeetingShortInfo>();
        List<Meeting> meetings = new ArrayList<Meeting>(allmeetings.values());
        for (Meeting m : meetings) {
            if ((m.getTitle().contains(s)) || (m.getSummary().contains(s)))
                infos.add(m.toShortInfo());
        }
        return infos;
    }

}
