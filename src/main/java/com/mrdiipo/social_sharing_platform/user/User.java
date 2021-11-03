package com.mrdiipo.social_sharing_platform.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username")) //This constraint is important when multiple applications are accessing the same database
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "{Teil.constraints.username.NotNull.message}")
    @Size(min = 4, max = 255)
    @UniqueUsername
    private String username;

    @NotNull(message = "{Teil.constraints.username.NotNull.message.displayName}")
    @Size(min = 4, max = 255)
    private String displayName;

    @NotNull(message = "{Teil.constraints.username.NotNull.message.password}")
    @Size(min = 8, max = 255)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[^\\s]{8,}$", message = "{Teil.constraints.password.Pattern.message}")
    private String password;

}
