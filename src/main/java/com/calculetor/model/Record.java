package com.calculetor.model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Record {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer balance=6000;
    @OneToOne
   private Operation operation;
    @ManyToOne
    private User user;
    private Date createdAt;
    private Integer operationAmount;

    @PostConstruct
    private void createAt(Date date){
        this.createdAt = new Date(System.currentTimeMillis());
    }
}
