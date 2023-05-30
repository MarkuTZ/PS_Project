package markciurea.ps_server.repository;

import markciurea.ps_server.model.user.Role;
import markciurea.ps_server.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByEmail(String email);

    List<User> findAllByRoleIn(List<Role> role);

}
