import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.*;

@Entity
public abstract class Library{
private String libraryId;

public Library(String aLibraryId, String aName, String aAddress)
{
  name = aName;
  address = aAddress;
  openingHours = new HashSet<WorkDay>();
}
   
   public void setLibraryId(String value) {
this.libraryId = value;
   }
   
@Id
public String getLibraryId() {
return this.libraryId;
    }

private String name;

public void setName(String value) {
this.name = value;
    }
public String getName() {
return this.name;
    }
private String address;

public void setAddress(String value) {
this.address = value;
    }
public String getAddress() {
return this.address;
    }
private Set<WorkDay> openingHours;

@OneToMany
public Set<WorkDay> getOpeningHours() {
   return this.openingHours;
}

public void setOpeningHours(Set<WorkDay> openingHourss) {
   this.openingHours = openingHourss;
}

}
