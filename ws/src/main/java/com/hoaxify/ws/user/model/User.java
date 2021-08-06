package com.hoaxify.ws.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String displayName;
    private String password;

}
