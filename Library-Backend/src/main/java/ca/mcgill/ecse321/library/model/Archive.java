package ca.mcgill.ecse321.library.model;

import java.sql.Date;
import javax.persistence.Entity;

@Entity
public class Archive extends ImmobileItem {

    public Archive(Long aItemId, Date aDate, int aNumberOfPages) {
        super(aItemId, aDate, aNumberOfPages);
    }

    public Archive() {
        super();
    }
}
