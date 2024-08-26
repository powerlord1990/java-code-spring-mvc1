package com.company.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserSummary.class)
    private Long id;

    @NotBlank
    @JsonView(Views.UserSummary.class)
    private String name;

    @NotBlank
    @Email
    @JsonView(Views.UserSummary.class)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonView(Views.UserDetails.class)
    private List<Order> orders;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public interface Views {

        interface UserSummary {
        }
        interface UserDetails extends UserSummary {

        }
    }


}