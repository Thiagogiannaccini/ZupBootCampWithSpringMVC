package com.zupbootcamp.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zupbootcamp.teste.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByCodigo(Long codigo);

	List<Usuario> findAll();

}
