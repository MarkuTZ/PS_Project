package markciurea.ps_server.model.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import markciurea.ps_server.model.dto.userDto.UserShort;

@Entity
@Data
@DiscriminatorValue("ACTIVITY_COORDINATOR")
public class ActivityCoordinator extends User {

    public ActivityCoordinator() {
        super();
        setRole(Role.ACTIVITY_COORDINATOR);
    }

    public ActivityCoordinator(UserShort userShort) {
        super(userShort);
        setRole(Role.ACTIVITY_COORDINATOR);
    }

}
