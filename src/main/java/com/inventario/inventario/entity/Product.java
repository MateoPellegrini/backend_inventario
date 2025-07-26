package com.inventario.inventario.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long productId;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    private Long initialStock;
    private Long currentStock;
    private Long price;
}
