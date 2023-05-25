package markciurea.ps_server.repository;

import markciurea.ps_server.model.thrashLocation.ThrashLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThrashLocationRepository extends JpaRepository<ThrashLocation, Long> {
}
