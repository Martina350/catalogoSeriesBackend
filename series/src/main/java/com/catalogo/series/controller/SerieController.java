package com.catalogo.series.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.series.models.Serie;
import com.catalogo.series.service.SerieService;

import lombok.RequiredArgsConstructor;



@RestController 
@RequestMapping ("/api/series")
@RequiredArgsConstructor 
public class SerieController {
    private final SerieService serieService;

    @PostMapping("/register")
    public ResponseEntity<?> registerSerie(@RequestBody Serie serie) {
        Serie registeredSerie = serieService.registerSerie(serie);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredSerie);
    }
    
    @GetMapping
    public ResponseEntity<List<Serie>> getAllSeries() {
        List<Serie> series = serieService.getAllSeries();
        return ResponseEntity.ok(series);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> getSerieById(@PathVariable Long id) {
        Optional<Serie> serie = serieService.getSerieById(id);
        return serie.isPresent() ? ResponseEntity.ok(serie.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serie not found with id: " + id);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<?> updateSerie(@PathVariable Long id, @RequestBody Serie serie) {
        try {
            Serie updatedSerie = new Serie();
            updatedSerie.setTitle(serie.getTitle());
            updatedSerie.setPlatform(serie.getPlatform());
            updatedSerie.setQualification(serie.getQualification());
            Serie serieBDD = serieService.updateSerie(id, updatedSerie);
            return ResponseEntity.ok(serieBDD);            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSerie(@PathVariable Long id) {
        serieService.deleteSerie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}