package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Newspaper extends ImmobileItem {
    public Newspaper(Long aItemId, Date aDate, int aNumberOfPages) {
        super(aItemId, aDate, aNumberOfPages);
    }

    public Newspaper() {
        super();
    }
}
