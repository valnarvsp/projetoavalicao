package com.example.gerenciamentopessoas.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.gerenciamentopessoas.domain.exception.EntidadeNaoEncontradaException;
import com.example.gerenciamentopessoas.domain.exception.NegocioException;
import com.example.gerenciamentopessoas.domain.exception.PessoaNaoEncontradaException;
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
	//@ResponseStatus(HttpStatus.CREATED) - Fazendo os Testes de API
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{pessoaId}")
	public Pessoa buscar(@PathVariable Long pessoaId) {
		return cadastroPessoaService.buscarOuFalhar(pessoaId);
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@RequestBody Pessoa pessoa) {
		return cadastroPessoaService.salvar(pessoa);
	}

	@PutMapping("/{pessoaId}")
	public Pessoa atualizar(@PathVariable Long pessoaId, @RequestBody Pessoa pessoa) {
		Pessoa pessoaAtual = cadastroPessoaService.buscarOuFalhar(pessoaId);

		BeanUtils.copyProperties(pessoa, pessoaAtual, "id");

		try {
			return cadastroPessoaService.salvar(pessoaAtual);

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@DeleteMapping("/{pessoaId}")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long pessoaId) {
		cadastroPessoaService.excluir(pessoaId);
	}
	
	@ExceptionHandler(PessoaNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(NegocioException e) {	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException e) {	
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
}
