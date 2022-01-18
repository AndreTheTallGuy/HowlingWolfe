package com.HowlingWolfe.HowlingWolfe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "boat")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Boat {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "boat")
    private String boat;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "date")
    private Date date;

    @Column(name = "duration")
    private String duration;

    @Column(name = "time")
    private String time;

    @Column(name = "price")
    private int price;

    @Column(name = "shuttle")
    private String shuttle;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "giftCard")
    private Integer giftCard;

    @Column(name = "gcDebit")
    private Integer gcDebit;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @JsonBackReference
    private Order order;

}
