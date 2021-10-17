import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class HeadLibrarian extends Librarian{
	
	public HeadLibrarian(String aId, String aUsername, String aPassword, String aAddress)
	  {
	    super(aId, aUsername, aPassword, aAddress);
	  }
}
