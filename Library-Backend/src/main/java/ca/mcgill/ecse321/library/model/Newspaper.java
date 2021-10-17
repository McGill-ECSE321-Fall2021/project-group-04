import javax.persistence.Entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Newspaper extends ImmobileItem{
	public Newspaper(String aItemId, Date aDate, int aNumberOfPages)
	  {
	    super(aItemId, aDate, aNumberOfPages);
	  }
	
	
}
