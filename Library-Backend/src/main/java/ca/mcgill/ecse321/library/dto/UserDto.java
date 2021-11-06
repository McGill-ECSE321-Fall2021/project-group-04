package ca.mcgill.ecse321.library.dto;

public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String address;

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
