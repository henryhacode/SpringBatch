package student.springbatch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import student.springbatch.batchprocessing.OutputPerson;

@Repository
public interface PersonRepository extends CrudRepository<OutputPerson, Integer> {
}
