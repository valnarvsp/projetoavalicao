package com.example.gerenciamentopessoas.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.gerenciamentopessoas.domain.exception.EntidadeEmUsoException;
import com.example.gerenciamentopessoas.domain.exception.EntidadeNaoEncontradaException;
import com.example.gerenciamentopessoas.domain.model.Pessoa;
import com.example.gerenciamentopessoas.domain.repository.PessoaRepository;

@Service
public class CadastroPessoaService {

	private static final String MSG_PESSOA_COM_ENDERECO = "Pessoa de código %d não pode ser removida, pois possui um endereço cadastrado";

	private static final String MSG_PESSOA_NAO_ENCONTRADA = "Não existe um cadastro de pessoa com código %d";

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public void excluir(Long pessoaId) {
		try {
			pessoaRepository.deleteById(pessoaId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_PESSOA_NAO_ENCONTRADA, pessoaId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PESSOA_COM_ENDERECO, pessoaId));
		}
	}

	public Pessoa buscarOuFalhar(Long pessoaId) {
		return pessoaRepository.findById(pessoaId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_PESSOA_NAO_ENCONTRADA, pessoaId)));
	}

}
