package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
@Table(name = "BookingTypes")
public abstract class BookingType {
    private Long id;

    public BookingType(Long id) {
        this.id = id;
    }

    public BookingType() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
