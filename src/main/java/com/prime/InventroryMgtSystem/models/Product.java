package com.prime.InventroryMgtSystem.models;

import com.prime.InventroryMgtSystem.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "SKU is required")
    private String sku;

    @Positive(message = "ProductDTO price must be a positive value")
    private BigDecimal price;

    @Min(value =0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    private String description;
    private LocalDateTime expiryDate;
    private  String imageurl;


    @Column(name = "created_at")
    private final LocalDateTime createdAt= LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public String toString() {
        return "ProductService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", description='" + description + '\'' +
                ", expiryDate=" + expiryDate +
                ", imageurl='" + imageurl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
