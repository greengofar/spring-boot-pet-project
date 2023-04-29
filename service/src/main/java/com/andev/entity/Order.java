package com.andev.entity;

import com.andev.entity.enums.Payment;
import com.andev.entity.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user", "products"})
@EqualsAndHashCode(exclude = {"user", "products"})
@Builder
@Entity
@Table(name = "orders")
public class Order implements BaseEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dateOrder;
    private LocalDate dateClosing;
    private Integer totalValue;
    @Enumerated(EnumType.STRING)
    private Payment payment;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Embedded
    private UserAddress userAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    @ManyToMany(mappedBy = "orders")
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        product.getOrders().add(this);
    }
}
