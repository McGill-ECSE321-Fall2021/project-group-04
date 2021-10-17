import javax.persistence.Entity;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Librarian extends User{
private Set<WorkDay> workHours;

public Librarian(String aId, String aUsername, String aPassword, String aAddress)
{
  super(aId, aUsername, aPassword, aAddress);
  workHours = new HashSet<WorkDay>();
}


@OneToMany
public Set<WorkDay> getWorkHours() {
   return this.workHours;
}

public void setWorkHours(Set<WorkDay> workDays) {
   this.workHours = workDays;
}

}
