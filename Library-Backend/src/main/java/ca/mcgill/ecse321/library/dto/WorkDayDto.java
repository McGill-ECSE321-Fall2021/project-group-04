package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.WorkDay;
import java.sql.Time;

public class WorkDayDto {

    private WorkDay.DayOfWeek dayOfWeek;
    private Time startTime;
    private Time endTime;
    private Long id;

    public WorkDayDto(Long id, WorkDay.DayOfWeek aDayOfWeek, Time aStartTime, Time aEndTime) {
        this.id = id;
        dayOfWeek = aDayOfWeek;
        startTime = aStartTime;
        endTime = aEndTime;
    }

    public WorkDay.DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Long getId() {
        return id;
    }
}
