package com.catalogo.series.service;

import java.util.List;
import java.util.Optional;

import com.catalogo.series.models.Serie;

public interface SerieService {
    Serie registerSerie(Serie serie);
    List<Serie> getAllSeries();
    Optional<Serie> getSerieById(Long id);
    Serie updateSerie(Long id, Serie serie);
    void deleteSerie(Long id);
}
