import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class WorkDay{
private DayOfWeek dayOfWeek;

public WorkDay(DayOfWeek aDayOfWeek, Time aStartTime, Time aEndTime, String id)
{
  dayOfWeek = aDayOfWeek;
  startTime = aStartTime;
  endTime = aEndTime;
  this.wDayid = id;
  
}
   
   public void setDayOfWeek(DayOfWeek value) {
this.dayOfWeek = value;
    }
public DayOfWeek getDayOfWeek() {
return this.dayOfWeek;
    }
private Time startTime;

public void setStartTime(Time value) {
this.startTime = value;
    }
public Time getStartTime() {
return this.startTime;
    }
private Time endTime;

public void setEndTime(Time value) {
this.endTime = value;
    }
public Time getEndTime() {
return this.endTime;
    }
private String wDayid;

public void setwDayId(String value) {
this.wDayid = value;
    }

@Id
public String getwDayId() {
return this.wDayid;
       }
   }
