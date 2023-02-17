package com.example.gerenciamentopessoas.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.gerenciamentopessoas.domain.model.Endereco;
import com.example.gerenciamentopessoas.domain.model.Pessoa;
import com.example.gerenciamentopessoas.domain.repository.EnderecoRepository;
import com.example.gerenciamentopessoas.domain.service.CadastroEnderecoService;
import com.example.gerenciamentopessoas.domain.service.CadastroPessoaService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CadastroEnderecoService cadastroEnderecoServive;

	@Autowired
	private CadastroPessoaService cadastroPessoaService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Endereco> listar() {
		return enderecoRepository.findAll();
	}

	@GetMapping("/{enderecoId}")
	public Endereco buscar(@PathVariable Long enderecoId) {
		return cadastroEnderecoServive.buscarOuFalhar(enderecoId);
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco adicionar(@RequestBody Endereco endereco) {
		Long pessoaId = endereco.getPessoa().getId();
		Pessoa pessoa = cadastroPessoaService.buscarOuFalhar(pessoaId);
		endereco.setPessoa(pessoa);
		return enderecoRepository.save(endereco);

	}

	@PutMapping("/{enderecoId}")
	public Endereco atualizar(@PathVariable Long enderecoId, @RequestBody Endereco endereco) {
		Endereco enderecoAtual = cadastroEnderecoServive.buscarOuFalhar(enderecoId);
		BeanUtils.copyProperties(endereco, enderecoAtual, "id");
		return cadastroEnderecoServive.salvar(enderecoAtual);

	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long enderecoId) {
		cadastroEnderecoServive.excluir(enderecoId);

	}

}
