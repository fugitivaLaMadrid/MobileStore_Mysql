package mobileStore.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 25)
    private String code;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at", columnDefinition = "DATE")
    private LocalDate createdAt;

    @Column(name = "disabled")
    private boolean disabled;
}
