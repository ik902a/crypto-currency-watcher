package com.idfinance.task.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "currencies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Currency {
    @Id
    @EqualsAndHashCode.Include
    private Long id;
    private String symbol;
    @OneToMany(
            mappedBy = "currency",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Price> priceList = new ArrayList<>();
}
