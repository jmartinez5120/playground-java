package com.springjohn.springbatchsample.config;

import com.springjohn.springbatchsample.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

// Annotation need for newer versions of Spring Batch
@EnableBatchProcessing
@Configuration
public class SpringBatchConfig {

    /**
     * Setup the properties and steps for all the batch jobs that will be running as part of this Spring Batch Job.
     * @param jobBuilderFactory
     * @param stepBuilderFactory
     * @param itemReader
     * @param itemProcessor
     * @param itemWriter
     * @return {@link Job}
     */
    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<User> itemReader,
                   ItemProcessor<User, User> itemProcessor,
                   ItemWriter<User> itemWriter){
        Step step = stepBuilderFactory.get("ETL-file-load")
                .<User, User>chunk(100) // takes the records 100 by 100
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                // If you have multiple STEPs, then you can use .next to call the next job.
                .build();
    }

    /**
     * Set all the properties to read the CSV file and map the input CSV with the {@link User} POJO.
     * @param resource input file from the resource folder.
     * @return {@link FlatFileItemReader<User>}
     */
    @Bean
    public FlatFileItemReader<User> itemReader(@Value("${input}") Resource resource) {

        FlatFileItemReader<User> userFlatFileItemReader = new FlatFileItemReader<>();
        userFlatFileItemReader.setResource(resource);
        userFlatFileItemReader.setName("CSV-Reader");
        // If there is any issue it will skip that issue line. Also, it will always skip the first line by default (header)
        userFlatFileItemReader.setLinesToSkip(1);
        userFlatFileItemReader.setLineMapper(lineMapper());

        return userFlatFileItemReader;
    }

    /**
     * Maps the CSV attributes with the {@link User} POJO
     * @return {@link LineMapper<User>}
     */
    @Bean
    public LineMapper<User> lineMapper() {
        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] {"id", "name", "department", "salary"});

        // Map the POJO to the line tokenizer.
        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }


}
