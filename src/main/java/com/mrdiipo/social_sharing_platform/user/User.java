package com.mrdiipo.social_sharing_platform.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.beans.Transient;
import java.util.Collection;

@Data
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username")) //This constraint is important when multiple applications are accessing the same database
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @JsonView(Views.Base.class)
    private Long id;

    @NotNull(message = "{SHAREMOBILE.constraints.username.NotNull.message}")
    @Size(min = 4, max = 255)
    @UniqueUsername
    @JsonView(Views.Base.class)
    private String username;

    @NotNull(message = "{SHAREMOBILE.constraints.username.NotNull.message.displayName}")
    @Size(min = 4, max = 255)
    @JsonView(Views.Base.class)
    private String displayName;

    @NotNull(message = "{SHAREMOBILE.constraints.username.NotNull.message.password}")
    @Size(min = 8, max = 255)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[^\\s]{8,}$", message = "{SHAREMOBILE.constraints.password.Pattern.message}")
    private String password;

    @JsonView(Views.Base.class)
    private String image;

    @Override
    @java.beans.Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_USER");
    }

    @Override
    @java.beans.Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @java.beans.Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
