package markciurea.ps_server.model.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("ADMINISTRATOR")
public class Administrator extends User {

    public Administrator() {
        super();
        setRole(Role.ADMINISTRATOR);
    }

}
