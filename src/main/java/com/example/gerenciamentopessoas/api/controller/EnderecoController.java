package com.example.gerenciamentopessoas.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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

import com.example.gerenciamentopessoas.domain.exception.EntidadeEmUsoException;
import com.example.gerenciamentopessoas.domain.exception.EntidadeNaoEncontradaException;
import com.example.gerenciamentopessoas.domain.model.Endereco;
import com.example.gerenciamentopessoas.domain.repository.EnderecoRepository;
import com.example.gerenciamentopessoas.domain.service.CadastroEnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CadastroEnderecoService cadastroEnderecoServive;
	
	@GetMapping
	public List<Endereco> listar() {
		return enderecoRepository.findAll();
	}
	
	@GetMapping("/{enderecoId}")
	public ResponseEntity<Endereco> buscar(@PathVariable Long enderecoId) {
		Optional<Endereco> endereco = enderecoRepository.findById(enderecoId);
		
		if (endereco != null) {
			return ResponseEntity.ok(endereco.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Endereco endereco) {
		try {
			endereco = cadastroEnderecoServive.salvar(endereco);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{enderecoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long enderecoId,	@RequestBody Endereco endereco) {
		try {
			Endereco enderecoAtual = enderecoRepository.findById(enderecoId).orElse(null);
			
			if (enderecoAtual != null) {
				BeanUtils.copyProperties(endereco, enderecoAtual, "id");
				
				enderecoAtual = cadastroEnderecoServive.salvar(enderecoAtual);
				return ResponseEntity.ok(enderecoAtual);
			}
			
			return ResponseEntity.notFound().build();
		
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{enderecoId}")
	public ResponseEntity<Endereco> remover(@PathVariable Long enderecoId) {
		try {
			cadastroEnderecoServive.excluir(enderecoId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	

}
