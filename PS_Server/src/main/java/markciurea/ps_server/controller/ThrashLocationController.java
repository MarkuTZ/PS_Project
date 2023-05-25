package markciurea.ps_server.controller;

import lombok.RequiredArgsConstructor;
import markciurea.ps_server.model.thrashLocation.ThrashLocation;
import markciurea.ps_server.service.ThrashLocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/thrash")
public class ThrashLocationController {

    private final ThrashLocationService service;

    @GetMapping
    public List<ThrashLocation> getAllThrashLocations() {
        return service.getAllThrashLocations();
    }

}
