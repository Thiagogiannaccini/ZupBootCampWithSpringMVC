package com.zupbootcamp.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zupbootcamp.teste.model.Fotos;

public interface FotoRepository extends JpaRepository<Fotos, Long> {

	Fotos findByCodigo(Long codigo);

}
