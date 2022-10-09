package student.springbatch.backend.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import student.springbatch.backend.repository.PersonRepository;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public PersonRepository personRepository;

    @Bean
    public FlatFileItemReader<InputPerson> reader() {
        return new FlatFileItemReaderBuilder<InputPerson>()
                .name("personItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names("firstName", "lastName", "gpa", "age")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<InputPerson>(){{
                    setTargetType(InputPerson.class);
                }})
                .build();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

//    @Bean
//    public JdbcBatchItemWriter<OutputPerson> writer(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<OutputPerson>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO people (first_name, last_name, gpa, dob) VALUES (:firstName, :lastName, :gpa, :dob)")
//                .dataSource(dataSource)
//                .build();
//    }

    @Bean
    public RepositoryItemWriter<OutputPerson> writer(){
        RepositoryItemWriter<OutputPerson> writer = new RepositoryItemWriter<>();
        writer.setRepository(personRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Job importUserJob(//JobCompletionNotificationListener listener,
                             Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                //.listener(listener)
                .flow(step1)
                .end()
                .build();
    }

//    @Bean
//    public Step step1(JdbcBatchItemWriter<OutputPerson> writer) {
//        return stepBuilderFactory.get("step1")
//                .allowStartIfComplete(true)
//                .<InputPerson, OutputPerson> chunk(10)
//                .reader(reader())
//                .processor(processor())
//                .writer(writer)
//                .build();
//    }

    @Bean
    public Step step1(RepositoryItemWriter<OutputPerson> writer) {
        return stepBuilderFactory.get("step1")
                .allowStartIfComplete(true)
                .<InputPerson, OutputPerson> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}
