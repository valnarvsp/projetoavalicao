package com.example.gerenciamentopessoas.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.gerenciamentopessoas.domain.exception.EnderecoNaoEncontradoException;
import com.example.gerenciamentopessoas.domain.exception.EntidadeEmUsoException;
import com.example.gerenciamentopessoas.domain.model.Endereco;
import com.example.gerenciamentopessoas.domain.model.Pessoa;
import com.example.gerenciamentopessoas.domain.repository.EnderecoRepository;

@Service
public class CadastroEnderecoService {

	private static final String MSG_ENDERECO_COM_PESSOA = 
			"Enderecço de código %d não pode ser removida, pois possui um endereço cadastrado";


	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	public Endereco salvarTest(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public Endereco salvar(Endereco endereco) {
		Long pessoaId = endereco.getPessoa().getId();
		Pessoa pessoa = cadastroPessoaService.buscarOuFalhar(pessoaId);
		endereco.setPessoa(pessoa);
		
		return enderecoRepository.save(endereco);
	}

	public void excluir(Long enderecoId) {
		try {
			enderecoRepository.deleteById(enderecoId);

		} catch (EmptyResultDataAccessException e) {
			throw new EnderecoNaoEncontradoException(enderecoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ENDERECO_COM_PESSOA, enderecoId));
		}
	}

	public Endereco buscarOuFalhar(Long enderecoId) {
		return enderecoRepository.findById(enderecoId).orElseThrow(() -> new EnderecoNaoEncontradoException(enderecoId));
	}

}
