package com.dinemap.dinemap.reservations.entities.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuSummary {
    private String name;
    private double price;
    private String description;
}
