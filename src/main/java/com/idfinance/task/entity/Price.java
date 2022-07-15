package com.idfinance.task.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prices")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private BigDecimal price;
    private LocalDateTime updated;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
    @OneToMany(
            mappedBy = "price",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<User> userList = new ArrayList<>();
}
