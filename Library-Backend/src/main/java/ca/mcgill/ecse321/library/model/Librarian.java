package ca.mcgill.ecse321.library.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Librarian extends User {
    private Set<WorkDay> workHours;

    public Librarian(Long aId, String aUsername, String aPassword, String aAddress) {
        super(aId, aUsername, aPassword, aAddress);
        workHours = new HashSet<WorkDay>();
    }

    public Librarian() {
        super();
    }

    @OneToMany
    public Set<WorkDay> getWorkHours() {
        return this.workHours;
    }

    public void setWorkHours(Set<WorkDay> workDays) {
        this.workHours = workDays;
    }

}
