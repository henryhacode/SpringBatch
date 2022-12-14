package student.springbatch.backend.batchprocessing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputPerson {
    private String firstName;
    private String lastName;
    private double gpa;
    private int age;
}
