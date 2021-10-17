package ca.mcgill.ecse321.library.model;

import java.sql.Date;
import javax.persistence.Entity;

@Entity
public abstract class ImmobileItem extends LibraryItem {
    private Date date;
    private int numberOfPages;

    public ImmobileItem(Long aItemId, String aTitle, Date aDate, int aNumberOfPages) {
        super(aItemId, aTitle);
        date = aDate;
        numberOfPages = aNumberOfPages;
    }

    public ImmobileItem() {
        super();
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date value) {
        this.date = value;
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    public void setNumberOfPages(int value) {
        this.numberOfPages = value;
    }
}
