package model;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Meeting {
    public final static int PRIORITY_URGENT = 1;
    public final static int PRIORITY_PLANNED = 2;
    public final static int PRIORITY_IF_POSSIBLE = 3;
    private int id;
    private String title;
    private String summary;
    private DateTime startdate;
    private DateTime enddate;
    private List<Participant> participants;
    private int priority;

    public Meeting(int id, String title, String summary, DateTime startdate, DateTime enddate, List<Participant> participants, int priority) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.startdate = startdate;
        this.enddate = enddate;
        if (participants == null) {
            this.participants = new ArrayList<Participant>();
        } else {
            this.participants = participants;
        }
        this.priority = priority;
    }

    public MeetingShortInfo toShortInfo() {
        return new MeetingShortInfo(id, title, priority, startdate, enddate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addParticipant(Participant participant) {
        if (!participants.contains(participant))
            participants.add(participant);
    }

    public void deleteParticipant(Participant participant) {
        if (participants.contains(participant))
            participants.remove(participant);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public DateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(DateTime startdate) {
        this.startdate = startdate;
    }

    public DateTime getEnddate() {
        return enddate;
    }

    public void setEnddate(DateTime enddate) {
        this.enddate = enddate;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}