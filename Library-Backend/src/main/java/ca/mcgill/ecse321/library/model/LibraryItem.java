import javax.persistence.Entity;
import javax.persistence.*;
@Entity
public abstract class LibraryItem{
private String itemId;

public LibraryItem(String aItemId)
{
  itemId = aItemId;
}
   
   public void setItemId(String value) {
this.itemId = value;
    }
   
@Id
public String getItemId() {
return this.itemId;
       }
   }
