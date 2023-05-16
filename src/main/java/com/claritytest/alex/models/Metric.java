package com.claritytest.alex.models;

import lombok.*;

import javax.persistence.*;

//Little overkill here where @data would surfice but this keeps thing imutable while giving builder annotation
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "METRICS")
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;

   @Column
    private String system;

    @Column
    private String name;

    @Column
    private Integer date;

    @Column
    private Integer value;
}

