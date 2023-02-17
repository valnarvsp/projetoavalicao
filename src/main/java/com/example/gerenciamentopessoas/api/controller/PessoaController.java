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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.gerenciamentopessoas.domain.exception.EntidadeEmUsoException;
import com.example.gerenciamentopessoas.domain.exception.EntidadeNaoEncontradaException;
import com.example.gerenciamentopessoas.domain.model.Pessoa;
import com.example.gerenciamentopessoas.domain.repository.PessoaRepository;
import com.example.gerenciamentopessoas.domain.service.CadastroPessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	@GetMapping()
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	@GetMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> buscar(@PathVariable Long pessoaId) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);
		
		if (pessoa != null) {
			return ResponseEntity.ok(pessoa.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@RequestBody Pessoa pessoa) {
		return cadastroPessoaService.salvar(pessoa);
	}
	
	@PutMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long pessoaId, @RequestBody Pessoa pessoa) {
		Pessoa pessoaAtual = pessoaRepository.findById(pessoaId).orElse(null);
		
		if (pessoaAtual != null) {
			BeanUtils.copyProperties(pessoa, pessoaAtual, "id");
			
			pessoaAtual = cadastroPessoaService.salvar(pessoaAtual);
			return ResponseEntity.ok(pessoaAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{pessoaId}")
	public ResponseEntity<?> remover(@PathVariable Long pessoaId) {
		try {
			cadastroPessoaService.excluir(pessoaId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

}
