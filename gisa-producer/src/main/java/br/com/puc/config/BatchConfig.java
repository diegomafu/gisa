package br.com.puc.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import br.com.puc.entity.Exemplo;
import br.com.puc.producer.Producer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableBatchProcessing
@Configuration
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory; 
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job producerJob(Step step) {
		return this.jobBuilderFactory.get("producerJob")
				.incrementer(new RunIdIncrementer())
				.flow(step)
				.end()
				.build();
	}
	
	@Bean
	public Step step(ItemReader<Exemplo> reader, ItemWriter<Exemplo> writer) {
		return this.stepBuilderFactory.get("producerStep")
				.<Exemplo, Exemplo>chunk(10)
				.reader(reader)
				.writer(writer)
				.build();
	}
	
	@Bean
	public ItemReader<Exemplo> reader(DataSource dataSource) throws Exception{
		return new JdbcCursorItemReaderBuilder<Exemplo>()
				.dataSource(dataSource)
				.saveState(false)
				.sql("Select * from Exemplo")
				.rowMapper(mapper())
				.build();
	}
	
	private RowMapper<Exemplo> mapper(){
		return new RowMapper<Exemplo>() {
			@Override
			public Exemplo mapRow(ResultSet rs, int rowNum) throws SQLException{
				return Exemplo.builder()
						.id(rs.getLong("id"))
						.nome(rs.getString("nome"))
						.idade(rs.getInt("idade"))
						.data(rs.getDate("data"))
						.valor(rs.getDouble("valor")).build();
						
			}
		};
	}
	
	@Bean
	public ItemWriter<Exemplo> writer(Producer producer) throws Exception{
		return new ItemWriter<Exemplo>(){
			
			@Override
			public void write(List<? extends Exemplo> items) throws Exception{
				log.info("Enviando total de "+items.size());
				for(Exemplo exemplo : items) {
					try {
						producer.send(exemplo);
					}catch(Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		};
	}
}
