package com.zupbootcamp.teste.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.zupbootcamp.teste.model.Usuario;
import com.zupbootcamp.teste.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	//Metodo de cadastro de usuario. Recebe os dados do usuario e salva no repositorio.
	@PostMapping
	public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	//Metodo de listar usuario. Busca todos os usuario que contem no repositorio.
	@GetMapping
	public List<Usuario> buscarUsuario() {
		return usuarioRepository.findAll();
	}

	//Metodo de buscar usuario. Recebe um codigo e através dele busca o usuario a qual o codigo pertence no repositorio.
	@GetMapping("/{codigo}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long codigo) {
		Usuario usuario = usuarioRepository.findByCodigo(codigo);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);
	}

	//Metodo de atualizar cadastro do usuario. Recebe o codigo e novos parametros, busca o usuario no repositorio atraves do codigo, e utiliza os novos parametros
	//para setar dentro do usuario que foi encontrado.
	@PutMapping("/{codigo}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @RequestBody Usuario novoUsuario) {
		Usuario usuario = usuarioRepository.findByCodigo(codigo);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		usuario.setNomeUsuario(novoUsuario.getNomeUsuario());
		usuario.setSenha(novoUsuario.getSenha());

		return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
	}
	
	//Metodo para deletar cadastro do usuario. Recebe um codigo para buscar o usuario no repositorio e deletar o usuario que foi encontrado.
	@DeleteMapping("/{codigo}")
	public void deletarUsuario(@PathVariable long codigo) {
		Usuario usuario = usuarioRepository.findByCodigo(codigo);
		usuarioRepository.delete(usuario);
	}


}
