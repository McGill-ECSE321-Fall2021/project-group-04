package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class LibraryItem {
    private Long id;

    public LibraryItem(Long aItemId) {
        id = aItemId;
    }

    public LibraryItem() {
        super();
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
