package student.springbatch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import student.springbatch.service.BatchService;

@CrossOrigin
@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {
    private final BatchService batchService;

    @PostMapping
    public BatchStatus runBatch() {
        return batchService.runBatch();
    }
}
