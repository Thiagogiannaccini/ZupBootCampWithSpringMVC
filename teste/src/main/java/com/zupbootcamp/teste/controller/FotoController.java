package com.zupbootcamp.teste.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zupbootcamp.teste.model.Fotos;
import com.zupbootcamp.teste.model.Usuario;
import com.zupbootcamp.teste.repository.FotoRepository;
import com.zupbootcamp.teste.repository.UsuarioRepository;

@RestController
@RequestMapping("/fotos")
public class FotoController {

	@Autowired
	FotoRepository fotoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	// Metodo listar fotos. Buscar todas as fotos no repositorio.
	@GetMapping
	public List<Fotos> buscarFotos() {
		return fotoRepository.findAll();
	}

	// Metodo buscar foto pelo codigo. Recebe um PathVariable com o codigo e busca a
	// foto a qual este codigo pertence no repositorio.
	@GetMapping("/{codigo}")
	public ResponseEntity<Fotos> buscar(@PathVariable Long codigo) {
		Fotos foto = fotoRepository.findByCodigo(codigo);

		if (foto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(foto);
	}

	// Metodo de publicas foto. Recebe o arquivo, a legenda(opicional) e o codigo do
	// Usuario à quem a foto pertence.
	@PostMapping
	public @ResponseBody ResponseEntity<Fotos> upload(@RequestParam MultipartFile fotos, String legenda, Long codigo)
			throws IOException {
		Fotos pics = new Fotos();
		pics.setFotos(fotos.getBytes());
		pics.setLegenda(legenda);
		Usuario user = usuarioRepository.findByCodigo(codigo);
		pics.setUsuario(user);
		fotoRepository.save(pics);
		return ResponseEntity.ok().build();
	}

	// Metodo de ediçao de legenda. Recebe o codigo da foto e a legenda que deseja
	// colocar.
	@PutMapping("/{codigo}")
	public ResponseEntity<Fotos> atualizar(@PathVariable Long codigo, @RequestBody Fotos novaLegenda) {
		Fotos legenda = fotoRepository.findByCodigo(codigo);

		legenda.setLegenda(novaLegenda.getLegenda());

		return new ResponseEntity<>(fotoRepository.save(legenda), HttpStatus.OK);
	}

	@DeleteMapping("/{codigo}")
	public void deletarFoto(@PathVariable long codigo) {
		Fotos fotos = fotoRepository.findByCodigo(codigo);
		fotoRepository.delete(fotos);
	}
}