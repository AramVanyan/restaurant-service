package com.epam.kitchenservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket")
@Data
@Builder
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created", columnDefinition="DATETIME",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column
    private Long orderId;

}
