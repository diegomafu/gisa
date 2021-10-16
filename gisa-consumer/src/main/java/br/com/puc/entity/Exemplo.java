package br.com.puc.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "Exemplo")
public class Exemplo {

	@Id
	private Long id;
	private String nome;
	private Integer idade;
	private Date data;
	private Double valor;
	
}
