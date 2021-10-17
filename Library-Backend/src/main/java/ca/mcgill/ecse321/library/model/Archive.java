import java.sql.Date;
import javax.persistence.*;

import javax.persistence.Entity;

@Entity
public class Archive extends ImmobileItem{
	
	public Archive(String aItemId, Date aDate, int aNumberOfPages)
	  {
	    super(aItemId, aDate, aNumberOfPages);
	  }
}
