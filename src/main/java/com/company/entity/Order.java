package com.company.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(User.Views.UserDetails.class)
    private Long id;

    @NotBlank
    @JsonView(User.Views.UserDetails.class)
    private String product;

    @JsonView(User.Views.UserDetails.class)
    private Double amount;

    @NotBlank
    @JsonView(User.Views.UserDetails.class)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public interface Views {
        interface OrderSummary {}
        interface OrderDetails extends OrderSummary {}
    }
}
