package com.drone.dispatcher.domain.medication.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("medications")
public class Medication {

    @Id
    private Long id;
    private String uuid;
    private String name;
    private String code;
    private int weight;
    private String imageUuid;
}
