package com.jpeedroza.reservroom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rooms")
@SequenceGenerator(name = "startCountingWith101", initialValue = 101)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "startCountingWith101")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, length = 50)
    private String location;
    @Column(nullable = false, length = 10)
    private LocalDateTime timePosted;
    @Column(nullable = false, length = 10)
    private BigDecimal valuePerDay;
    @Column(nullable = false, length = 20)
    private String userName;
    @Column(columnDefinition = "boolean default true")
    private Boolean activated;
}
