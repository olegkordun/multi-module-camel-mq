package me.kordun.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import me.kordun.enums.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;
@JsonRootName(value = "user")
public final class UserJson {
    @JsonProperty(value = "id", required = false)
    protected Long id;

    @JsonProperty(value = "email", required = true)
    @Pattern(regexp = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b", message = "Enter correct email")
    private String email;

    @NotNull(message = "Username must be filled")
    @Size(min = 4, max = 255, message = "Username must be filled, from 5 to 255 symbols")
    @JsonProperty(value = "userName", required = true)
    private String userName;

    @NotNull(message = "First Name must be filled")
    @Size(min = 1, max = 255, message = "First Name must be filled, from 5 to 255 symbols")
    @JsonProperty(value = "firstName", required = true)
    private String firstName;

    @NotNull(message = "Last Name must be filled")
    @Size(min = 1, max = 255, message = "Last Name must be filled, from 5 to 255 symbols")
    @JsonProperty(value = "lastName", required = true)
    private String lastName;

    @JsonProperty(value = "comment", required = false)
    private String comment;

    @JsonProperty(value = "roles", required = true)
    private Set<Role> roles;

    @JsonProperty(value = "companyId", required = true)
    private Long companyId;
    @JsonProperty(value = "passwordDigest", required = false)
    private String passwordDigest;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest;
    }

    @JsonIgnore
    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
