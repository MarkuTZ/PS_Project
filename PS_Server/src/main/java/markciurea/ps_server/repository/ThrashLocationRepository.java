package markciurea.ps_server.repository;

import markciurea.ps_server.model.thrashLocation.ThrashLocation;
import markciurea.ps_server.model.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThrashLocationRepository extends JpaRepository<ThrashLocation, Long> {

    List<ThrashLocation> findAllByEmployee(Employee employee);

}
