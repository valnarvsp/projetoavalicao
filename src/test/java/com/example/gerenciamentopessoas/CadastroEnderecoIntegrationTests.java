package com.example.gerenciamentopessoas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gerenciamentopessoas.domain.exception.EntidadeEmUsoException;
import com.example.gerenciamentopessoas.domain.exception.EntidadeNaoEncontradaException;
import com.example.gerenciamentopessoas.domain.model.Endereco;
import com.example.gerenciamentopessoas.domain.service.CadastroEnderecoService;

@SpringBootTest
public class CadastroEnderecoIntegrationTests {

	@Autowired
	private CadastroEnderecoService cadastroEnderecoServive;

	@Test
	public void cadastroEnderecoComSucesso() {

		// cenário
		Endereco novoEndereco = new Endereco();
		novoEndereco.setId(9L);
		novoEndereco.setCep("87083-675");

		// ação
		novoEndereco = cadastroEnderecoServive.salvar(novoEndereco);

		// validação
		assertThat(novoEndereco).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoExcluirEnderecoInexistente() {

		EntidadeNaoEncontradaException erroEsperado = Assertions.assertThrows(EntidadeNaoEncontradaException.class,
				() -> {
					cadastroEnderecoServive.excluir(10L);
				});

		assertThat(erroEsperado).isNotNull();

	}

}
