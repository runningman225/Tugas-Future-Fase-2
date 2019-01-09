package com.example.OjekOnline.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "driver",referencedColumnName = "id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "member",referencedColumnName = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "payment",referencedColumnName = "id")
    private Payment payment;

    @Column
    private long bookedDate;

    @Column
    private long pickedUpDate;

    @Column
    private long droppedOffDate;

    @Column
    private long canceledDate;

    @Column
    private long rejectedDate;

    @Column
    private int statusCode;

    @Column
    private String status;

    @Column
    private String note;

    @Column
    private double price;
}
