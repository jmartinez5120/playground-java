package com.springjohn.springbatchsample.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class LoadController {

    private final JobLauncher jobLauncher;

    private final Job job;

    @Autowired
    public LoadController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping
    public BatchStatus load() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter> maps = new HashMap<>();
        // Adds parameters to the job.
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameter = new JobParameters(maps);
        // Executes the job
        JobExecution jobExecution = jobLauncher.run(job, parameter);

        // Prints while the job is running.
        while (jobExecution.isRunning()) {
            System.out.println("Job is running...");
        }

        return jobExecution.getStatus();
    }

}
