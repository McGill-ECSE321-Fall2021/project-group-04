package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Archive extends ImmobileItem {

    public Archive(Long aItemId, Date aDate, int aNumberOfPages) {
        super(aItemId, aDate, aNumberOfPages);
    }

    public Archive() {
        super();
    }
}
