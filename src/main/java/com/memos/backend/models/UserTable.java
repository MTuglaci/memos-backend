package com.memos.backend.models;

import com.memos.backend.annotations.UniqueEmail;
import com.memos.backend.annotations.UniqueUsername;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
public class UserTable {

    @Id
    @NotBlank(message = "{memos.validation.constraints.username.NotBlank.message}")
    @Size(max = 255)
    @UniqueUsername
    private String username;

    @NotBlank(message = "{memos.validation.constraints.firstName.NotBlank.message}")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "{memos.validation.constraints.lastName.NotBlank.message}")
    @Size(max = 255)
    private String surname;

    @NotBlank(message = "{memos.validation.constraints.email.NotBlank.message}")
    @Size(max = 255)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{memos.validation.constraints.email.Pattern.message}")
    @UniqueEmail
    private String email;

    @NotNull(message = "{memos.validation.constraints.password.NotNull.message}")
    @Size(min= 8, max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{memos.validation.constraints.password.Pattern.message}")
    private String password;

}
