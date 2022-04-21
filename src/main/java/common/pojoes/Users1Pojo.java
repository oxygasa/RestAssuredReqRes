package common.pojoes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) //I need to deserialize only id and email. I don't want an Exception for other keys.
public class Users1Pojo {
    private Integer id;
    @JsonProperty("email")
    private String email;

    public Users1Pojo(Integer id, String email) {
        this.id = id;
        this.email = email;
    }
    public Users1Pojo(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
