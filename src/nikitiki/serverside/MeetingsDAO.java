package nikitiki.serverside;

import model.Meeting;
import model.MeetingShortInfo;
import model.Participant;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MeetingsDAO {
    private static MeetingsDAO instance = new MeetingsDAO();
    private int currentId;
    private HashMap<Integer, Meeting> allmeetings;

    private MeetingsDAO() {
        allmeetings = new HashMap<Integer, Meeting>();
        currentId = 0;
    }

    public static MeetingsDAO getInstance() {
        return instance;
    }

    public List<Meeting> getAllMeetings() {

        return new ArrayList<Meeting>(allmeetings.values());

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
        DateTime date = new DateTime(datelong);
        List<MeetingShortInfo> infos = new ArrayList<MeetingShortInfo>();
        List<Meeting> meetings = new ArrayList<Meeting>(allmeetings.values());
        for (Meeting m : meetings) {
            if (m.getStartdate().toDate() == date.toDate())
                infos.add(m.toShortInfo());
        }
        return infos;
    }

    public Meeting getMeeting(int id) {
        return allmeetings.get(id);
    }

    public void AddMeeting(String title, String summary, DateTime datestart, DateTime dateend, int priority) {
        Meeting meeting = new Meeting(currentId, title, summary, datestart, dateend, null, priority);
        allmeetings.put(currentId, meeting);
        currentId++;

    }

    public void AddMeeting(String title, String summary, DateTime datestart, DateTime dateend, List<Participant> p, int priority) {
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

    public void DeleteMeeting(int id) {
        allmeetings.remove(id);
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
