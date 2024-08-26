package com.company.entity;

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

    public interface Views {
        interface OrderSummary {}
        interface OrderDetails extends OrderSummary {}
    }

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
    private User user;
}
