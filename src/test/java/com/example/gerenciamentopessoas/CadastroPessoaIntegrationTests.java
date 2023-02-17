package com.example.gerenciamentopessoas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gerenciamentopessoas.domain.exception.EntidadeEmUsoException;
import com.example.gerenciamentopessoas.domain.exception.EntidadeNaoEncontradaException;
import com.example.gerenciamentopessoas.domain.model.Pessoa;
import com.example.gerenciamentopessoas.domain.service.CadastroPessoaService;

@SpringBootTest
class CadastroPessoaIntegrationTests {

	@Autowired
	private CadastroPessoaService cadastroPessoaServive;

	@Test
	public void cadastroPessoaComSucesso() {

		// cenário
		Pessoa novaPessoa = new Pessoa();
		novaPessoa.setId(4L);
		novaPessoa.setNome("Valdinar Pereira");

		// ação
		novaPessoa = cadastroPessoaServive.salvar(novaPessoa);

		// validação
		assertThat(novaPessoa).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoExcluirPessoaComEndereco() {

		EntidadeEmUsoException erroEsperado = Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
					cadastroPessoaServive.excluir(4L);
				});

		assertThat(erroEsperado).isNotNull();

	}
	
	@Test
	public void deveFalhar_QuandoExcluirPessoaInexistente() {


		EntidadeNaoEncontradaException erroEsperado =
				Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
					cadastroPessoaServive.excluir(100L);
				});

		assertThat(erroEsperado).isNotNull();

	}


}
