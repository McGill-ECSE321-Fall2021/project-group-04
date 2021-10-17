package ca.mcgill.ecse321.library.model;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WorkDays")
public class WorkDay {

    public enum DayOfWeek {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }

    private DayOfWeek dayOfWeek;
    private Time startTime;
    private Time endTime;
    private Long id;

    public WorkDay(Long id, DayOfWeek aDayOfWeek, Time aStartTime, Time aEndTime) {
        this.id = id;
        dayOfWeek = aDayOfWeek;
        startTime = aStartTime;
        endTime = aEndTime;
    }

    public WorkDay() {
        super();
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek value) {
        this.dayOfWeek = value;
    }

    public Time getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Time value) {
        this.startTime = value;
    }

    public Time getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Time value) {
        this.endTime = value;
    }

    @Id
    public Long getId() {
        return this.id;
    }

    public void setId(Long aId) {
        this.id = aId;
    }
}
