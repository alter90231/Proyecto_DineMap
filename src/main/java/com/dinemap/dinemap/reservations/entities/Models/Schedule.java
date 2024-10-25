package com.dinemap.dinemap.reservations.entities.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private String day;
    private String opens;
    private String closes;
}
