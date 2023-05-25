package markciurea.ps_server.model.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import markciurea.ps_server.model.user.Role;
import markciurea.ps_server.model.user.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShort {

    private Long id;

    private String email;

    private String password;

    private Role role;

    private String phoneNr;

    public UserShort(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.phoneNr = user.getPhoneNr();
    }

}
