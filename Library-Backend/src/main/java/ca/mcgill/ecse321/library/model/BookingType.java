import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public abstract class BookingType{
	String bTypeid;
	
	BookingType(String id){
		this.bTypeid = id;
	}
	
	@Id
	String getId() {
		return this.bTypeid;
	}
	
	void setId(String id) {
		this.bTypeid = id;
	}
}
