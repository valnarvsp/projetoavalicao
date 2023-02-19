package com.example.gerenciamentopessoas;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.example.gerenciamentopessoas.domain.model.Pessoa;
import com.example.gerenciamentopessoas.domain.service.CadastroPessoaService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroPessoaIT {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/pessoas";
	}

	@Autowired
	private CadastroPessoaService cadastroPessoaServive;

	/**
	 * @author valdinarvsp Testes de Integração
	 */

	@Test
	public void cadastroPessoaComSucesso() {

		// cenário
		Pessoa novaPessoa = new Pessoa();
		novaPessoa.setId(3L);
		novaPessoa.setNome("Valdinar Pereira");

		// ação
		novaPessoa = cadastroPessoaServive.salvar(novaPessoa);

		// validação
		assertThat(novaPessoa).isNotNull();
	}

	/**
	 * @author valdinarvsp Testes de APIs
	 */

	@Test
	public void deveRetornarStatus200_QuandoConsultarPessoa() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		given().basePath("/pessoas").port(port).accept(ContentType.JSON).when().get().then()
				.statusCode(HttpStatus.OK.value());
	}

//
	@Test
	public void deveConter3Pessoas_QuandoConsultarPessoa() {

		given().basePath("/pessoas").port(port).accept(ContentType.JSON).when().get().then()
				.body("nome", Matchers.hasSize(3))
				.body("nome", Matchers.hasItems("Karla Pereira", "Vinícius Pereira", "Vitória Karina Pereira"));
//			    .body("", Matchers.hasSize(3));
	}

}
