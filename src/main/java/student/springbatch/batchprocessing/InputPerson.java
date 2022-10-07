package student.springbatch.batchprocessing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputPerson {
    private String firstName;
    private String lastName;
    private int age;
}
