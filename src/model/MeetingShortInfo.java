package model;


import java.io.Serializable;
import java.util.Date;


public class MeetingShortInfo implements Serializable {
    private int id;
    private Date startDate;
    private Date endDate;
    private String title;
    private int priority;


    public MeetingShortInfo(Meeting m) {
        this.id = m.getId();
        this.title = m.getTitle();
        this.priority = m.getPriority();
    }

    public MeetingShortInfo(int id, String title, int priority, Date startDate, Date endDate) {
        this.id = id;

        this.title = title;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
