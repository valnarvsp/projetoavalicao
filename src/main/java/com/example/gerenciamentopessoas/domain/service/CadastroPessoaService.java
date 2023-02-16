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
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa salvar(Pessoa pessoa) {
			return pessoaRepository.save(pessoa);
	}
	
	public void excluir(Long pessoaId) {
		try {
			pessoaRepository.deleteById(pessoaId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de pessoa com código %d", pessoaId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Pessoa de código %d não pode ser removido, pois está em uso", pessoaId));
		}
	}

}
