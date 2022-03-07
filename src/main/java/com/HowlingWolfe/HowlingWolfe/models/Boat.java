package com.HowlingWolfe.HowlingWolfe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "boat")
@Builder
//@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Boat {

    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private String id;

    @Column(name = "boat")
    @Getter
    @Setter
    private String boat;

    @Column(name = "height")
    @Getter
    @Setter
    private String height;

    @Column(name = "weight")
    @Getter
    @Setter
    private String weight;

    @Column(name = "date")
    @Getter
    @Setter
    private Date date;

    @Column(name = "duration")
    @Getter
    @Setter
    private String duration;

    @Column(name = "time")
    @Getter
    @Setter
    private String time;

    @Column(name = "price")
    @Getter
    @Setter
    private int price;

    @Column(name = "shuttle")
    @Getter
    @Setter
    private String shuttle;

    @Column(name = "discount")
    @Getter
    @Setter
    private Integer discount;

    @Column(name = "giftCard")
    @Getter
    @Setter
    private Integer giftCard;

    @Column(name = "gcDebit")
    @Getter
    @Setter
    private Integer gcDebit;

    @Column(name = "type")
    @Getter
    @Setter
    private String type;

    @Column(name = "comment")
    @Getter
    @Setter
    private String comment;

    @ManyToOne(cascade=CascadeType.ALL)
    @Getter
    @Setter
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @JsonBackReference
    private Order order;

}
