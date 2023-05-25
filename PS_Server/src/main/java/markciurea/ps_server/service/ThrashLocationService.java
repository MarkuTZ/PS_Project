package markciurea.ps_server.service;

import lombok.RequiredArgsConstructor;
import markciurea.ps_server.model.thrashLocation.ThrashLocation;
import markciurea.ps_server.repository.ThrashLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThrashLocationService {

    private final ThrashLocationRepository repository;

    public List<ThrashLocation> getAllThrashLocations() {
        return repository.findAll();
    }

}
