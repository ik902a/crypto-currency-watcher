package com.idfinance.task.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    Long id;
    String symbol;
//    @OneToMany(
//            fetch = FetchType.EAGER,
//            mappedBy = "registryId",
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<Value> valueList = new ArrayList<>();
}
