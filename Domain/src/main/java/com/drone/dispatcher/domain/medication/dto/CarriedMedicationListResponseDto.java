package com.drone.dispatcher.domain.medication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarriedMedicationListResponseDto {
    private List<CarriedMedicationDto> results;
}
