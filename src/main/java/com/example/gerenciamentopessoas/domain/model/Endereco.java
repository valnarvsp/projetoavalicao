package com.example.gerenciamentopessoas.domain.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Endereco {

	@Id
	private Long id;

	@Column(nullable = false)
	private String cep;

	@Column(nullable = false)
	private String logradouro;

	@Column(nullable = false)
	private String numero;

	private boolean isPrincipal = false;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cidade cidade;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isPrincipal() {
		return isPrincipal;
	}

	public void setPrincipal(boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}

}
