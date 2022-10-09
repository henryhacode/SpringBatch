package student.springbatch.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BatchService {
    private final JobLauncher jobLauncher;
    private final Job job;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BatchStatus runBatch() {
        Map<String, JobParameter> parameters = new HashMap<>();
        try {
            JobExecution jobExecution = jobLauncher.run(job, new JobParameters(parameters));
            BatchStatus status = jobExecution.getStatus();
            return status;
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        }
    }
}
