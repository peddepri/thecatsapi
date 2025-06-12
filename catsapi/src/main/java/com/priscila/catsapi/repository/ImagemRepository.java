package com.priscila.catsapi.repository;

import com.priscila.catsapi.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
}
