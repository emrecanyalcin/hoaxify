package com.hoaxify.ws.user.model;


import com.hoaxify.ws.user.annotation.UniqueUsername;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 4,max = 255)
    @UniqueUsername
    private String username;

    @NotNull
    @Size(min = 4,max = 255)
    private String displayName;

    @NotNull
    @Size(min = 8,max = 255)
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;

}
