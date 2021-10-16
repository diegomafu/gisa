package br.com.puc.entity;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Exemplo {

	private Long id;
	private String nome;
	private Integer idade;
	private Date data;
	private Double valor;
	
}
