package com.memos.backend.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.memos.backend.annotations.UniqueEmail;
import com.memos.backend.annotations.UniqueUsername;
import com.memos.backend.shared.Views;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name="user", schema = "public")
@Data
public class User implements UserDetails {

    private static final long serialVersionUID = 3614515627868980364L;

    @Id
    @NotBlank(message = "{memos.validation.constraints.username.NotBlank.message}")
    @Size(max = 255)
    @UniqueUsername
    @JsonView(Views.Base.class)
    private String username;

    @NotBlank(message = "{memos.validation.constraints.firstName.NotBlank.message}")
    @Size(max = 255)
    @JsonView(Views.Base.class)
    private String name;

    @NotBlank(message = "{memos.validation.constraints.lastName.NotBlank.message}")
    @Size(max = 255)
    @JsonView(Views.Base.class)
    private String surname;

    @NotBlank(message = "{memos.validation.constraints.email.NotBlank.message}")
    @Size(max = 255)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{memos.validation.constraints.email.Pattern.message}")
    @UniqueEmail
    @JsonView(Views.Base.class)
    private String email;

    @NotNull(message = "{memos.validation.constraints.password.NotNull.message}")
    @Size(min= 8, max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{memos.validation.constraints.password.Pattern.message}")
    @JsonView(Views.Sensitive.class)
    private String password;

    @JsonView(Views.Base.class)
    private String image;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_user");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
