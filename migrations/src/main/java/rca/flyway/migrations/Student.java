package rca.flyway.migrations;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity()
@Data()
public class Student {
    @Id()
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
