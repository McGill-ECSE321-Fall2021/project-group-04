import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Date;

@Entity
public abstract class ImmobileItem extends LibraryItem{
private Date date;


public ImmobileItem(String aItemId, Date aDate, int aNumberOfPages)
{
  super(aItemId);
  date = aDate;
  numberOfPages = aNumberOfPages;
}
   
   public void setDate(Date value) {
this.date = value;
    }
public Date getDate() {
return this.date;
    }
private int numberOfPages;

public void setNumberOfPages(int value) {
this.numberOfPages = value;
    }
public int getNumberOfPages() {
return this.numberOfPages;
       }
   }
