package br.com.puc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.puc.entity.Exemplo;

public interface ExemploRepository extends JpaRepository<Exemplo, Long>{
	Exemplo findFirstByOrderByIdDesc();
}
