package markciurea.ps_server.model.user;

import jakarta.persistence.*;
import lombok.Data;
import markciurea.ps_server.model.thrashLocation.ThrashLocation;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<ThrashLocation> thrashLocations;

    public Employee() {
        super();
        setRole(Role.EMPLOYEE);
    }

    public void addThrashLocation(ThrashLocation thrashLocation) {
        if (thrashLocations == null) {
            thrashLocations = new ArrayList<>();
        }
        thrashLocations.add(thrashLocation);
        thrashLocation.setEmployee(this);
    }

    public void removeThrashLocation(ThrashLocation thrashLocation) {
        thrashLocations.remove(thrashLocation);
        thrashLocation.setEmployee(null);
    }
}
