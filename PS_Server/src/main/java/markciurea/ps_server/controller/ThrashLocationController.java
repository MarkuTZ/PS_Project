package markciurea.ps_server.controller;

import lombok.RequiredArgsConstructor;
import markciurea.ps_server.model.dto.thrashLocationDto.ThrashLocationUserShortDTO;
import markciurea.ps_server.service.ThrashLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/thrash")
public class ThrashLocationController {

    private final ThrashLocationService service;

    @GetMapping
    public List<ThrashLocationUserShortDTO> getAllThrashLocations(@RequestParam(name = "email", required = false) String email) {
        if (email == null) {
            return service.getAllThrashLocations().stream().map(ThrashLocationUserShortDTO::new).toList();
        } else {
            return service.getAllThrashLocationsForEmail(email).stream().map(ThrashLocationUserShortDTO::new).toList();
        }
    }

    @PostMapping
    public ThrashLocationUserShortDTO saveThrashLocation(@RequestBody ThrashLocationUserShortDTO newThrash) {
        return service.saveThrashLocation(newThrash);
    }

    @DeleteMapping("/{thrashId}")
    public void deleteThrashLocation(@PathVariable Long thrashId) {
        service.deleteThrashById(thrashId);
    }

}
