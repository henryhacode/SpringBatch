package student.springbatch.backend.batchprocessing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="people")
public class OutputPerson {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String gpa;
    private String dob;
    public OutputPerson(String firstName, String lastName, String gpa, String dob){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gpa = gpa;
        this.dob = dob;
    }
}
