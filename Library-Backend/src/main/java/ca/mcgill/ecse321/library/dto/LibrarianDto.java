package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.WorkDay;

import java.util.HashSet;
import java.util.Set;

public class LibrarianDto extends UserDto {

    private Set<WorkDayDto> workHours;

    public LibrarianDto(Long aId, String aUsername, String aPassword, String aAddress) {
        super(aId, aUsername, aPassword, aAddress);
        workHours = new HashSet<WorkDayDto>();
    }

    public Set<WorkDayDto> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Set<WorkDayDto> workDays) {
        this.workHours = workDays;
    }
}
