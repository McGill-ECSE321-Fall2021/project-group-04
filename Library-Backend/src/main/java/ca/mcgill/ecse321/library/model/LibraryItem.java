package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "LibraryItems")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class LibraryItem {
    private String title;
    private Long id;

    public LibraryItem(Long aItemId, String aTitle) {
        id = aItemId;
        title = aTitle;
    }
    public LibraryItem() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    public void setId(Long value) {
        this.id = value;
    }
}
