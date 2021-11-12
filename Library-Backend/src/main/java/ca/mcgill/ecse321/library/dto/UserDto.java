package ca.mcgill.ecse321.library.dto;

public class UserDto {

    private final Long id;
    private final String username;
    private final String password;
    private final String address;

    public UserDto(Long aId, String username, String password, String aAddress) {
        this.username = username;
        this.password = password;
        this.id = aId;
        this.address = aAddress;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }
}
