package common.pojoes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Users2Pojo {
    private Integer id;
    private String avatar;
    @JsonProperty("first_name")
    // <== It links to JSON key. It helps to take any name for Java variable with any mistakes.
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;

    public Users2Pojo(Integer id, String avatar, String firstName, String lastName, String email) {
        this.id = id;
        this.avatar = avatar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Users2Pojo() {
    }

    public Integer getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
