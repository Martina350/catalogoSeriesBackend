package com.catalogo.series.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalogo.series.models.Serie;

@Repository 
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findById(Long id);
    Optional<Serie> findByTitle(String title);
}
