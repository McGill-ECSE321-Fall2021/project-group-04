package ca.mcgill.ecse321.library.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Library {
    private Long id;
    private String name;
    private String address;
    private Set<WorkDay> openingHours;

    public Library(Long aLibraryId, String aName, String aAddress) {
        id = aLibraryId;
        name = aName;
        address = aAddress;
        openingHours = new HashSet<WorkDay>();
    }

    public Library() {
        super();
    }

    @Id
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String value) {
        this.address = value;
    }

    @OneToMany
    public Set<WorkDay> getOpeningHours() {
        return this.openingHours;
    }

    public void setOpeningHours(Set<WorkDay> openingHours) {
        this.openingHours = openingHours;
    }

}
