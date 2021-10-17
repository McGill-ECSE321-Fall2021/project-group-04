import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "Accounts")
public abstract class User{
private String id;

public User(String aId, String username, String password, String aAddress)
{
  this.username=username;
  this.password=password;
  this.id=aId;
  this.address=aAddress;
}

public User() {
}
   
   public void setId(String value) {
this.id = value;
    }
   
@Id
public String getId() {
return this.id;
    }
private String username;

public void setUsername(String value) {
this.username = value;
    }
public String getUsername() {
return this.username;
    }
private String password;

public void setPassword(String value) {
this.password = value;
    }
public String getPassword() {
return this.password;
    }
private String address;

public void setAddress(String value) {
this.address = value;
    }
public String getAddress() {
return this.address;
       }
   }
