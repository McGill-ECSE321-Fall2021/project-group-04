package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BookingTypes")
public abstract class BookingType {
    private Long id;

    BookingType(Long id) {
        this.id = id;
    }

    public BookingType() {
        super();
    }

    @Id
    Long getId() {
        return this.id;
    }

    void setId(Long id) {
        this.id = id;
    }
}
