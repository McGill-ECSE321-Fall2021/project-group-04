package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;

@Entity
public class HeadLibrarian extends Librarian {

    public HeadLibrarian(Long aId, String aUsername, String aPassword, String aAddress) {
        super(aId, aUsername, aPassword, aAddress);
    }

    public HeadLibrarian() {
        super();
    }
}
