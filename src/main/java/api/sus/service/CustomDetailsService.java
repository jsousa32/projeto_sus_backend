package api.sus.service;

import api.sus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The Class CustomDetailsService
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Service
public class CustomDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.loadUserByUsername(email);
    }
}
