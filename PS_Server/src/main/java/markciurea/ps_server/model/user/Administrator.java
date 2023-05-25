package markciurea.ps_server.model.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import markciurea.ps_server.model.dto.userDto.UserShort;

@Entity
@Data
@DiscriminatorValue("ADMINISTRATOR")
public class Administrator extends User {

    public Administrator() {
        super();
        setRole(Role.ADMINISTRATOR);
    }

    public Administrator(UserShort userShort){
        super(userShort);
        setRole(Role.ADMINISTRATOR);
    }

}
