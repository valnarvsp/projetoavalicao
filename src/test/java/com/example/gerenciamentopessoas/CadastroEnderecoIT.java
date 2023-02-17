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

import com.example.gerenciamentopessoas.domain.model.Endereco;
import com.example.gerenciamentopessoas.domain.service.CadastroEnderecoService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroEnderecoIT {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/enderecos";
	}

	@Autowired
	private CadastroEnderecoService cadastroEnderecoServive;

	@Test
	public void cadastroEnderecoComSucesso() {

		// cenário
		Endereco novaEndereco = new Endereco();
		novaEndereco.setId(4L);
		novaEndereco.setCep("87083-675");

		// ação
		novaEndereco = cadastroEnderecoServive.salvar(novaEndereco);

		// validação
		assertThat(novaEndereco).isNotNull();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarEndereco() {

		given().basePath("/enderecos").port(port).accept(ContentType.JSON).when().get().then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConter7Endereco_QuandoConsultarEndereco() {

		given().basePath("/enderecos").port(port).accept(ContentType.JSON).when().get().then()
				.body("cep", Matchers.hasSize(7)).body("cep", Matchers.hasItems("87083-675", "87083-567"));
//			.body("", Matchers.hasSize(7));
	}

	/**
	 * @author valdinarvsp Teste de validaçaõ de resposta ao incluir uma endereço,
	 *         tatusCode(HttpStatus.CREATED.value()) Observação: Para este teste o
	 *         arquivo import.sql de deve está comentado.
	 * 
	 */

//	@Test
//	public void testRetornarStatus201_QuandoCadastrarEndereco() {
//		given()
//			.body("{ \"cep\": \"87083-675\" }")
//			.contentType(ContentType.JSON)
//			.accept(ContentType.JSON)
//		.when()
//			.post()
//		.then()
//			.statusCode(HttpStatus.CREATED.value());
//	}

}
