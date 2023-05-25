package markciurea.model.entities.dto;

import lombok.Data;
import markciurea.model.entities.user.Role;

@Data
public class UserShort {

    private Long id;

    private String email;

    private String password;

    private Role role;

    private String phoneNr;

}
