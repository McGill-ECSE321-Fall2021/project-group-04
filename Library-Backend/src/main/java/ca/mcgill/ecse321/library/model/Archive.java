package ca.mcgill.ecse321.library.model;

import java.sql.Date;
import javax.persistence.Entity;

@Entity
public class Archive extends ImmobileItem {

    public Archive(Long aItemId, String aTitle, Date aDate, int aNumberOfPages) {
        super(aItemId, aTitle, aDate, aNumberOfPages);
    }

    public Archive() {
        super();
    }
}
