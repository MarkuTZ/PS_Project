package markciurea.ps_server.service;

import lombok.RequiredArgsConstructor;
import markciurea.ps_server.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

}
