package markciurea.ps_server.model.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role",
        discriminatorType = DiscriminatorType.STRING,
        columnDefinition = "enum('EMPLOYEE', 'ACTIVITY_COORDINATOR', 'ADMINISTRATOR')")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",
            insertable = false,
            updatable = false)
    private Role role;

    private String phoneNr;

}
