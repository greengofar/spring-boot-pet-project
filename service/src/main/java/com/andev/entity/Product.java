package com.andev.entity;

import com.andev.entity.enums.Category;
import lombok.*;

import javax.persistence.*;
import java.math.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orders", "manufacturer"})
@EqualsAndHashCode(exclude = {"orders", "manufacturer"})
@Builder
@Entity
public class Product implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String model;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String description;
    private BigDecimal price;
    private Integer amount;
    private String imageName;

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    public void addOrder(Order order) {
        orders.add(order);
        order.setProduct(this);
    }
}
