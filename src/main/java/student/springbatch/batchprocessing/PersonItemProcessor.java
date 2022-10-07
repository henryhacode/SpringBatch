package student.springbatch.batchprocessing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

import static java.time.LocalDate.now;


@Slf4j
public class PersonItemProcessor implements ItemProcessor<InputPerson, OutputPerson> {
    @Override
    public OutputPerson process(final InputPerson person) {
        String firstName = person.getFirstName().toUpperCase();
        String lastName = person.getLastName().toUpperCase();
        int age = person.getAge();
        LocalDate dob = now().minusYears(age).withMonth(1).withDayOfMonth(1);

        final OutputPerson transformedPerson = new OutputPerson(firstName, lastName, dob.toString());
        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}
