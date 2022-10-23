package com.drone.dispatcher.domain.drone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneListResponseDto {
    private List<DroneDto> results;
}
