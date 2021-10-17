package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public abstract class User {
    private Long id;
    private String username;
    private String password;
    private String address;

    public User(Long aId, String username, String password, String aAddress) {
        this.username = username;
        this.password = password;
        this.id = aId;
        this.address = aAddress;
    }

    public User() {
        super();
    }

    @Id
    public Long getId() {
        return this.id;
    }

    public void setId(Long aId) {
        this.id = aId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String value) {
        this.address = value;
    }
}
