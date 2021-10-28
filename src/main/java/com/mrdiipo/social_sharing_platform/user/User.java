package com.mrdiipo.social_sharing_platform.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Generated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;

    private String displayName;

    private String password;

}
