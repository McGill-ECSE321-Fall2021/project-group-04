package ca.mcgill.ecse321.library.dto;

public abstract class BookingTypeDto {

    private Long id;

    public BookingTypeDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
