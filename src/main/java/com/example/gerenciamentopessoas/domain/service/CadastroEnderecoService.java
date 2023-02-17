package com.example.gerenciamentopessoas.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.gerenciamentopessoas.domain.exception.EntidadeEmUsoException;
import com.example.gerenciamentopessoas.domain.exception.EntidadeNaoEncontradaException;
import com.example.gerenciamentopessoas.domain.model.Endereco;
import com.example.gerenciamentopessoas.domain.repository.EnderecoRepository;

@Service
public class CadastroEnderecoService {

	private static final String MSG_ENDERECO_COM_ENDERECO = "Pessoa de código %d não pode ser removida, pois possui um endereço cadastrado";

	private static final String MSG_ENDERECO_NAO_ENCONTRADA = "Não existe um cadastro de pessoa com código %d";

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public void excluir(Long enderecoId) {
		try {
			enderecoRepository.deleteById(enderecoId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_ENDERECO_NAO_ENCONTRADA, enderecoId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ENDERECO_COM_ENDERECO, enderecoId));
		}
	}

	public Endereco buscarOuFalhar(Long enderecoId) {
		return enderecoRepository.findById(enderecoId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_ENDERECO_NAO_ENCONTRADA, enderecoId)));
	}

}
