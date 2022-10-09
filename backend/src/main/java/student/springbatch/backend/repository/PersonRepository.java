package student.springbatch.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import student.springbatch.backend.batchprocessing.OutputPerson;

@Repository
public interface PersonRepository extends CrudRepository<OutputPerson, Integer> {
}
