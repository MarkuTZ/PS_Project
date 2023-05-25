package markciurea.ps_server.model.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("ACTIVITY_COORDINATOR")
public class ActivityCoordinator extends User {

    public ActivityCoordinator() {
        super();
        setRole(Role.ACTIVITY_COORDINATOR);
    }

}
