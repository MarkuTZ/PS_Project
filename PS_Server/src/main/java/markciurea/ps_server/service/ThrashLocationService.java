package markciurea.ps_server.service;

import lombok.RequiredArgsConstructor;
import markciurea.ps_server.config.CustomError;
import markciurea.ps_server.model.thrashLocation.ThrashLocation;
import markciurea.ps_server.model.user.Employee;
import markciurea.ps_server.model.user.Role;
import markciurea.ps_server.model.user.User;
import markciurea.ps_server.repository.ThrashLocationRepository;
import markciurea.ps_server.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThrashLocationService {

    private final ThrashLocationRepository thrashLocationRepository;
    private final UserRepository userRepository;

    public List<ThrashLocation> getAllThrashLocations() {
        return thrashLocationRepository.findAll();
    }

    public List<ThrashLocation> getAllThrashLocationsForEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null || !user.getRole().equals(Role.EMPLOYEE)) {
            throw new CustomError(HttpStatus.NOT_FOUND, "User not found/ employee.");
        }
        return thrashLocationRepository.findAllByEmployee((Employee) user);
    }

}
