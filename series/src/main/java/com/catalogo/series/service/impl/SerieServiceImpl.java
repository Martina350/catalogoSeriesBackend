package com.catalogo.series.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.catalogo.series.models.Serie;
import com.catalogo.series.repository.SerieRepository;
import com.catalogo.series.service.SerieService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service 
@RequiredArgsConstructor 
public class SerieServiceImpl implements SerieService {

    private final SerieRepository serieRepository;

    @Override
    public Serie registerSerie(Serie serie) {
        return serieRepository.save(serie);
    }

    @Override 
    public List<Serie> getAllSeries() {
        return serieRepository.findAll();
    }

    @Override
    public Optional<Serie> getSerieById(Long id) {
        return serieRepository.findById(id);
    }

    @Override
    @SneakyThrows 
    public Serie updateSerie(Long id, Serie serie) {
        Optional<Serie> existingSerie = serieRepository.findById(id);
        if (existingSerie.isPresent()) {
            Serie updatedSerie = existingSerie.get();
            updatedSerie.setTitle(serie.getTitle());
            updatedSerie.setPlatform(serie.getPlatform());
            updatedSerie.setQualification(serie.getQualification());
            return serieRepository.save(updatedSerie);
        } else {
            throw new Exception("Serie not found with id:" + id); 
        }
    }

    @Override
    public void deleteSerie(Long id) {
        serieRepository.deleteById(id);
    }

}
