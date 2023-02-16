package com.example.gerenciamentopessoas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gerenciamentopessoas.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
