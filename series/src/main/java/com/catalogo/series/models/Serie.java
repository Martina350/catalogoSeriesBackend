package com.catalogo.series.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Serie {
    private Long id;
    private String title;
    private String platform;
    private String qualification;
}
