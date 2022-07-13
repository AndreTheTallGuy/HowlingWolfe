package com.HowlingWolfe.HowlingWolfe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "availability")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Availability {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "tripType")
    private String tripType;

    @Column(name = "subType")
    private String subType;

    @ElementCollection
    @Column(name = "dates")
    private List<LocalDate> dates = new ArrayList<>();
}
