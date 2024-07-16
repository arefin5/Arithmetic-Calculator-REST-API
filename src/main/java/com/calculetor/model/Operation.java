package com.calculetor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    @Id
    @GeneratedValue
    private Integer id;
    private OperationType type;

    @OneToOne
    private Record record;
    @ManyToOne
    private User user;
}
