package markciurea.ps_server.model.thrashLocation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import markciurea.ps_server.model.user.Employee;

@Data
@Entity
public class ThrashLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String addressName;

    private Long x;

    private Long y;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employee;
}
