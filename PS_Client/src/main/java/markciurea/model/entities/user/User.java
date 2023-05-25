package markciurea.model.entities.user;

import lombok.Data;
import markciurea.model.entities.thrashLocation.ThrashLocation;

import java.util.List;

@Data
public class User {

    private Long id;

    private String email;

    private String password;

    private Role role;

    private String phoneNr;

    private List<ThrashLocation> thrashLocations;

}
