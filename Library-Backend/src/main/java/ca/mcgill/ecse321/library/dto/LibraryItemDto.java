package ca.mcgill.ecse321.library.dto;

public abstract class LibraryItemDto {
    private final String title;
    private final Long id;

    public LibraryItemDto(Long aItemId, String aTitle) {
        id = aItemId;
        title = aTitle;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
}
