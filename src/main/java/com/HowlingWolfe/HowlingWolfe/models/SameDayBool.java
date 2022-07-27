package com.HowlingWolfe.HowlingWolfe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "sameday")
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SameDayBool {

    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private String id;

    @Column(name = "sameday")
    @Getter
    @Setter
    private boolean sameDay;

    @Column(name = "type")
    @Getter
    @Setter
    private String type;
}
