package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;

@Entity
public class HeadLibrarian extends Librarian {

    public HeadLibrarian(String aUsername, String aPassword, String aAddress) {
        super(aUsername, aPassword, aAddress);
    }

    public HeadLibrarian() {
        super();
    }
}
